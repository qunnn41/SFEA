package moon.nju.edu.cn.fm.verification;

import kodkod.ast.Expression;
import kodkod.ast.Formula;
import kodkod.ast.IntConstant;
import kodkod.ast.Relation;
import kodkod.ast.Variable;

public class FeatureModelVerification {
	// Signature
	private Relation sigFeatureModel, sigName, sigRelation, sigType, sigOptional, sigMandatory, sigOrFeature, sigXorFeature;
	private Relation sigFormula, sigNameF, sigForm, sigOperation, sigAndF, sigOrF, sigImpliesF, sigNotF;
	private Relation sigConfiguration;
	
	// Relation
	private Relation rFeatures, rRoot, rRelations, rFormulas, rParent, rChild, rType, rMin, rMax;
	private Relation rSatisfy, rWelltyped, rName, rLeft, rRight, rOp, rValue;
	
	public FeatureModelVerification() {
		this.setupSignatures();
		System.out.println(this.setupSigFeatureModelDeclarations());
		System.out.println(this.setupSigRelationDeclarations());
		System.out.println(this.setupSigFormulaDeclarations());
		System.out.println(this.setupSigConfigurationDeclarations());
		System.out.println(this.wellFormedFeatureModel(Variable.unary("fm")));
	}
	
	private void setupSignatures() {
		this.sigFeatureModel = Relation.unary("FeatureModel");
		
		this.sigName = Relation.unary("Name");
		
		this.sigRelation = Relation.unary("Relation");
		this.sigType = Relation.unary("Type");
		this.sigOptional = Relation.unary("Optional");
		this.sigMandatory = Relation.unary("Mandatory");
		this.sigOrFeature = Relation.unary("OrFeature");
		this.sigXorFeature = Relation.unary("XorFeature");
		
		this.sigFormula = Relation.unary("Formula");
		this.sigNameF = Relation.unary("NameF");
		this.sigForm = Relation.unary("Form");
		this.sigOperation = Relation.unary("Operation");
		this.sigAndF = Relation.unary("AndF");
		this.sigOrF = Relation.unary("OrF");
		this.sigImpliesF = Relation.unary("ImpliesF");
		this.sigNotF = Relation.unary("NotF");
		
		this.sigConfiguration = Relation.unary("Configuration");
	}
	
	private Formula setupSigFeatureModelDeclarations() {
		this.rFeatures = Relation.binary("features");
		this.rRoot = Relation.binary("root");
		this.rRelations = Relation.binary("relations");
		this.rFormulas = Relation.binary("formulas");
		
		final Formula f1 = rFeatures.in(sigFeatureModel.product(sigName));
		final Formula f2 = rRoot.function(sigFeatureModel, sigName);
		final Formula f3 = rRelations.in(sigFeatureModel.product(sigRelation));
		final Formula f4 = rFormulas.in(sigFeatureModel.product(sigFormula));
		
		return Formula.and(f1, f2, f3, f4);
	}
	
	private Formula setupSigRelationDeclarations() {
		this.rParent = Relation.binary("parent");
		this.rChild = Relation.binary("child");
		this.rType = Relation.binary("type");
		this.rMin = Relation.binary("min");
		this.rMax = Relation.binary("max");
		
		final Formula f1 = rParent.function(sigRelation, sigName);
		final Formula f2 = rChild.in(sigRelation.product(sigName));
		final Formula f3 = rType.function(sigRelation, sigType);
		final Formula f4 = rMin.function(sigRelation, Expression.INTS);
		final Formula f5 = rMax.function(sigRelation, Expression.INTS);
		
		// TODO add in bound
		final Formula f6 = Formula.and(sigOptional.one(), sigMandatory.one(), sigOrFeature.one(), sigXorFeature.one());
		
		return Formula.and(f1, f2, f3, f4, f5, f6);
	}
	
	private Formula setupSigFormulaDeclarations() {
		this.rSatisfy = Relation.ternary("satisfy");
		this.rWelltyped = Relation.ternary("welltyped");
		this.rName = Relation.binary("name");
		this.rLeft = Relation.binary("left");
		this.rRight = Relation.binary("right");
		this.rOp = Relation.binary("op");
		
		final Formula f1 = rSatisfy.in(sigFormula.product(sigConfiguration).product(BooleanRelation.BOOL));
		final Formula f2 = rWelltyped.in(sigFormula.product(sigFeatureModel).product(BooleanRelation.BOOL));
		
		final Formula f3 = rName.function(sigNameF, sigName);
		final Formula f4 = rLeft.function(sigForm, sigFormula);
		final Formula f5 = rRight.function(sigForm, sigFormula);
		final Formula f6 = rOp.function(sigForm, sigOperation);
		
		// TODO add in bound
		final Formula f7 = Formula.and(sigAndF.one(), sigOrF.one(), sigImpliesF.one(), sigNotF.one());
		
		return Formula.and(f1, f2, f3, f4, f5, f6, f7);
	}
	
	private Formula setupSigConfigurationDeclarations() {
		this.rValue = Relation.binary("value");
		
		final Formula f1 = rValue.in(sigConfiguration.product(sigName));
		
		return f1;
	}
	
	private Formula wellFormedFeatureModel(Expression fm) {
		final Variable formula = Variable.unary("formula");
		final Formula f1 = (fm.join(formula.join(rWelltyped)).eq(BooleanRelation.TRUE)).forAll(formula.oneOf(fm.join(rFormulas)));
		
		final Variable relation = Variable.unary("relation");
		final Formula f2 = (relation.join(rParent)).in(fm.join(rFeatures));
		final Formula f3 = (relation.join(rChild)).in(fm.join(rFeatures));
		
		final Formula f4 = relation.join(rChild).sum().eq(IntConstant.constant(1));
		final Formula f5 = relation.join(rType).in(Expression.union(sigOptional, sigMandatory)).implies(f4);
		final Formula f6 = relation.join(rChild).sum().gt(IntConstant.constant(1));
		final Formula f7 = relation.join(rType).in(Expression.union(sigOrFeature, sigXorFeature)).implies(f6);
		final Formula f8 = Formula.and(f2, f3, f5, f7).forAll(relation.oneOf(fm.join(rRelations)));
		
		return Formula.and(f1, f8);
	}
	
	public static void main(String[] args) {
		FeatureModelVerification featureModelVerification = new FeatureModelVerification();
	}
	
}
