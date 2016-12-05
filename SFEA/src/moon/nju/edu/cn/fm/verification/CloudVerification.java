package moon.nju.edu.cn.fm.verification;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import moon.nju.edu.cn.fm.model.Constraints;
import moon.nju.edu.cn.fm.model.Feature;
import moon.nju.edu.cn.fm.model.FeatureModel;
import moon.nju.edu.cn.fm.model.OrFeature;
import moon.nju.edu.cn.fm.model.SFEAPackage;
import moon.nju.edu.cn.fm.model.XorFeature;

import kodkod.ast.Expression;
import kodkod.ast.Formula;
import kodkod.ast.IntConstant;
import kodkod.ast.Relation;
import kodkod.engine.Solution;
import kodkod.engine.Solver;
import kodkod.engine.satlab.SATFactory;
import kodkod.instance.Bounds;
import kodkod.instance.Tuple;
import kodkod.instance.TupleFactory;
import kodkod.instance.TupleSet;
import kodkod.instance.Universe;

public class CloudVerification {
	protected List<Formula> formulas = new LinkedList<Formula>();
	private MetaModelConstraints metamodel;
	
	private Relation fm1;
	protected Relation config1;
	
	private FeatureModel cloudFeatureModel;
	protected Feature rootFeature;
	private LinkedList<Constraints> constraints = new LinkedList<Constraints>();
	
	protected Map<Feature, Relation> signMap = new LinkedHashMap<Feature, Relation>();
	private List<Relation> signList = new LinkedList<Relation>(); 
	private List<Relation> relationList = new LinkedList<Relation>();
	private List<Relation> formList = new LinkedList<Relation>();
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
			forms = forms == null ? form : forms.union(form);
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
//		for (Constraints constraint : constraints) {
//			if (constraint instanceof ImpliesConstraints) {
//				ImpliesConstraints impliesConstraint = (ImpliesConstraints) constraint;
//				Feature fromFeature = impliesConstraint.getFrom();
//				Feature toFeature = impliesConstraint.getTo();
//				
//				String impliesName = "f" + (++ mFormIndex);
//				Relation impliesRelation = Relation.unary(impliesName);
//				relationNameMap.put(impliesRelation, impliesName);
//				
//				String fromName = "nf" + (++ mNameFIndex);
//				Relation fromRelation = Relation.unary(fromName);
//				relationNameMap.put(fromRelation, fromName);
//				
//				String toName = "nf" + (++ mNameFIndex);
//				Relation toRelation = Relation.unary(toName);
//				relationNameMap.put(toRelation, toName);
//				
//				formulas.add(fromRelation.join(MetaModelConstraints.rName).eq(signMap.get(fromFeature)));
//				formulas.add(toRelation.join(MetaModelConstraints.rName).eq(signMap.get(toFeature)));
//				
//				formulas.add(impliesRelation.join(MetaModelConstraints.rOp).eq(MetaModelConstraints.sigImpliesF));
//				formulas.add(impliesRelation.join(MetaModelConstraints.rLeft).eq(fromRelation));
//				formulas.add(impliesRelation.join(MetaModelConstraints.rRight).eq(toRelation));
//				
//				nameFList.add(fromRelation);
//				nameFList.add(toRelation);
//				
//				formList.add(impliesRelation);
//			}
//		}
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
		
		atoms.add("config1");
		
		int configurationSize = Math.min(63, (int) Math.pow(2, signMap.size()) - 1);
		for (int i = 0; i <= configurationSize; ++i) {
			atoms.add("Configuration" + i);
		}
		
		for (int i = 0; i < 10; ++i) {
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
		bounds.bound(config1, configurationTuple);
		
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
		
		for (int i = 0; i < 10; ++i) {
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
			return true;
		}
	}
	
	protected void validConfiguration() {
		formulas.add(metamodel.wellFormed(fm1));
		formulas.add(config1.in(metamodel.semantics(fm1)));
	}
}
