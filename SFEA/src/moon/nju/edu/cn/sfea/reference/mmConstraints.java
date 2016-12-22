package moon.nju.edu.cn.sfea.reference;

import java.util.LinkedList;
import java.util.List;

import kodkod.ast.Expression;
import kodkod.ast.Formula;
import kodkod.ast.IntConstant;
import kodkod.ast.Relation;
import kodkod.ast.Variable;
import kodkod.engine.Solution;
import kodkod.engine.Solver;
import kodkod.engine.satlab.SATFactory;
import kodkod.instance.Bounds;
import kodkod.instance.TupleFactory;
import kodkod.instance.TupleSet;
import kodkod.instance.Universe;

public class mmConstraints {
	public static final Relation sigFM, sigName, rFeatures;
	private List<Formula> formulas = new LinkedList<Formula>();
	
	public mmConstraints() {
		formulas.add(this.setupDeclarations());
	}
	
	static {
		sigFM = Relation.unary("FM");
		sigName = Relation.unary("Name");
		rFeatures = Relation.binary("features");
	}
	
	private Formula setupDeclarations() {
		return rFeatures.in(sigFM.product(sigName));
	}
	
	private Formula optionalConstraints(Expression A, Expression B, Expression conf) {
		return B.in(conf).implies(A.in(conf));
	}
	
	private Formula mandatoryConstraints(Expression A, Expression B, Expression conf) {
		return A.in(conf).iff(B.in(conf));
	}
	
	private Formula root(Expression A, Expression conf) {
		return A.in(conf);
	}
	
	private Formula orFeature(Expression A, Expression children, Expression conf) {
		Variable c = Variable.unary("c");
		Formula f1 = c.in(conf).forSome(c.oneOf(children));
		Formula f2 = f1.iff(A.in(conf));
		Formula f3 = children.count().gt(IntConstant.constant(1));
		return Formula.and(f2, f3);
	}
	
	private Formula xorFeature(Expression A, Expression children, Expression conf) {
		Formula formula = children.intersection(conf).count().gt(IntConstant.constant(1));
		return Formula.and(orFeature(A, children, conf), formula);
	}
	
	private Relation mobilePhone, earphone, mp3, camera;
	private Relation M;
	
	private void init() {
		mobilePhone = Relation.unary("mobilephone");
		earphone = Relation.unary("earphone");
		mp3 = Relation.unary("mp3");
		camera = Relation.unary("camera");
		M = Relation.unary("M");
		
		formulas.add(Formula.and(mobilePhone.one(), earphone.one(), mp3.one(), camera.one()));
		formulas.add(M.one());
		
		formulas.add(semantics(Expression.union(mobilePhone, mp3, earphone)));
	}
	
	private Formula semantics(Expression conf) {
		Formula f1 = conf.in(M.join(rFeatures));
		Formula f2 = root(mobilePhone, conf);
		
		Formula f3 = optionalConstraints(mobilePhone, earphone, conf);
		Formula f4 = orFeature(mobilePhone, Expression.union(earphone, camera), conf);
		Formula f5 = earphone.in(conf).iff(mp3.in(conf));
		
		return Formula.and(f1, f2, f3, f4, f5);
	}
	
	private Bounds bounds() {
		final List<Object> atoms = new LinkedList<Object>();
		
		atoms.add("M");
		atoms.add("mobilephone");
		atoms.add("earphone");
		atoms.add("mp3");
		atoms.add("camera");
		
		final Universe universe = new Universe(atoms);
		final TupleFactory factory = universe.factory();
		final Bounds bounds = new Bounds(universe);
		final TupleSet fmTuple = factory.range(factory.tuple("M"), factory.tuple("M"));
		final TupleSet nameTuple = factory.range(factory.tuple("mobilephone"), factory.tuple("camera"));
		
		bounds.boundExactly(mobilePhone, factory.range(factory.tuple("mobilephone"), factory.tuple("mobilephone")));
		bounds.boundExactly(earphone, factory.range(factory.tuple("earphone"), factory.tuple("earphone")));
		bounds.boundExactly(mp3, factory.range(factory.tuple("mp3"), factory.tuple("mp3")));
		bounds.boundExactly(camera, factory.range(factory.tuple("camera"), factory.tuple("camera")));
		bounds.boundExactly(M, factory.range(factory.tuple("M"), factory.tuple("M")));
		bounds.boundExactly(sigFM, fmTuple);
		bounds.boundExactly(sigName, nameTuple);
		bounds.boundExactly(rFeatures, fmTuple.product(nameTuple));
		
		return bounds;
	}
	
	public Formula getFormulas() {
		Formula result = Formula.TRUE;
		for (Formula f: formulas) {
			result = result.and(f);
		}
		
		return result;
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
	
	public static void main(String[] args) {
		mmConstraints mm = new mmConstraints();
		mm.init();
		mm.check();
	}
}
