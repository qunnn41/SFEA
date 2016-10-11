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

public class DemoVerification {
	private List<Formula> formulas = new LinkedList<Formula>();
	private FM_MM_Constraints basic;
	
	private Relation mobilePhone;
	private Relation earphone;
	private Relation mp3;
	private Relation camera;
	
	private Relation fm1;
	private Relation r1;
	private Relation r2;
	
	private Relation f1;
	private Relation f2;
	private Relation f3;
	private Relation f4;
	
	private Relation config1;
	
	public DemoVerification() {
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
		mobilePhone = Relation.unary("mobilephone");
		earphone = Relation.unary("earphone");
		mp3 = Relation.unary("mp3");
		camera = Relation.unary("camera");
		
		fm1 = Relation.unary("fm1");
		r1 = Relation.unary("r1");
		r2 = Relation.unary("r2");
		
		f1 = Relation.unary("f1");
		f2 = Relation.unary("f2");
		f3 = Relation.unary("f3");
		f4 = Relation.unary("f4");
		
		formulas.add(Formula.and(mobilePhone.one(), earphone.one(), mp3.one(), camera.one()));
		formulas.add(Formula.and(fm1.one(), r1.one(), r2.one()));
		formulas.add(Formula.and(f1.one(), f2.one(), f3.one(), f4.one()));
		
		formulas.add(fm1.join(FM_MM_Constraints.rRoot).eq(mobilePhone));
		formulas.add(fm1.join(FM_MM_Constraints.rFeatures).eq(Expression.union(mobilePhone, earphone, mp3, camera)));
		formulas.add(fm1.join(FM_MM_Constraints.rRelations).eq(Expression.union(r1, r2)));
		formulas.add(fm1.join(FM_MM_Constraints.rFormulas).eq(Expression.union(f1, f4)));
		
		formulas.add(r1.join(FM_MM_Constraints.rType).eq(FM_MM_Constraints.sigOptional));
		formulas.add(r1.join(FM_MM_Constraints.rParent).eq(mobilePhone));
		formulas.add(r1.join(FM_MM_Constraints.rChild).eq(earphone));
		formulas.add(r1.join(FM_MM_Constraints.rMin).count().eq(IntConstant.constant(0)));
		formulas.add(r1.join(FM_MM_Constraints.rMax).count().eq(IntConstant.constant(0)));
		
		formulas.add(r2.join(FM_MM_Constraints.rType).eq(FM_MM_Constraints.sigOrFeature));
		formulas.add(r2.join(FM_MM_Constraints.rParent).eq(mobilePhone));
		formulas.add(r2.join(FM_MM_Constraints.rChild).eq(Expression.union(mp3, camera)));
		formulas.add(r2.join(FM_MM_Constraints.rMin).count().eq(IntConstant.constant(1)));
		formulas.add(r2.join(FM_MM_Constraints.rMax).count().eq(IntConstant.constant(1)));
		formulas.add(r2.join(FM_MM_Constraints.rMin).sum().eq(IntConstant.constant(1)));
		formulas.add(r2.join(FM_MM_Constraints.rMax).sum().eq(IntConstant.constant(2)));
		
		formulas.add(f1.join(FM_MM_Constraints.rOp).eq(FM_MM_Constraints.sigImpliesF));
		formulas.add(f1.join(FM_MM_Constraints.rLeft).eq(f2));
		formulas.add(f1.join(FM_MM_Constraints.rRight).eq(f3));
		
		formulas.add(f2.join(FM_MM_Constraints.rName).eq(earphone));
		formulas.add(f3.join(FM_MM_Constraints.rName).eq(mp3));
		
		formulas.add(f4.join(FM_MM_Constraints.rOp).eq(FM_MM_Constraints.sigImpliesF));
		formulas.add(f4.join(FM_MM_Constraints.rLeft).eq(f3));
		formulas.add(f4.join(FM_MM_Constraints.rRight).eq(f2));
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
		atoms.add("mobilephone");
		atoms.add("earphone");
		atoms.add("mp3");
		atoms.add("camera");
		atoms.add("f1");
		atoms.add("f4");
		atoms.add("f2");
		atoms.add("f3");
		atoms.add("r1");
		atoms.add("r2");
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
		
		final TupleSet fmTuple = factory.range(factory.tuple("fm1"), factory.tuple("fm1"));
		final TupleSet nameTuple = factory.range(factory.tuple("mobilephone"), factory.tuple("camera"));
		final TupleSet nameFTuple = factory.range(factory.tuple("f2"), factory.tuple("f3"));
		final TupleSet formTuple = factory.range(factory.tuple("f1"), factory.tuple("f4"));
		final TupleSet formulaTuple = factory.range(factory.tuple("f1"), factory.tuple("f3"));
		final TupleSet relationTuple = factory.range(factory.tuple("r1"), factory.tuple("r2"));
		final TupleSet typeTuple = factory.range(factory.tuple("Optional"), factory.tuple("XorFeature"));
		final TupleSet operationTuple = factory.range(factory.tuple("AndF"), factory.tuple("NotF"));
		final TupleSet configurationTuple = factory.range(factory.tuple("Configuration0"), factory.tuple("Configuration15"));
		final TupleSet intTuple = factory.range(factory.tuple(Integer.valueOf(0)), factory.tuple(Integer.valueOf(9)));
		final TupleSet booleanTuple = factory.range(factory.tuple("True"), factory.tuple("False"));
		
		bounds.boundExactly(mobilePhone, factory.range(factory.tuple("mobilephone"), factory.tuple("mobilephone")));
		bounds.boundExactly(earphone, factory.range(factory.tuple("earphone"), factory.tuple("earphone")));
		bounds.boundExactly(mp3, factory.range(factory.tuple("mp3"), factory.tuple("mp3")));
		bounds.boundExactly(camera, factory.range(factory.tuple("camera"), factory.tuple("camera")));
		bounds.boundExactly(fm1, factory.range(factory.tuple("fm1"), factory.tuple("fm1")));
		
		bounds.boundExactly(f1, factory.range(factory.tuple("f1"), factory.tuple("f1")));
		bounds.boundExactly(f4, factory.range(factory.tuple("f4"), factory.tuple("f4")));
		bounds.boundExactly(f2, factory.range(factory.tuple("f2"), factory.tuple("f2")));
		bounds.boundExactly(f3, factory.range(factory.tuple("f3"), factory.tuple("f3")));
		bounds.boundExactly(r1, factory.range(factory.tuple("r1"), factory.tuple("r1")));
		bounds.boundExactly(r2, factory.range(factory.tuple("r2"), factory.tuple("r2")));
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
		formulas.add(config1.join(FM_MM_Constraints.rValue).eq(Expression.union(mobilePhone, camera)));
	}
	
	public void validConfiguration() {
		formulas.add(basic.wellFormedFeatureModel(fm1));
		formulas.add(config1.in(basic.semantics(fm1)));
	}
	
	public static void main(String[] args) {
		DemoVerification demo = new DemoVerification();
		demo.createInstance();
		demo.validConfiguration();
		demo.check();
	}
}
