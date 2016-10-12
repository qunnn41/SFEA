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
import moon.nju.edu.cn.fm.model.ImpliesConstraints;
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
	private List<Formula> formulas = new LinkedList<Formula>();
	private FM_MM_Constraints basic;
//	
//	private Relation cloud;
//	private Relation framework;
//	private Relation language;
//	private Relation rails;
//	private Relation spring;
//	private Relation java;
//	private Relation python;
//	
//	private Relation r1;
//	private Relation r2;
//	private Relation r3;
//	private Relation r4;
//	
//	private Relation f1;
//	private Relation f2;
//	private Relation f3;
	
	private Relation fm1;
	private Relation config1;
	
	private FeatureModel cloudFeatureModel;
	private Feature rootFeature;
	private LinkedList<Constraints> constraints = new LinkedList<Constraints>();
	private LinkedList<Feature> queue = new LinkedList<Feature>();
	
	public CloudVerification() {
		basic = new FM_MM_Constraints();
//		init();
		loadModel();
	}
	
	public Formula getFormulas() {
		Formula result = Formula.TRUE;
		for (Formula f: basic.getFormulas()) {
			result = result.and(f);
		}
		
		for (Formula f: formulas) {
			result = result.and(f);
		}
		
		return result;
	}
	
	private Map<Feature, Relation> signMap = new LinkedHashMap<Feature, Relation>();
	private List<Relation> signList = new LinkedList<Relation>(); 
	private List<Relation> relationList = new LinkedList<Relation>();
	private List<Relation> formList = new LinkedList<Relation>();
	private List<Relation> nameFList = new LinkedList<Relation>();
	private Map<Relation, String> relationNameMap = new LinkedHashMap<Relation, String>();
	private int mRelationIndex = 0;
	private int mNameFIndex = 0;
	private int mFormIndex = 0;
	private int mSignIndex = 0;
	
	private void loadModel() {
		SFEAPackage.eINSTANCE.eClass();
		Resource.Factory.Registry registry = Resource.Factory.Registry.INSTANCE;
		Map<String, Object> map = registry.getExtensionToFactoryMap();
		map.put("fm", new XMIResourceFactoryImpl());
		
		ResourceSet resourceSet = new ResourceSetImpl();
		Resource resource = resourceSet.getResource(URI.createURI("feature_model/heroku.fm"), true);
		this.cloudFeatureModel = (FeatureModel) resource.getContents().get(0);
		this.rootFeature = cloudFeatureModel.getRoot();
		this.constraints.addAll(cloudFeatureModel.getConstraints());
		
		this.loadSignature();
		this.loadRelation();
		this.loadFormula();
		
		fm1 = Relation.unary("fm1");
		formulas.add(fm1.join(FM_MM_Constraints.rRoot).eq(signMap.get(rootFeature)));
		
		Expression features = null;
		for (Relation signRelation: signList) {
			formulas.add(signRelation.one());
			features = features == null ? signRelation : features.union(signRelation);
		}
		
		formulas.add(fm1.join(FM_MM_Constraints.rFeatures).eq(features));
		
		Expression relations = null;
		for (Relation relation: relationList) {
			formulas.add(relation.one());
			relations = relations == null ? relation : relations.union(relation);
		}
		
		if (relations != null) {
			formulas.add(fm1.join(FM_MM_Constraints.rRelations).eq(relations));
		}
		
		Expression forms = null;
		for (Relation form : formList) {
			formulas.add(form.one());
			forms = forms == null ? form : forms.union(form);
		}
		
		if (forms != null) {
			formulas.add(fm1.join(FM_MM_Constraints.rFormulas).eq(forms));
		}
		
		for (Relation nameF : nameFList) {
			formulas.add(nameF.one());
		}
	}
	
	private void loadSignature() {
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
				formulas.add(relation.join(FM_MM_Constraints.rType).eq(FM_MM_Constraints.sigXorFeature));
				formulas.add(relation.join(FM_MM_Constraints.rParent).eq(signMap.get(feature)));
				formulas.add(relation.join(FM_MM_Constraints.rChild).eq(child));
				formulas.add(relation.join(FM_MM_Constraints.rMin).count().eq(IntConstant.constant(1)));
				formulas.add(relation.join(FM_MM_Constraints.rMax).count().eq(IntConstant.constant(1)));
				formulas.add(relation.join(FM_MM_Constraints.rMin).sum().eq(IntConstant.constant(min)));
				formulas.add(relation.join(FM_MM_Constraints.rMax).sum().eq(IntConstant.constant(max)));

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
				formulas.add(relation.join(FM_MM_Constraints.rType).eq(FM_MM_Constraints.sigOrFeature));
				formulas.add(relation.join(FM_MM_Constraints.rParent).eq(signMap.get(feature)));
				formulas.add(relation.join(FM_MM_Constraints.rChild).eq(child));
				formulas.add(relation.join(FM_MM_Constraints.rMin).count().eq(IntConstant.constant(1)));
				formulas.add(relation.join(FM_MM_Constraints.rMax).count().eq(IntConstant.constant(1)));
				formulas.add(relation.join(FM_MM_Constraints.rMin).sum().eq(IntConstant.constant(min)));
				formulas.add(relation.join(FM_MM_Constraints.rMax).sum().eq(IntConstant.constant(max)));
				
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
						formulas.add(relation.join(FM_MM_Constraints.rType).eq(FM_MM_Constraints.sigMandatory));
					} else if (min == 0 && max == 1) {
						formulas.add(relation.join(FM_MM_Constraints.rType).eq(FM_MM_Constraints.sigOptional));
					} else {
//						System.out.println("wrong model\t" + feature.getName() + "\t" + subFeature.getName());
						formulas.add(relation.join(FM_MM_Constraints.rType).eq(FM_MM_Constraints.sigOptional));
					}
					
					formulas.add(relation.join(FM_MM_Constraints.rParent).eq(signMap.get(feature)));
					formulas.add(relation.join(FM_MM_Constraints.rChild).eq(signMap.get(subFeature)));
					formulas.add(relation.join(FM_MM_Constraints.rMin).count().eq(IntConstant.constant(0)));
					formulas.add(relation.join(FM_MM_Constraints.rMax).count().eq(IntConstant.constant(0)));
					
					relationList.add(relation);
				}
			}
		}
	}

	private void loadFormula() {
		// should use DFS to load formula
		for (Constraints constraint : constraints) {
			if (constraint instanceof ImpliesConstraints) {
				ImpliesConstraints impliesConstraint = (ImpliesConstraints) constraint;
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
				
				formulas.add(fromRelation.join(FM_MM_Constraints.rName).eq(signMap.get(fromFeature)));
				formulas.add(toRelation.join(FM_MM_Constraints.rName).eq(signMap.get(toFeature)));
				
				formulas.add(impliesRelation.join(FM_MM_Constraints.rOp).eq(FM_MM_Constraints.sigImpliesF));
				formulas.add(impliesRelation.join(FM_MM_Constraints.rLeft).eq(fromRelation));
				formulas.add(impliesRelation.join(FM_MM_Constraints.rRight).eq(toRelation));
				
				nameFList.add(fromRelation);
				nameFList.add(toRelation);
				
				formList.add(impliesRelation);
			} else {
				//TODO add other
			}
		}
	}
	
	/*
	private void init() {
		cloud = Relation.unary("cloud");
		framework = Relation.unary("framework");
		language = Relation.unary("language");
		rails = Relation.unary("rails");
		spring = Relation.unary("spring");
		java = Relation.unary("java");
		python = Relation.unary("python");
		
		fm1 = Relation.unary("fm1");
		r1 = Relation.unary("r1");
		r2 = Relation.unary("r2");
		r3 = Relation.unary("r3");
		r4 = Relation.unary("r4");
		
		f1 = Relation.unary("f1");
		f2 = Relation.unary("f2");
		f3 = Relation.unary("f3");
		
		formulas.add(Formula.and(cloud.one(), framework.one(), language.one(), rails.one(), spring.one(), java.one(), python.one()));
		formulas.add(Formula.and(fm1.one(), r1.one(), r2.one(), r3.one(), r4.one()));
		formulas.add(Formula.and(f1.one(), f2.one(), f3.one()));
		
		formulas.add(fm1.join(FM_MM_Constraints.rRoot).eq(cloud));
		formulas.add(fm1.join(FM_MM_Constraints.rFeatures).eq(Expression.union(cloud, framework, language, rails, spring, java, python)));
		formulas.add(fm1.join(FM_MM_Constraints.rRelations).eq(Expression.union(r1, r2, r3, r4)));
		formulas.add(fm1.join(FM_MM_Constraints.rFormulas).eq(f1));
		
		formulas.add(r1.join(FM_MM_Constraints.rType).eq(FM_MM_Constraints.sigOptional));
		formulas.add(r1.join(FM_MM_Constraints.rParent).eq(cloud));
		formulas.add(r1.join(FM_MM_Constraints.rChild).eq(framework));
		formulas.add(r1.join(FM_MM_Constraints.rMin).count().eq(IntConstant.constant(0)));
		formulas.add(r1.join(FM_MM_Constraints.rMax).count().eq(IntConstant.constant(0)));
		
		formulas.add(r2.join(FM_MM_Constraints.rType).eq(FM_MM_Constraints.sigMandatory));
		formulas.add(r2.join(FM_MM_Constraints.rParent).eq(cloud));
		formulas.add(r2.join(FM_MM_Constraints.rChild).eq(language));
		formulas.add(r2.join(FM_MM_Constraints.rMin).count().eq(IntConstant.constant(0)));
		formulas.add(r2.join(FM_MM_Constraints.rMax).count().eq(IntConstant.constant(0)));
		
		
		formulas.add(r3.join(FM_MM_Constraints.rType).eq(FM_MM_Constraints.sigOrFeature));
		formulas.add(r3.join(FM_MM_Constraints.rParent).eq(framework));
		formulas.add(r3.join(FM_MM_Constraints.rChild).eq(Expression.union(rails, spring)));
		formulas.add(r3.join(FM_MM_Constraints.rMin).count().eq(IntConstant.constant(1)));
		formulas.add(r3.join(FM_MM_Constraints.rMax).count().eq(IntConstant.constant(1)));
		formulas.add(r3.join(FM_MM_Constraints.rMin).sum().eq(IntConstant.constant(0)));
		formulas.add(r3.join(FM_MM_Constraints.rMax).sum().eq(IntConstant.constant(1)));
		
		formulas.add(r4.join(FM_MM_Constraints.rType).eq(FM_MM_Constraints.sigXorFeature));
		formulas.add(r4.join(FM_MM_Constraints.rParent).eq(language));
		formulas.add(r4.join(FM_MM_Constraints.rChild).eq(Expression.union(java, python)));
		formulas.add(r4.join(FM_MM_Constraints.rMin).count().eq(IntConstant.constant(1)));
		formulas.add(r4.join(FM_MM_Constraints.rMax).count().eq(IntConstant.constant(1)));
		formulas.add(r4.join(FM_MM_Constraints.rMin).sum().eq(IntConstant.constant(1)));
		formulas.add(r4.join(FM_MM_Constraints.rMax).sum().eq(IntConstant.constant(1)));
		
		formulas.add(f1.join(FM_MM_Constraints.rOp).eq(FM_MM_Constraints.sigImpliesF));
		formulas.add(f1.join(FM_MM_Constraints.rLeft).eq(f2));
		formulas.add(f1.join(FM_MM_Constraints.rRight).eq(f3));
		
		formulas.add(f2.join(FM_MM_Constraints.rName).eq(spring));
		formulas.add(f3.join(FM_MM_Constraints.rName).eq(java));
	}
	*/

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
		
		/*
		atoms.add("cloud");
		atoms.add("framework");
		atoms.add("language");
		atoms.add("rails");
		atoms.add("spring");
		atoms.add("java");
		atoms.add("python");
		atoms.add("f1");
		atoms.add("f2");
		atoms.add("f3");
		atoms.add("r1");
		atoms.add("r2");
		atoms.add("r3");
		atoms.add("r4");*/
		
		for (Relation relation: signList) {
			atoms.add(relationNameMap.get(relation));
		}
		
		for (Relation relation: relationList) {
			atoms.add(relationNameMap.get(relation));
		}
		
		for (Relation form: formList) {
			atoms.add(relationNameMap.get(form));
		}
		
		for (Relation nameF: nameFList) {
			atoms.add(relationNameMap.get(nameF));
		}
		
		atoms.add("config1");
		
		int configurationSize = Math.min(128, (int) Math.pow(2, signMap.size()) - 1);
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
		
		/*
		final TupleSet fmTuple = factory.range(factory.tuple("fm1"), factory.tuple("fm1"));
		final TupleSet nameTuple = factory.range(factory.tuple("cloud"), factory.tuple("python"));
		final TupleSet nameFTuple = factory.range(factory.tuple("f2"), factory.tuple("f3"));
		final TupleSet formTuple = factory.range(factory.tuple("f1"), factory.tuple("f1"));
		final TupleSet formulaTuple = factory.range(factory.tuple("f1"), factory.tuple("f3"));
		final TupleSet relationTuple = factory.range(factory.tuple("r1"), factory.tuple("r4"));
		
		bounds.boundExactly(cloud, factory.range(factory.tuple("cloud"), factory.tuple("cloud")));
		bounds.boundExactly(framework, factory.range(factory.tuple("framework"), factory.tuple("framework")));
		bounds.boundExactly(language, factory.range(factory.tuple("language"), factory.tuple("language")));
		bounds.boundExactly(rails, factory.range(factory.tuple("rails"), factory.tuple("rails")));
		bounds.boundExactly(spring, factory.range(factory.tuple("spring"), factory.tuple("spring")));
		bounds.boundExactly(java, factory.range(factory.tuple("java"), factory.tuple("java")));
		bounds.boundExactly(python, factory.range(factory.tuple("python"), factory.tuple("python")));
		

		bounds.boundExactly(f1, factory.range(factory.tuple("f1"), factory.tuple("f1")));
		bounds.boundExactly(f2, factory.range(factory.tuple("f2"), factory.tuple("f2")));
		bounds.boundExactly(f3, factory.range(factory.tuple("f3"), factory.tuple("f3")));
		bounds.boundExactly(r1, factory.range(factory.tuple("r1"), factory.tuple("r1")));
		bounds.boundExactly(r2, factory.range(factory.tuple("r2"), factory.tuple("r2")));
		bounds.boundExactly(r3, factory.range(factory.tuple("r3"), factory.tuple("r3")));
		bounds.boundExactly(r4, factory.range(factory.tuple("r4"), factory.tuple("r4")));
		bounds.boundExactly(fm1, factory.range(factory.tuple("fm1"), factory.tuple("fm1")));
		bounds.bound(config1, configurationTuple);*/
		
		final TupleSet fmTuple = factory.range(factory.tuple("fm1"), factory.tuple("fm1"));
		final TupleSet nameTuple = factory.range(factory.tuple("s1"), factory.tuple("s" + mSignIndex));
		final TupleSet nameFTuple = factory.range(factory.tuple("nf1"), factory.tuple("nf" + mNameFIndex));
		final TupleSet formTuple = factory.range(factory.tuple("f1"), factory.tuple("f" + mFormIndex));
		final TupleSet formulaTuple = factory.range(factory.tuple("f1"), factory.tuple("nf" + mNameFIndex));
		final TupleSet relationTuple = factory.range(factory.tuple("r1"), factory.tuple("r" + mRelationIndex));
		
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
		
		bounds.boundExactly(FM_MM_Constraints.sigOptional, factory.range(factory.tuple("Optional"), factory.tuple("Optional")));
		bounds.boundExactly(FM_MM_Constraints.sigMandatory, factory.range(factory.tuple("Mandatory"), factory.tuple("Mandatory")));
		bounds.boundExactly(FM_MM_Constraints.sigOrFeature, factory.range(factory.tuple("OrFeature"), factory.tuple("OrFeature")));
		bounds.boundExactly(FM_MM_Constraints.sigXorFeature, factory.range(factory.tuple("XorFeature"), factory.tuple("XorFeature")));
		bounds.boundExactly(FM_MM_Constraints.sigType, typeTuple);
		
		bounds.boundExactly(FM_MM_Constraints.sigAndF, factory.range(factory.tuple("AndF"), factory.tuple("AndF")));
		bounds.boundExactly(FM_MM_Constraints.sigOrF, factory.range(factory.tuple("OrF"), factory.tuple("OrF")));
		bounds.boundExactly(FM_MM_Constraints.sigImpliesF, factory.range(factory.tuple("ImpliesF"), factory.tuple("ImpliesF")));
		bounds.boundExactly(FM_MM_Constraints.sigNotF, factory.range(factory.tuple("NotF"), factory.tuple("NotF")));
		bounds.boundExactly(FM_MM_Constraints.sigOperation, operationTuple);
		
		bounds.boundExactly(BooleanExpression.TRUE, factory.range(factory.tuple("True"), factory.tuple("True")));
		bounds.boundExactly(BooleanExpression.FALSE, factory.range(factory.tuple("False"), factory.tuple("False")));
		bounds.boundExactly(BooleanExpression.BOOL, booleanTuple);
		
		bounds.boundExactly(FM_MM_Constraints.sigFeatureModel, fmTuple);
		bounds.boundExactly(FM_MM_Constraints.sigName, nameTuple);
		bounds.boundExactly(FM_MM_Constraints.sigNameF, nameFTuple);
		bounds.boundExactly(FM_MM_Constraints.sigForm, formTuple);
		bounds.boundExactly(FM_MM_Constraints.sigFormula, formulaTuple);
		bounds.boundExactly(FM_MM_Constraints.sigRelation, relationTuple);
		bounds.boundExactly(FM_MM_Constraints.sigConfiguration, configurationTuple);
		
		bounds.bound(FM_MM_Constraints.rFeatures, fmTuple.product(nameTuple));
		bounds.bound(FM_MM_Constraints.rRoot, fmTuple.product(nameTuple));
		bounds.bound(FM_MM_Constraints.rRelations, fmTuple.product(relationTuple));
		bounds.bound(FM_MM_Constraints.rFormulas, fmTuple.product(formulaTuple));
		bounds.bound(FM_MM_Constraints.rParent, relationTuple.product(nameTuple));
		bounds.bound(FM_MM_Constraints.rChild, relationTuple.product(nameTuple));
		bounds.bound(FM_MM_Constraints.rType, relationTuple.product(typeTuple));
		bounds.bound(FM_MM_Constraints.rMin, relationTuple.product(intTuple));
		bounds.bound(FM_MM_Constraints.rMax, relationTuple.product(intTuple));
		bounds.bound(FM_MM_Constraints.rSatisfy, formulaTuple.product(configurationTuple).product(booleanTuple));
		bounds.bound(FM_MM_Constraints.rWelltyped, formulaTuple.product(fmTuple).product(booleanTuple));
		bounds.bound(FM_MM_Constraints.rName, nameFTuple.product(nameTuple));
		bounds.bound(FM_MM_Constraints.rLeft, formTuple.product(formulaTuple));
		bounds.bound(FM_MM_Constraints.rRight, formTuple.product(formulaTuple));
		bounds.bound(FM_MM_Constraints.rOp, formTuple.product(operationTuple));
		bounds.bound(FM_MM_Constraints.rValue, configurationTuple.product(nameTuple));
		
		for (int i = 0; i < 10; ++i) {
			bounds.boundExactly(i, factory.setOf(Integer.valueOf(i)));
		}
		
		return bounds;
	}
	
	public void check() {
		final Solver solver = new Solver();
		solver.options().setSolver(SATFactory.MiniSat);
		final Solution solution = solver.solve(getFormulas(), bounds());
		if (solution.unsat()) {
			System.out.println("unsat");
		} else {
			System.out.println(solution);
		}
	}
	
	// mapping function might be different among feature models
	public void createInstance(String[] string) {
		config1 = Relation.unary("Config1");
		formulas.add(config1.one());
		
//		formulas.add(config1.join(FM_MM_Constraints.rValue).eq(Expression.union(cloud, language, framework, rails, java)));

		Expression featureSelection = signMap.get(rootFeature);
		for (String str : string) {
			for (Feature feature: signMap.keySet()) {
				if (str.equals(feature.getName())) {
					featureSelection = featureSelection == null ? signMap.get(feature) : featureSelection.union(signMap.get(feature));
					break;
				}
			}
		}
		
		formulas.add(config1.join(FM_MM_Constraints.rValue).eq(featureSelection));
	}
	
	public void validConfiguration() {
		formulas.add(basic.wellFormedFeatureModel(fm1));
		formulas.add(config1.in(basic.semantics(fm1)));
	}
	
	public static void main(String[] args) {
		CloudVerification demo = new CloudVerification();
		demo.createInstance(new String[] {"Language", "Spring", "EU", "Location", "Framework", "Java"});
		demo.validConfiguration();
		demo.check();
	}
}
