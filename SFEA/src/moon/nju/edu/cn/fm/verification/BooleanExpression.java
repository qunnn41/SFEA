package moon.nju.edu.cn.fm.verification;

import kodkod.ast.Expression;
import kodkod.ast.Formula;
import kodkod.ast.Relation;

public class BooleanExpression {
	public static final Expression BOOL = Relation.unary("Bool");
	public static final Expression TRUE = Relation.unary("True");
	public static final Expression FALSE = Relation.unary("False");
	
	public static Expression And(Expression expression1, Expression expression2) {
		Formula f1 = (expression1.union(expression2)).in(TRUE);
		return f1.thenElse(TRUE, FALSE);
	}
	
	public static Expression Or(Expression expression1, Expression expression2) {
		Formula f1 = TRUE.in(expression1.union(expression2));
		return f1.thenElse(TRUE, FALSE);
	}
	
	public static Expression Not(Expression expression) {
		Formula f1 = TRUE.eq(expression);
		return f1.thenElse(FALSE, TRUE);
	}
}
