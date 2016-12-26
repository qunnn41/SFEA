package moon.nju.edu.cn.sfea.consistency;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

public class PlatformConstraints {
	protected List<Formula> formulas = new LinkedList<Formula>();
	private FeatureModel cloudFeatureModel;
	protected Feature rootFeature;
	private List<Constraints> constraints = new LinkedList<Constraints>();
	
	private Relation fm1;
	
	protected Map<Feature, Relation> signMap = new LinkedHashMap<Feature, Relation>();
	private List<Relation> signList = new LinkedList<Relation>(); 
	private Map<Relation, String> relationNameMap = new LinkedHashMap<Relation, String>();
	private int mSignIndex = 0;
	
	public PlatformConstraints(String file) {
		loadModel(file);
	}
	
	public Formula getFormulas() {
		Formula result = FMConstraints.getFormulas();
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
		
		fm1 = Relation.unary("fm1");
		Expression features = null;
		for (Relation signRelation: signList) {
			formulas.add(signRelation.one());
			features = features == null ? signRelation : features.union(signRelation);
		}
		
		formulas.add(fm1.join(FMConstraints.rFeatures).eq(features));
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
			
			formulas.add(relation.join(FMConstraints.rCard).count().eq(IntConstant.constant(1)));
			formulas.add(relation.join(FMConstraints.rCard).sum().eq(IntConstant.constant(1)));
			
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
	
	protected void semantics(Expression conf) {
		formulas.add(FMConstraints.root(signMap.get(rootFeature), conf));
		formulas.add(conf.in(fm1.join(FMConstraints.rFeatures)));
		
		this.loadRelation(conf);
		this.loadFormula(conf);
	}
	
	private void loadRelation(Expression conf) {
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
				
				formulas.add(FMConstraints.xorFeature(signMap.get(feature), child, conf, min, max));
			} else if (feature instanceof OrFeature) {
				OrFeature orFeature = (OrFeature) feature;
				Expression child = null;
				for (Feature subFeature: orFeature.getVariants()) {
					queue.add(subFeature);
					child = child == null ? signMap.get(subFeature) : child.union(signMap.get(subFeature));
				}
				
				int min = orFeature.getGroupCardinality().getMin();
				int max = orFeature.getGroupCardinality().getMax();
				
				formulas.add(FMConstraints.orFeature(signMap.get(feature), child, conf, min, max));
			} else {
				for (Feature subFeature: feature.getSubFeatures()) {
					queue.add(subFeature);
					int min = subFeature.getFeatureCardinality().getMin();
					int max = subFeature.getFeatureCardinality().getMax();
					
					if (min == 1 && max == 1) {
						formulas.add(FMConstraints.mandatoryConstraints(signMap.get(feature), signMap.get(subFeature), conf));
					} else if (min == 0 && max == 1){
						formulas.add(FMConstraints.optionalConstraints(signMap.get(feature), signMap.get(subFeature), conf));
					} else {
						formulas.add(FMConstraints.optionalConstraints(signMap.get(feature), signMap.get(subFeature), conf));
					}
				}
			}
		}
	}
	
	private void loadFormula(Expression conf) {
		for (Constraints constraint : constraints) {
			if (constraint instanceof BooleanConstraints) {
				BooleanConstraints implieConstraints = (BooleanConstraints) constraint;
				Feature fromFeature = implieConstraints.getFrom();
				Feature toFeature = implieConstraints.getTo();
				
				formulas.add((signMap.get(fromFeature).in(conf)).implies(signMap.get(toFeature).in(conf)));
			} else if (constraint instanceof CardExConstraint) {
				CardExConstraint cardExConstraint = (CardExConstraint) constraint;
				Operation action = cardExConstraint.getAction();
				List<Operation> conditionList = cardExConstraint.getCondition();
				Operation left_condition = conditionList.get(0);
				Operation right_condition = conditionList.get(1);
				
				Feature leftFeature = left_condition.getFeature();
				Feature rightFeature = right_condition.getFeature();
				Feature actionFeature = action.getFeature();
				
				Formula left = signMap.get(leftFeature).in(conf)
						.and(IntConstant.constant(left_condition.getValue()).lte(signMap.get(leftFeature).join(FMConstraints.rCard).sum()));
				Formula right = signMap.get(rightFeature).in(conf)
						.and(IntConstant.constant(right_condition.getValue()).lte(signMap.get(rightFeature).join(FMConstraints.rCard).sum()));;
				Formula result = signMap.get(actionFeature).in(conf)
						.and(IntConstant.constant(action.getValue()).lte(signMap.get(actionFeature).join(FMConstraints.rCard).sum()));;
				
				if (cardExConstraint.getOperator() instanceof AndOperator) {
					formulas.add((left.and(right)).implies(result));
				} else {
					formulas.add((left.or(right)).implies(result));
				}
			}
		}
	}
	
	private Bounds bounds() {
		final List<Object> atoms = new LinkedList<Object>();
		
		atoms.add("fm1");
		for (Relation relation: signList) {
			atoms.add(relationNameMap.get(relation));
		}
		
		for (int i = 0; i < 10; ++i) {
			atoms.add(Integer.valueOf(i));
		}
		
		final Universe universe = new Universe(atoms);
		final TupleFactory factory = universe.factory();
		final Bounds bounds = new Bounds(universe);
		final TupleSet fmTuple = factory.range(factory.tuple("fm1"), factory.tuple("fm1"));
		final TupleSet nameTuple = factory.range(factory.tuple("s1"), factory.tuple("s" + mSignIndex));
		final TupleSet intTuple = factory.range(factory.tuple(Integer.valueOf(0)), factory.tuple(Integer.valueOf(9)));
		
		for (Relation relation: signList) {
			Tuple tuple = factory.tuple(relationNameMap.get(relation));
			bounds.boundExactly(relation, factory.range(tuple, tuple));
		}
		
		bounds.boundExactly(fm1, factory.range(factory.tuple("fm1"), factory.tuple("fm1")));
		bounds.boundExactly(FMConstraints.sigFM, fmTuple);
		bounds.boundExactly(FMConstraints.sigName, nameTuple);
		bounds.boundExactly(FMConstraints.rFeatures, fmTuple.product(nameTuple));
		bounds.bound(FMConstraints.rCard, nameTuple.product(intTuple));
		
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
//			System.out.println("unsat");
			return false;
		} else {
//			System.out.println(solution);
		}
		
		return true;
	}
}
