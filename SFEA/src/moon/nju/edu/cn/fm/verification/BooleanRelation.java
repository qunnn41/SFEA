package moon.nju.edu.cn.fm.verification;

import kodkod.ast.Relation;

public class BooleanRelation {
	public static final Relation BOOL = Relation.unary("Bool");
	public static final Relation TRUE = Relation.unary("True");
	public static final Relation FALSE = Relation.unary("False");
	
	public static Relation And(Relation...relations) {
		for (Relation relation: relations) {
			if (relation == FALSE) {
				return FALSE;
			}
		}
		
		return TRUE;
	}
	
	public static Relation Or(Relation...relations) {
		for (Relation relation: relations) {
			if (relation == TRUE) {
				return TRUE;
			}
		}
		
		return FALSE;
	}
	
	public static Relation Not(Relation relation) {
		return relation == TRUE ? FALSE : TRUE;
	}
}
