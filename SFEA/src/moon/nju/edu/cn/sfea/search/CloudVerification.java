package moon.nju.edu.cn.sfea.search;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import moon.nju.edu.cn.fm.model.AndOperator;
import moon.nju.edu.cn.fm.model.BooleanConstraints;
import moon.nju.edu.cn.fm.model.CardExConstraint;
import moon.nju.edu.cn.fm.model.Constraints;
import moon.nju.edu.cn.fm.model.Feature;
import moon.nju.edu.cn.fm.model.FeatureModel;
import moon.nju.edu.cn.fm.model.Operation;
import moon.nju.edu.cn.fm.model.OrFeature;
import moon.nju.edu.cn.fm.model.SFEAPackage;
import moon.nju.edu.cn.fm.model.XorFeature;
import moon.nju.edu.cn.sfea.consistency.HerokuConsist;

import kodkod.ast.Expression;
import kodkod.ast.Formula;
import kodkod.ast.IntConstant;
import kodkod.ast.Relation;
import kodkod.engine.Solution;
import kodkod.engine.Solver;
import kodkod.engine.satlab.SATFactory;
import kodkod.instance.Bounds;
import kodkod.instance.Instance;
import kodkod.instance.Tuple;
import kodkod.instance.TupleFactory;
import kodkod.instance.TupleSet;
import kodkod.instance.Universe;

public class CloudVerification {
	protected List<Formula> formulas = new LinkedList<Formula>();
	private MetaModelConstraints metamodel;
	
	private Relation fm1;
	private FeatureModel cloudFeatureModel;
	protected Feature rootFeature;
	private LinkedList<Constraints> constraints = new LinkedList<Constraints>();
	
	protected Map<Feature, Relation> signMap = new LinkedHashMap<Feature, Relation>();
	private List<Relation> signList = new LinkedList<Relation>(); 
	private List<Relation> relationList = new LinkedList<Relation>();
	private List<Relation> formList = new LinkedList<Relation>();
	private List<Relation> fakeList = new LinkedList<Relation>(); 
	private List<Relation> nameFList = new LinkedList<Relation>();
	private Map<Relation, String> relationNameMap = new LinkedHashMap<Relation, String>();
	private int mRelationIndex = 0;
	private int mNameFIndex = 0;
	private int mFormIndex = 0;
	private int mSignIndex = 0;
	
	public CloudVerification(String file) {
		metamodel = new MetaModelConstraints();
		loadModel(file);
	}
	
	public Formula getFormulas() {
		Formula result = Formula.TRUE;
		for (Formula f: metamodel.getFormulas()) {
			result = result.and(f);
		}
		
		for (Formula f: formulas) {
			result = result.and(f);
		}
		
		return result;
	}
	
	private void loadModel(String file) {
		SFEAPackage.eINSTANCE.eClass();
		Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> map = registry.getExtensionToFactoryMap();
		map.put("fm", new XMIResourceFactoryImpl());
		
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(URI.createURI(file), true);
		this.cloudFeatureModel = (FeatureModel) resource.getContents().get(0);
		this.rootFeature = cloudFeatureModel.getRoot();
		this.constraints.addAll(cloudFeatureModel.getConstraints());
		
		this.loadSignature();
		this.loadRelation();
		this.loadFormula();
		
		fm1 = Relation.unary("fm1");
		formulas.add(fm1.join(MetaModelConstraints.rRoot).eq(signMap.get(rootFeature)));
		
		Expression features = null;
		for (Relation signRelation: signList) {
			formulas.add(signRelation.one());
			features = features == null ? signRelation : features.union(signRelation);
		}
		
		formulas.add(fm1.join(MetaModelConstraints.rFeatures).eq(features));
		
		Expression relations = null;
		for (Relation relation: relationList) {
			formulas.add(relation.one());
			relations = relations == null ? relation : relations.union(relation);
		}
		
		if (relations != null) {
			formulas.add(fm1.join(MetaModelConstraints.rRelations).eq(relations));
		}
		
		Expression forms = null;
		for (Relation form : formList) {
			formulas.add(form.one());
			if (!fakeList.contains(form)) {
				forms = forms == null ? form : forms.union(form);
			}
		}
		
		if (forms != null) {
			formulas.add(fm1.join(MetaModelConstraints.rFormulas).eq(forms));
		}
		
		for (Relation nameF : nameFList) {
			formulas.add(nameF.one());
		}
	}
	
	private void loadSignature() {
		LinkedList<Feature> queue = new LinkedList<Feature>();
		queue.add(rootFeature);
		
		while (!queue.isEmpty()) {
			Feature feature = queue.poll();
			String name = "s" + (++ mSignIndex);
			Relation relation = Relation.unary(name);
			signMap.put(feature, relation);
			relationNameMap.put(relation, name);
			signList.add(relation);
			
			// TODO calculate cardinality from non-functional requirements
			formulas.add(relation.join(MetaModelConstraints.rCard).count().eq(IntConstant.constant(1)));
			formulas.add(relation.join(MetaModelConstraints.rCard).sum().eq(IntConstant.constant(1)));
			
			if (feature instanceof XorFeature) {
				XorFeature xorFeature = (XorFeature) feature;
				for (Feature subFeature: xorFeature.getVariants()) {
					queue.add(subFeature);
				}
			} else if (feature instanceof OrFeature) {
				OrFeature orFeature = (OrFeature) feature;
				for (Feature subFeature: orFeature.getVariants()) {
					queue.add(subFeature);
				}
			} else {
				for (Feature subFeature: feature.getSubFeatures()) {
					queue.add(subFeature);
				}
			}
		}
	}
	
	private void loadRelation() {
		LinkedList<Feature> queue = new LinkedList<Feature>();
		queue.add(rootFeature);
		
		while (!queue.isEmpty()) {
			Feature feature = queue.poll();
			if (feature instanceof XorFeature) {
				XorFeature xorFeature = (XorFeature) feature;
				Expression child = null;
				for (Feature subFeature: xorFeature.getVariants()) {
					queue.add(subFeature);
					child = child == null ? signMap.get(subFeature) : child.union(signMap.get(subFeature));
				}
				
				int min = xorFeature.getGroupCardinality().getMin();
				int max = xorFeature.getGroupCardinality().getMax();
				
				String name = "r" + (++ mRelationIndex);
				Relation relation = Relation.unary(name);
				relationNameMap.put(relation, name);
				formulas.add(relation.join(MetaModelConstraints.rType).eq(MetaModelConstraints.sigXorFeature));
				formulas.add(relation.join(MetaModelConstraints.rParent).eq(signMap.get(feature)));
				formulas.add(relation.join(MetaModelConstraints.rChild).eq(child));
				formulas.add(relation.join(MetaModelConstraints.rMin).count().eq(IntConstant.constant(1)));
				formulas.add(relation.join(MetaModelConstraints.rMax).count().eq(IntConstant.constant(1)));
				formulas.add(relation.join(MetaModelConstraints.rMin).sum().eq(IntConstant.constant(min)));
				formulas.add(relation.join(MetaModelConstraints.rMax).sum().eq(IntConstant.constant(max)));

				relationList.add(relation);
			} else if (feature instanceof OrFeature) {
				OrFeature orFeature = (OrFeature) feature;
				Expression child = null;
				for (Feature subFeature: orFeature.getVariants()) {
					queue.add(subFeature);
					child = child == null ? signMap.get(subFeature) : child.union(signMap.get(subFeature));
				}
				
				int min = orFeature.getGroupCardinality().getMin();
				int max = orFeature.getGroupCardinality().getMax();
				
				String name = "r" + (++ mRelationIndex);
				Relation relation = Relation.unary(name);
				relationNameMap.put(relation, name);
				formulas.add(relation.join(MetaModelConstraints.rType).eq(MetaModelConstraints.sigOrFeature));
				formulas.add(relation.join(MetaModelConstraints.rParent).eq(signMap.get(feature)));
				formulas.add(relation.join(MetaModelConstraints.rChild).eq(child));
				formulas.add(relation.join(MetaModelConstraints.rMin).count().eq(IntConstant.constant(1)));
				formulas.add(relation.join(MetaModelConstraints.rMax).count().eq(IntConstant.constant(1)));
				formulas.add(relation.join(MetaModelConstraints.rMin).sum().eq(IntConstant.constant(min)));
				formulas.add(relation.join(MetaModelConstraints.rMax).sum().eq(IntConstant.constant(max)));
				
				relationList.add(relation);
			} else {
				for (Feature subFeature: feature.getSubFeatures()) {
					queue.add(subFeature);
					
					String name = "r" + (++ mRelationIndex);
					Relation relation = Relation.unary(name);
					relationNameMap.put(relation, name);
					int min = subFeature.getFeatureCardinality().getMin();
					int max = subFeature.getFeatureCardinality().getMax();
					
					if (min == 1 && max == 1) {
						formulas.add(relation.join(MetaModelConstraints.rType).eq(MetaModelConstraints.sigMandatory));
					} else if (min == 0 && max == 1) {
						formulas.add(relation.join(MetaModelConstraints.rType).eq(MetaModelConstraints.sigOptional));
					} else {
//						System.out.println("wrong model\t" + feature.getName() + "\t" + subFeature.getName());
						formulas.add(relation.join(MetaModelConstraints.rType).eq(MetaModelConstraints.sigOptional));
					}
					
					formulas.add(relation.join(MetaModelConstraints.rParent).eq(signMap.get(feature)));
					formulas.add(relation.join(MetaModelConstraints.rChild).eq(signMap.get(subFeature)));
					formulas.add(relation.join(MetaModelConstraints.rMin).count().eq(IntConstant.constant(0)));
					formulas.add(relation.join(MetaModelConstraints.rMax).count().eq(IntConstant.constant(0)));
					
					relationList.add(relation);
				}
			}
		}
	}

	private void loadFormula() {
		for (Constraints constraint : constraints) {
			if (constraint instanceof BooleanConstraints) {
				BooleanConstraints impliesConstraint = (BooleanConstraints) constraint;
				Feature fromFeature = impliesConstraint.getFrom();
				Feature toFeature = impliesConstraint.getTo();
				
				String impliesName = "f" + (++ mFormIndex);
				Relation impliesRelation = Relation.unary(impliesName);
				relationNameMap.put(impliesRelation, impliesName);
				
				String fromName = "nf" + (++ mNameFIndex);
				Relation fromRelation = Relation.unary(fromName);
				relationNameMap.put(fromRelation, fromName);
				
				String toName = "nf" + (++ mNameFIndex);
				Relation toRelation = Relation.unary(toName);
				relationNameMap.put(toRelation, toName);
				
				formulas.add(fromRelation.join(MetaModelConstraints.rName).eq(signMap.get(fromFeature)));
				formulas.add(toRelation.join(MetaModelConstraints.rName).eq(signMap.get(toFeature)));
				formulas.add(fromRelation.join(MetaModelConstraints.rSize).sum().eq(IntConstant.constant(1)));
				formulas.add(toRelation.join(MetaModelConstraints.rSize).sum().eq(IntConstant.constant(1)));
				formulas.add(fromRelation.join(MetaModelConstraints.rSize).count().eq(IntConstant.constant(1)));
				formulas.add(toRelation.join(MetaModelConstraints.rSize).count().eq(IntConstant.constant(1)));
				
				formulas.add(impliesRelation.join(MetaModelConstraints.rOp).eq(MetaModelConstraints.sigImpliesF));
				formulas.add(impliesRelation.join(MetaModelConstraints.rLeft).eq(fromRelation));
				formulas.add(impliesRelation.join(MetaModelConstraints.rRight).eq(toRelation));
				
				nameFList.add(fromRelation);
				nameFList.add(toRelation);
				
				formList.add(impliesRelation);
			} else if (constraint instanceof CardExConstraint) {
				CardExConstraint cardExConstraint = (CardExConstraint) constraint;
				Operation action = cardExConstraint.getAction();
				List<Operation> conditionList = cardExConstraint.getCondition();
				Operation left_condition = conditionList.get(0);
				Operation right_condition = conditionList.get(1);
				
				Feature leftFeature = left_condition.getFeature();
				Feature rightFeature = right_condition.getFeature();
				Feature actionFeature = action.getFeature();
				
				String conditionName = "f" + (++ mFormIndex);
				Relation conditionRelation = Relation.unary(conditionName);
				relationNameMap.put(conditionRelation, conditionName);
				
				String impliesName = "f" + (++ mFormIndex);
				Relation impliesRelation = Relation.unary(impliesName);
				relationNameMap.put(impliesRelation, impliesName);
				
				String leftName = "nf" + (++ mNameFIndex);
				Relation leftRelation = Relation.unary(leftName);
				relationNameMap.put(leftRelation, leftName);
				
				String rightName = "nf" + (++ mNameFIndex);
				Relation rightRelation = Relation.unary(rightName);
				relationNameMap.put(rightRelation, rightName);
				
				String actionName = "nf" + (++ mNameFIndex);
				Relation actionRelation = Relation.unary(actionName);
				relationNameMap.put(actionRelation, actionName);
				
				formulas.add(leftRelation.join(MetaModelConstraints.rSize).count().eq(IntConstant.constant(1)));
				formulas.add(rightRelation.join(MetaModelConstraints.rSize).count().eq(IntConstant.constant(1)));
				formulas.add(actionRelation.join(MetaModelConstraints.rSize).count().eq(IntConstant.constant(1)));
				formulas.add(leftRelation.join(MetaModelConstraints.rSize).sum().eq(IntConstant.constant(left_condition.getValue())));
				formulas.add(rightRelation.join(MetaModelConstraints.rSize).sum().eq(IntConstant.constant(right_condition.getValue())));
				formulas.add(actionRelation.join(MetaModelConstraints.rSize).sum().eq(IntConstant.constant(action.getValue())));
				formulas.add(leftRelation.join(MetaModelConstraints.rName).eq(signMap.get(leftFeature)));
				formulas.add(rightRelation.join(MetaModelConstraints.rName).eq(signMap.get(rightFeature)));
				formulas.add(actionRelation.join(MetaModelConstraints.rName).eq(signMap.get(actionFeature)));
				
				if (cardExConstraint.getOperator() instanceof AndOperator) {
					formulas.add(conditionRelation.join(MetaModelConstraints.rOp).eq(MetaModelConstraints.sigAndF));
				} else {
					formulas.add(conditionRelation.join(MetaModelConstraints.rOp).eq(MetaModelConstraints.sigOrF));
				}
				
				formulas.add(conditionRelation.join(MetaModelConstraints.rLeft).eq(leftRelation));
				formulas.add(conditionRelation.join(MetaModelConstraints.rRight).eq(rightRelation));
				formulas.add(impliesRelation.join(MetaModelConstraints.rOp).eq(MetaModelConstraints.sigImpliesF));
				formulas.add(impliesRelation.join(MetaModelConstraints.rLeft).eq(conditionRelation));
				formulas.add(impliesRelation.join(MetaModelConstraints.rRight).eq(actionRelation));
				
				nameFList.add(leftRelation);
				nameFList.add(rightRelation);
				nameFList.add(actionRelation);
				
				// conditionRelation should not be one of the formulas, add it in fake list
				// e.g. a^b->c
				// conditionRelation is a^b, it could not be satisfied
				formList.add(conditionRelation);
				fakeList.add(conditionRelation);
				formList.add(impliesRelation);
			}
		}
	}
	
	private Bounds bounds() {
		final List<Object> atoms = new LinkedList<Object>();
		atoms.add("True");
		atoms.add("False");
		
		atoms.add("Optional");
		atoms.add("Mandatory");
		atoms.add("OrFeature");
		atoms.add("XorFeature");
		
		atoms.add("AndF");
		atoms.add("OrF");
		atoms.add("ImpliesF");
		atoms.add("NotF");
		
		atoms.add("fm1");
		
		for (Relation relation: signList) {
			atoms.add(relationNameMap.get(relation));
		}
		
		for (Relation relation: relationList) {
			atoms.add(relationNameMap.get(relation));
		}
		
		if (mNameFIndex == 0) {
			atoms.add("f0");
			atoms.add("nf0");
		} else {
			for (Relation form: formList) {
				atoms.add(relationNameMap.get(form));
			}
			
			for (Relation nameF: nameFList) {
				atoms.add(relationNameMap.get(nameF));
			}
		}
		
		int configurationSize = Math.min(50, (int) Math.pow(2, signMap.size()) - 1);
		for (int i = 0; i <= configurationSize; ++i) {
			atoms.add("Configuration" + i);
		}
		
		for (int i = 0; i < 20; ++i) {
			atoms.add(Integer.valueOf(i));
		}
		
		final Universe universe = new Universe(atoms);
		final TupleFactory factory = universe.factory();
		final Bounds bounds = new Bounds(universe);
		
		final TupleSet typeTuple = factory.range(factory.tuple("Optional"), factory.tuple("XorFeature"));
		final TupleSet operationTuple = factory.range(factory.tuple("AndF"), factory.tuple("NotF"));
		final TupleSet configurationTuple = factory.range(factory.tuple("Configuration0"), factory.tuple("Configuration" + configurationSize));
		final TupleSet intTuple = factory.range(factory.tuple(Integer.valueOf(0)), factory.tuple(Integer.valueOf(9)));
		final TupleSet booleanTuple = factory.range(factory.tuple("True"), factory.tuple("False"));

		final TupleSet fmTuple = factory.range(factory.tuple("fm1"), factory.tuple("fm1"));
		final TupleSet nameTuple = factory.range(factory.tuple("s1"), factory.tuple("s" + mSignIndex));
		final TupleSet relationTuple = factory.range(factory.tuple("r1"), factory.tuple("r" + mRelationIndex));
		
		TupleSet nameFTuple = null;
		TupleSet formTuple = null;
		TupleSet formulaTuple = null;
		
		if (mNameFIndex == 0) {
			nameFTuple = factory.range(factory.tuple("nf0"), factory.tuple("nf0"));
			formTuple = factory.range(factory.tuple("f0"), factory.tuple("f0"));
			formulaTuple = factory.range(factory.tuple("f0"), factory.tuple("nf0"));
		} else {
			nameFTuple = factory.range(factory.tuple("nf1"), factory.tuple("nf" + mNameFIndex));
			formTuple = factory.range(factory.tuple("f1"), factory.tuple("f" + mFormIndex));
			formulaTuple = factory.range(factory.tuple("f1"), factory.tuple("nf" + mNameFIndex));
		}

		
		for (Relation relation: signList) {
			Tuple tuple = factory.tuple(relationNameMap.get(relation));
			bounds.boundExactly(relation, factory.range(tuple, tuple));
		}
		
		for (Relation relation: relationList) {
			Tuple tuple = factory.tuple(relationNameMap.get(relation));
			bounds.boundExactly(relation, factory.range(tuple, tuple));
		}
		
		for (Relation relation: formList) {
			Tuple tuple = factory.tuple(relationNameMap.get(relation));
			bounds.boundExactly(relation, factory.range(tuple, tuple));
		}
		
		for (Relation relation: nameFList) {
			Tuple tuple = factory.tuple(relationNameMap.get(relation));
			bounds.boundExactly(relation, factory.range(tuple, tuple));
		}
		
		
		bounds.boundExactly(fm1, factory.range(factory.tuple("fm1"), factory.tuple("fm1")));
		bounds.boundExactly(MetaModelConstraints.sigOptional, factory.range(factory.tuple("Optional"), factory.tuple("Optional")));
		bounds.boundExactly(MetaModelConstraints.sigMandatory, factory.range(factory.tuple("Mandatory"), factory.tuple("Mandatory")));
		bounds.boundExactly(MetaModelConstraints.sigOrFeature, factory.range(factory.tuple("OrFeature"), factory.tuple("OrFeature")));
		bounds.boundExactly(MetaModelConstraints.sigXorFeature, factory.range(factory.tuple("XorFeature"), factory.tuple("XorFeature")));
		bounds.boundExactly(MetaModelConstraints.sigType, typeTuple);
		
		bounds.boundExactly(MetaModelConstraints.sigAndF, factory.range(factory.tuple("AndF"), factory.tuple("AndF")));
		bounds.boundExactly(MetaModelConstraints.sigOrF, factory.range(factory.tuple("OrF"), factory.tuple("OrF")));
		bounds.boundExactly(MetaModelConstraints.sigImpliesF, factory.range(factory.tuple("ImpliesF"), factory.tuple("ImpliesF")));
		bounds.boundExactly(MetaModelConstraints.sigNotF, factory.range(factory.tuple("NotF"), factory.tuple("NotF")));
		bounds.boundExactly(MetaModelConstraints.sigOperation, operationTuple);
		
		bounds.boundExactly(BooleanExpression.TRUE, factory.range(factory.tuple("True"), factory.tuple("True")));
		bounds.boundExactly(BooleanExpression.FALSE, factory.range(factory.tuple("False"), factory.tuple("False")));
		bounds.boundExactly(BooleanExpression.BOOL, booleanTuple);
		
		bounds.boundExactly(MetaModelConstraints.sigFeatureModel, fmTuple);
		bounds.boundExactly(MetaModelConstraints.sigName, nameTuple);
		bounds.boundExactly(MetaModelConstraints.sigNameF, nameFTuple);
		bounds.boundExactly(MetaModelConstraints.sigForm, formTuple);
		bounds.boundExactly(MetaModelConstraints.sigFormula, formulaTuple);
		bounds.boundExactly(MetaModelConstraints.sigRelation, relationTuple);
		bounds.boundExactly(MetaModelConstraints.sigConfiguration, configurationTuple);
		
		bounds.bound(MetaModelConstraints.rFeatures, fmTuple.product(nameTuple));
		bounds.bound(MetaModelConstraints.rRoot, fmTuple.product(nameTuple));
		bounds.bound(MetaModelConstraints.rRelations, fmTuple.product(relationTuple));
		bounds.bound(MetaModelConstraints.rFormulas, fmTuple.product(formulaTuple));
		bounds.bound(MetaModelConstraints.rParent, relationTuple.product(nameTuple));
		bounds.bound(MetaModelConstraints.rChild, relationTuple.product(nameTuple));
		bounds.bound(MetaModelConstraints.rType, relationTuple.product(typeTuple));
		bounds.bound(MetaModelConstraints.rMin, relationTuple.product(intTuple));
		bounds.bound(MetaModelConstraints.rMax, relationTuple.product(intTuple));
		bounds.bound(MetaModelConstraints.rSatisfy, formulaTuple.product(configurationTuple).product(booleanTuple));
		bounds.bound(MetaModelConstraints.rWelltyped, formulaTuple.product(fmTuple).product(booleanTuple));
		bounds.bound(MetaModelConstraints.rName, nameFTuple.product(nameTuple));
		bounds.bound(MetaModelConstraints.rLeft, formTuple.product(formulaTuple));
		bounds.bound(MetaModelConstraints.rRight, formTuple.product(formulaTuple));
		bounds.bound(MetaModelConstraints.rOp, formTuple.product(operationTuple));
		bounds.bound(MetaModelConstraints.rValue, configurationTuple.product(nameTuple));
		bounds.bound(MetaModelConstraints.rCard, nameTuple.product(intTuple));
		bounds.bound(MetaModelConstraints.rSize, nameFTuple.product(intTuple));
		
		for (int i = 0; i < 20; ++i) {
			bounds.boundExactly(i, factory.setOf(Integer.valueOf(i)));
		}
		
		return bounds;
	}
	
	public boolean check() {
		final Solver solver = new Solver();
		solver.options().setSolver(SATFactory.MiniSat);
		final Solution solution = solver.solve(getFormulas(), bounds());
		if (solution.unsat()) {
			System.out.println("unsat");
			return false;
		} else {
			System.out.println(solution);
			Instance instance = solution.instance();
			
			//get Formulas
			List<String> allFormulas = new LinkedList<String>();
			TupleSet tupleSet = instance.tuples(MetaModelConstraints.rFormulas);
			Iterator<Tuple> iterator = tupleSet.iterator();
			while (iterator.hasNext()) {
				Tuple tuple = iterator.next();
				allFormulas.add(tuple.atom(1).toString());
			}
			
			//get Valid Configuration
			List<String> config = new LinkedList<String>();
			tupleSet = instance.tuples(MetaModelConstraints.rSatisfy);
			iterator = tupleSet.iterator();
			Map<String, LinkedList<String>> configSat = new HashMap<String, LinkedList<String>>();
			while (iterator.hasNext()) {
				Tuple tuple = iterator.next();
				if (tuple.atom(2).equals(BooleanExpression.TRUE.toString() )
						&& allFormulas.contains(tuple.atom(0).toString())) {
					if (configSat.containsKey(tuple.atom(1))) {
						configSat.get(tuple.atom(1).toString()).add(tuple.atom(0).toString());
					} else {
						LinkedList<String> list = new LinkedList<String>();
						list.add(tuple.atom(0).toString());
						configSat.put(tuple.atom(1).toString(), list);
					}
				}
			}
			
			for (String key: configSat.keySet()) {
				boolean flag = true;
				for (String form : allFormulas) {
					if (!configSat.get(key).remove(form)) {
						flag = false;
						break;
					}
				}
				
				if (configSat.get(key).size() == 0 && flag) {
					config.add(key);
				}
			}
			
			Map<String, List<String>> configMap = new HashMap<String, List<String>>();
			tupleSet = instance.tuples(MetaModelConstraints.rValue);
			iterator = tupleSet.iterator();
			while (iterator.hasNext()) {
				Tuple tuple = iterator.next();
				String configName = tuple.atom(0).toString();
				String valueName = tuple.atom(1).toString();
				if (config.contains(configName)) {
					if (configMap.containsKey(configName)) {
						configMap.get(configName).add(getFeatureName(valueName));
					} else {
						LinkedList<String> list = new LinkedList<String>();
						list.add(getFeatureName(valueName));
						configMap.put(configName, list);
					}
				}
			}
			
			for (String key: configMap.keySet()) {
//				System.out.println(key + "\t" + configMap.get(key));
				if (!configMap.get(key).contains(rootFeature.getName())) {
					configMap.get(key).add(0, rootFeature.getName());
				}
				
				String[] featureSet = new String[configMap.get(key).size()];
				configMap.get(key).toArray(featureSet);
				HerokuConsist consist = new HerokuConsist(featureSet);
				if (consist.check()) {
					System.out.println(configMap.get(key));
				}
			}
			
			return true;
		}
	}
	
	private String getFeatureName(String index) {
		for (Map.Entry<Feature, Relation> entry : signMap.entrySet()) {
			Feature feature = entry.getKey();
			Relation relation = entry.getValue();
			
			if (relation.toString().equals(index)) {
				return feature.getName();
			}
		}
		
		return null;
	}
	
	//TODO tradeoff
	protected void searchSimilarConfig(Expression instance, Expression important, int diff, int size, int value) {
		formulas.add(metamodel.wellFormed(fm1));
		formulas.add(metamodel.semantics(fm1, instance, important, diff, size, value).count()
				.gte(IntConstant.constant(7)));
	}
}
