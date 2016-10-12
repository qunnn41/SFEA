package moon.nju.edu.cn.fm.verification;

import java.util.LinkedList;
import java.util.List;

import kodkod.ast.Expression;
import kodkod.ast.Formula;
import kodkod.ast.IntConstant;
import kodkod.ast.Relation;
import kodkod.engine.Solution;
import kodkod.engine.Solver;
import kodkod.engine.satlab.SATFactory;
import kodkod.instance.Bounds;
import kodkod.instance.TupleFactory;
import kodkod.instance.TupleSet;
import kodkod.instance.Universe;

public class CloudVerification {
	private List<Formula> formulas = new LinkedList<Formula>();
	private FM_MM_Constraints basic;
	
	private Relation cloud;
	private Relation framework;
	private Relation language;
	private Relation rails;
	private Relation spring;
	private Relation java;
	private Relation python;
	
	private Relation fm1;
	private Relation r1;
	private Relation r2;
	private Relation r3;
	private Relation r4;
	
	private Relation f1;
	private Relation f2;
	private Relation f3;
	
	private Relation config1;
	
	public CloudVerification() {
		basic = new FM_MM_Constraints();
		init();
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
		atoms.add("r4");
		atoms.add("config1");
		
		for (int i = 0; i < 16; ++i) {
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
		final TupleSet configurationTuple = factory.range(factory.tuple("Configuration0"), factory.tuple("Configuration15"));
		final TupleSet intTuple = factory.range(factory.tuple(Integer.valueOf(0)), factory.tuple(Integer.valueOf(9)));
		final TupleSet booleanTuple = factory.range(factory.tuple("True"), factory.tuple("False"));
		
		bounds.boundExactly(cloud, factory.range(factory.tuple("cloud"), factory.tuple("cloud")));
		bounds.boundExactly(framework, factory.range(factory.tuple("framework"), factory.tuple("framework")));
		bounds.boundExactly(language, factory.range(factory.tuple("language"), factory.tuple("language")));
		bounds.boundExactly(rails, factory.range(factory.tuple("rails"), factory.tuple("rails")));
		bounds.boundExactly(spring, factory.range(factory.tuple("spring"), factory.tuple("spring")));
		bounds.boundExactly(java, factory.range(factory.tuple("java"), factory.tuple("java")));
		bounds.boundExactly(python, factory.range(factory.tuple("python"), factory.tuple("python")));
		
		final TupleSet fmTuple = factory.range(factory.tuple("fm1"), factory.tuple("fm1"));
		final TupleSet nameTuple = factory.range(factory.tuple("cloud"), factory.tuple("python"));
		final TupleSet nameFTuple = factory.range(factory.tuple("f2"), factory.tuple("f3"));
		final TupleSet formTuple = factory.range(factory.tuple("f1"), factory.tuple("f1"));
		final TupleSet formulaTuple = factory.range(factory.tuple("f1"), factory.tuple("f3"));
		final TupleSet relationTuple = factory.range(factory.tuple("r1"), factory.tuple("r4"));
		
		bounds.boundExactly(fm1, factory.range(factory.tuple("fm1"), factory.tuple("fm1")));
		bounds.boundExactly(f1, factory.range(factory.tuple("f1"), factory.tuple("f1")));
		bounds.boundExactly(f2, factory.range(factory.tuple("f2"), factory.tuple("f2")));
		bounds.boundExactly(f3, factory.range(factory.tuple("f3"), factory.tuple("f3")));
		bounds.boundExactly(r1, factory.range(factory.tuple("r1"), factory.tuple("r1")));
		bounds.boundExactly(r2, factory.range(factory.tuple("r2"), factory.tuple("r2")));
		bounds.boundExactly(r3, factory.range(factory.tuple("r3"), factory.tuple("r3")));
		bounds.boundExactly(r4, factory.range(factory.tuple("r4"), factory.tuple("r4")));
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
	
	
	public void createInstance() {
		config1 = Relation.unary("Config1");
		formulas.add(config1.one());
		formulas.add(config1.join(FM_MM_Constraints.rValue).eq(Expression.union(cloud, language, framework, rails)));
	}
	
	public void validConfiguration() {
		formulas.add(basic.wellFormedFeatureModel(fm1));
		formulas.add(config1.in(basic.semantics(fm1)));
	}
	
	public static void main(String[] args) {
		CloudVerification demo = new CloudVerification();
		demo.createInstance();
		demo.validConfiguration();
		demo.check();
	}
}
