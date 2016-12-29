package moon.nju.edu.cn.sfea.consistency;

import java.util.LinkedList;
import java.util.List;

import kodkod.ast.Expression;
import kodkod.ast.Formula;
import kodkod.ast.IntConstant;
import kodkod.ast.IntExpression;
import kodkod.ast.Relation;
import kodkod.ast.Variable;

public class FMConstraints {
	protected static final Relation sigFM, sigName, rFeatures, rCard;
	public static final List<Formula> formulas = new LinkedList<Formula>();
	
	public FMConstraints() {
		formulas.add(this.setupDeclarations());
	}
	
	static {
		sigFM = Relation.unary("FM");
		sigName = Relation.unary("Name");
		rFeatures = Relation.binary("features");
		rCard = Relation.binary("card");
	}
	
	/**
	 * sig FM {
	 * 		features: set Name
	 * }
	 * 
	 * sig Name {
	 * 		card: Int
	 * }
	 */
	private Formula setupDeclarations() {
		Formula f1 = rCard.in(sigName.product(Expression.INTS));
		Formula f2 = rFeatures.in(sigFM.product(sigName));
		
		return Formula.and(f1, f2);
	}
	
	/**
	 * pred optional(A,B: Name, conf: set Name) {
	 * 		B in conf implies A in conf
	 * }
	 */
	public static Formula optionalConstraints(Expression A, Expression B, Expression conf) {
		return B.in(conf).implies(A.in(conf));
	}
	
	
	/**
	 * pred mandatory(A, B: Name, conf: set Name) {
	 * 		A in conf <=> B in conf
	 * }
	 */
	public static Formula mandatoryConstraints(Expression A, Expression B, Expression conf) {
		return A.in(conf).iff(B.in(conf));
	}
	
	/**
	 * pred root(A: Name, conf: set Name) {
	 * 		A in conf
	 * }
	 */
	public static Formula root(Expression A, Expression conf) {
		return A.in(conf);
	}
	
	/**
	 * pred orFeature(A: Name, children: set Name, conf: set Name) {
	 * 		A in conf => (some c: children | c in conf and #children & conf >= min && children & conf <= max)
	 * }
	 */
	public static Formula orFeature(Expression A, Expression children, Expression conf, int min, int max) {
		Variable c = Variable.unary("c");
		Formula f1 = c.in(conf).forSome(c.oneOf(children));
		
		IntExpression e = (children.intersection(conf)).count();
		Formula f2 = e.gte(IntConstant.constant(min)).and(e.lte(IntConstant.constant(max)));
		Formula f3 = A.in(conf).implies(f1.and(f2));

		Formula f4 = children.count().gt(IntConstant.constant(1));
		return Formula.and(f3, f4);
	}
	
	/**
	 * pred alternative(A: Name, children: set Name, conf: set Name) {
	 * 		A in conf => some c: children | c in conf and (#children & conf >= min && children & conf <= max)
	 * }
	 */
	public static Formula xorFeature(Expression A, Expression children, Expression conf, int min, int max) {
		return orFeature(A, children, conf, min, max);
	}
	
	public static Formula getFormulas() {
		Formula result = Formula.TRUE;
		for (Formula f: formulas) {
			result = result.and(f);
		}
		
		return result;
	}
}
