package moon.nju.edu.cn.sfea.search;

import java.util.LinkedList;
import java.util.List;

import kodkod.ast.Expression;
import kodkod.ast.Formula;
import kodkod.ast.IntConstant;
import kodkod.ast.Relation;
import kodkod.ast.Variable;

public class MetaModelConstraints {
	// Signature
	public static final Relation sigFeatureModel, sigName, sigRelation, sigType, sigOptional, sigMandatory, sigOrFeature, sigXorFeature;
	public static final Relation sigFormula, sigNameF, sigForm, sigOperation, sigAndF, sigOrF, sigImpliesF, sigNotF;
	public static final Relation sigConfiguration;
	
	// Relation
	public static Relation rFeatures, rRoot, rRelations, rFormulas, rParent, rChild, rType, rMin, rMax;
	public static Relation rSatisfy, rWelltyped, rName, rLeft, rRight, rOp, rValue, rCard, rSize;
	
	private List<Formula> formulas = new LinkedList<Formula>();
	
	public List<Formula> getFormulas() {
		return formulas;
	}
	
	public MetaModelConstraints() {
		formulas.add(this.setupSigFeatureModelDeclarations());
		formulas.add(this.setupSigRelationDeclarations());
		formulas.add(this.setupSigFormulaDeclarations());
		formulas.add(this.setupSigConfigurationDeclarations());
		formulas.add(this.formulaConstruction());
		formulas.add(this.formulaSatisfaction());
	}
	
	static {
		sigFeatureModel = Relation.unary("FeatureModel");
		
		sigName = Relation.unary("Name");
		
		sigRelation = Relation.unary("Relation");
		sigType = Relation.unary("Type");
		sigOptional = Relation.unary("Optional");
		sigMandatory = Relation.unary("Mandatory");
		sigOrFeature = Relation.unary("OrFeature");
		sigXorFeature = Relation.unary("XorFeature");
		
		sigFormula = Relation.unary("Formula");
		sigNameF = Relation.unary("NameF");
		sigForm = Relation.unary("Form");
		sigOperation = Relation.unary("Operation");
		sigAndF = Relation.unary("AndF");
		sigOrF = Relation.unary("OrF");
		sigImpliesF = Relation.unary("ImpliesF");
		sigNotF = Relation.unary("NotF");
		
		sigConfiguration = Relation.unary("Configuration");
		
		rFeatures = Relation.binary("features");
		rRoot = Relation.binary("root");
		rRelations = Relation.binary("relations");
		rFormulas = Relation.binary("formulas");
		
		rParent = Relation.binary("parent");
		rChild = Relation.binary("child");
		rType = Relation.binary("type");
		rMin = Relation.binary("min");
		rMax = Relation.binary("max");
		rCard = Relation.binary("card");
		rSize = Relation.binary("size");
		
		rSatisfy = Relation.ternary("satisfy");
		rWelltyped = Relation.ternary("welltyped");
		rName = Relation.binary("name");
		rLeft = Relation.binary("left");
		rRight = Relation.binary("right");
		rOp = Relation.binary("op");
		
		rValue = Relation.binary("value");
	}
	
	/**
	 * 	sig FeatureModel {
	 * 		features: set Name,
	 * 		root: Name,
	 * 		relations: set Relation,
	 * 		formulas: set Formula
	 * 	}
	 * 
	 * 	sig Name {
	 * 		card: Int
	 * 	}
	 */
	private Formula setupSigFeatureModelDeclarations() {
		final Formula f1 = rFeatures.in(sigFeatureModel.product(sigName));
		final Formula f2 = rRoot.function(sigFeatureModel, sigName);
		final Formula f3 = rRelations.in(sigFeatureModel.product(sigRelation));
		final Formula f4 = rFormulas.in(sigFeatureModel.product(sigFormula));
		final Formula f5 = rCard.in(sigName.product(Expression.INTS));
		
		return Formula.and(f1, f2, f3, f4, f5);
	}
	
	/**
	 * 	sig Relation {
	 * 		parent: Name,
	 * 		child: some Name,
	 * 		type: Type,
	 * 		min: Int,
	 * 		max: Int
	 *	}
	 *
	 *	abstract sig Type {}
	 *	one sig Optional, Mandatory, OrFeature, XorFeature extends Type {}
	 */
	private Formula setupSigRelationDeclarations() {
		final Formula f1 = rParent.in(sigRelation.product(sigName));
		final Formula f2 = rChild.in(sigRelation.product(sigName));
		final Formula f3 = rType.in(sigRelation.product(sigType));
		final Formula f4 = rMin.in(sigRelation.product(Expression.INTS));
		final Formula f5 = rMax.in(sigRelation.product(Expression.INTS));
		final Formula f6 = Formula.and(sigOptional.one(), sigMandatory.one(), sigOrFeature.one(), sigXorFeature.one());
		
		return Formula.and(f1, f2, f3, f4, f5, f6);
	}
	
	/**
	 * abstract sig Formula {
	 * 		satisfy: Configuration -> Bool,
	 * 		welltyped: FeatureModel -> Bool
	 *	}
	 *
	 * 	sig NameF extends Formula {
	 * 		name: Name,
	 * 		size: Int
	 * 	}
	 * 
	 * 	sig Form extends Formula {
	 * 		left: Formula,
	 * 		right: Formula,
	 * 		op: Operation
	 * 	}
	 * 
	 * 	abstract sig Operation {}
	 * 	one sig AndF, OrF, ImpliesF, NotF extends Operation {}
	 */
	
	private Formula setupSigFormulaDeclarations() {
		final Formula f1 = rSatisfy.in(sigFormula.product(sigConfiguration).product(BooleanExpression.BOOL));
		final Formula f2 = rWelltyped.in(sigFormula.product(sigFeatureModel).product(BooleanExpression.BOOL));
		
		final Formula f3 = rName.in(sigNameF.product(sigName));
		final Formula f4 = rLeft.in(sigForm.product(sigFormula));
		final Formula f5 = rRight.in(sigForm.product(sigFormula));
		final Formula f6 = rOp.in(sigForm.product(sigOperation));
		
		final Formula f7 = Formula.and(sigAndF.one(), sigOrF.one(), sigImpliesF.one(), sigNotF.one());
		final Formula f8 = rSize.in(sigNameF.product(Expression.INTS));
		
		return Formula.and(f1, f2, f3, f4, f5, f6, f7, f8);
	}
	
	/**
	 * 	sig Configuration {
	 * 		value: set Name
	 * 	}
	 * 
	 * 	fact configDatatype {
	 * 		all n: Name | some c: Configuration | c.value = n
	 * 	}
	 */
	private Formula setupSigConfigurationDeclarations() {
		final Formula f1 = rValue.in(sigConfiguration.product(sigName));
		final Variable n = Variable.unary("n");
		final Variable c = Variable.unary("c");
		final Formula f2 = c.join(rValue).eq(n).forSome(c.oneOf(sigConfiguration)).forAll(n.oneOf(sigName));
		
		return Formula.and(f1, f2);
	}
	
	/**
	 *	pred wellFormed(fm: FeatureModel) {
	 *		all relation: fm.relations | {
	 *			relation.parent in fm.features
	 *			relation.child in fm.features
	 *			relation.type in Optional + Mandatory => #relation.child = 1
	 *			relation.type in XorFeature + OrFeature => #relation.child > 1
	 *		}
	 *		
	 *		all formula: fm.formulas | formula.welltyped[fm] = True
	 *	} 
	 */
	public Formula wellFormed(Expression fm) {
		final Variable formula = Variable.unary("formula");
		final Formula f1 = (fm.join(formula.join(rWelltyped)).eq(BooleanExpression.TRUE)).forAll(formula.oneOf(fm.join(rFormulas)));
		
		final Variable relation = Variable.unary("relation");
		final Formula f2 = (relation.join(rParent)).in(fm.join(rFeatures));
		final Formula f3 = (relation.join(rChild)).in(fm.join(rFeatures));
		
		final Formula f4 = relation.join(rChild).count().eq(IntConstant.constant(1));
		final Formula f5 = relation.join(rType).in(Expression.union(sigOptional, sigMandatory)).implies(f4);
		final Formula f6 = relation.join(rChild).count().gt(IntConstant.constant(1));
		final Formula f7 = relation.join(rType).in(Expression.union(sigOrFeature, sigXorFeature)).implies(f6);
		final Formula f8 = Formula.and(f2, f3, f5, f7).forAll(relation.oneOf(fm.join(rRelations)));
		
		return Formula.and(f1, f8);
	}
	
	/**
	 *	fact FormulaConstruction {
	 *		all formula: Form | formula not in formula.^(left + right)
	 *		all fm: FeatureModel | {
	 *			all v1: NameF | v1.welltyped[fm] = welltypedName[v1, fm]
	 *			all v2: Form | v2.welltyped[fm] = welltypedFormula[v2, fm]
	 *		}
	 *
	 *		all disj c1, c2: Configuration | c1.value != c2.value
	 *	} 
	 */
	private Formula formulaConstruction() {
		final Variable formula = Variable.unary("formula");
		final Formula f1 = (formula.in(formula.join(Expression.union(rLeft, rRight).closure()))).not().forAll(formula.oneOf(sigForm));
		
		final Variable fm = Variable.unary("fm");
		final Variable v1 = Variable.unary("v1");
		final Variable v2 = Variable.unary("v2");
		final Formula f2 = (fm.join(v1.join(rWelltyped)).eq(wellTypedName(v1, fm))).forAll(v1.oneOf(sigNameF));
		final Formula f3 = (fm.join(v2.join(rWelltyped)).eq(wellTypedFormula(v2, fm))).forAll(v2.oneOf(sigForm));
		final Formula f4 = Formula.and(f2, f3).forAll(fm.oneOf(sigFeatureModel));
		
		final Variable c1 = Variable.unary("c1");
		final Variable c2 = Variable.unary("c2");
		
		final Formula f5 = c1.join(rValue).eq(c2.join(rValue)).not();
		final Formula f6 = c1.eq(c2).not().implies(f5);
		
		final Formula f7 = f6.forAll(c1.oneOf(sigConfiguration).and(c2.oneOf(sigConfiguration)));
		
		return Formula.and(f1, f4, f7);
	}
	
	
	/**
	 * pred searchingConfig(instance: set Name, fm: FeatureModel) {
	 * 		all c: Configuration | some (c.value & instance) && c in semantics[fm]
	 * }
	 */
	public Formula searchingConfiguration(Expression instance, Expression fm) {
		final Variable c = Variable.unary("c");
		final Formula f1 = c.join(rValue).intersection(instance).some();
		final Formula f2 = c.in(semantics(fm));
		
		return Formula.and(f1, f2).forSome(c.oneOf(sigConfiguration));
	}
	
	/**
	 * 	fun welltypedName(f: NameF, fm: FeatureModel): Bool {
	 * 		f.name in fm.features implies True else False
	 * 	}
	 */
	private Expression wellTypedName(Expression f, Expression fm) {
		Formula f1 = f.join(rName).in(fm.join(rFeatures));
		return f1.thenElse(BooleanExpression.TRUE, BooleanExpression.FALSE);
	}
	
	/**
	 * 	fun welltypedFormula(f: Form, fm: FeatureModel): Bool {
	 * 		f.op = NotF implies f.welltyped[fm] else And[f.left.welltyped[fm], f.right.welltyped[fm]]
	 * 	}
	 */
	private Expression wellTypedFormula(Expression f, Expression fm) {
		Formula formula = f.join(rOp).eq(sigNotF);
		return formula.thenElse(fm.join(f.join(rWelltyped)),
			BooleanExpression.And(fm.join(f.join(rLeft).join(rWelltyped)), fm.join(f.join(rRight).join(rWelltyped)))); 
	}
	
	/**
	 * 	fun semantics(fm: FeatureModel): set Configuration {
	 * 		{c: Configuration | 
	 * 			satisfyRelations[fm,c] and 
	 * 			satisfyImplicitConstraints[fm,c] and 
	 * 			satisfyExplicitConstraints[fm,c]
	 * 		}
	 * 	}
	 */
	public Expression semantics(Expression fm) {
		final Variable c = Variable.unary("c");
		final Formula f = Formula.and(satisfyRelations(fm, c), 
				satisfyImplicitConstraints(fm, c), 
				satisfyExplicitConstraints(fm, c));
		
		return f.comprehension(c.oneOf(sigConfiguration));
	}
	
	/**
	 * 	pred satisfyRelations(fm: FeatureModel, c: Configuration) {
	 * 		all r: fm.relations | {
	 * 			r.type = Optional implies (r.child in c.value implies r.parent in c.value)
	 * 			r.type = Mandatory implies (r.child in c.value <=> r.parent in c.value)
	 * 
	 * 			//r.type = XorFeature implies (r.parent in c.value implies (one n : r.child | n in c.value))
	 * 			//r.type = OrFeature implies (r.parent in c.value implies (some n : r.child | n in c.value))
	 * 			r.type = OrFeature implies (r.parent in c.value implies #{n1: r.child | n1 in c.value} >= r.min and #{n2: r.child | n2 in c.value} <= r.max)
	 * 			r.type = XorFeature implies (r.parent in c.value implies #{n3: r.child | n3 in c.value} >= r.min and #{n4: r.child | n4 in c.value} <= r.max)
	 * 		}
	 * 	}
	 */
	private Formula satisfyRelations(Expression fm, Expression c) {
		final Variable r = Variable.unary("r");
		final Formula f1 = r.join(rChild).in(c.join(rValue)).implies(r.join(rParent).in(c.join(rValue)));
		final Formula f2 = r.join(rChild).in(c.join(rValue)).iff(r.join(rParent).in(c.join(rValue)));
		
		final Variable n = Variable.unary("n");
		final Expression e = n.in(c.join(rValue)).comprehension(n.oneOf(r.join(rChild)));
		// final Expression e = r.join(rChild).intersection(c.join(rValue));

		final Formula f3 = Formula.and(e.count().gte(r.join(rMin).sum()), e.count().lte(r.join(rMax).sum()));
		final Formula f4 = r.join(rParent).in(c.join(rValue)).implies(f3);
		
		final Formula f5 = r.join(rType).eq(sigOptional).implies(f1);
		final Formula f6 = r.join(rType).eq(sigMandatory).implies(f2);
		final Formula f7 = r.join(rType).in(Expression.union(sigOrFeature, sigXorFeature)).implies(f4);
		
		return Formula.and(f5, f6, f7).forAll(r.oneOf(fm.join(rRelations)));
	}
	
	/**
	 * 	pred satisfyImplicitConstraints(fm: FeatureModel, c: Configuration) {
	 * 		fm.root in c.value
	 * 		c.value in fm.features
	 * 	}
	 */
	private Formula satisfyImplicitConstraints(Expression fm, Expression c) {
		final Formula f1 = fm.join(rRoot).in(c.join(rValue));
		final Formula f2 = c.join(rValue).in(fm.join(rFeatures));
		
		return Formula.and(f1, f2);
	}
	
	/**
	 * 	pred satisfyExplicitConstraints(fm: FeatureModel, c:Configuration) {
	 * 		all formula: fm.formulas | formula.satisfy[c] = True
	 * 	}
	 */
	private Formula satisfyExplicitConstraints(Expression fm, Expression c) {
		final Variable formula = Variable.unary("formula");
		final Formula f = c.join(formula.join(rSatisfy)).eq(BooleanExpression.TRUE);
		
		return f.forAll(formula.oneOf(fm.join(rFormulas)));
	}
	
	/**
	 * fact FormulaSatisfaction {
	 * 		all c: Configuration | {
	 * 			all p: NameF | p.satisfy[c] = satisfyName[p, c]
	 * 			all q: Form | q.satisfy[c] = satisfyFormula[q, c]
	 * 		}
	 * 	}
	 */
	private Formula formulaSatisfaction() {
		final Variable c = Variable.unary("c");
		final Variable p = Variable.unary("p");
		final Variable q = Variable.unary("q");
		final Formula f1 = c.join(p.join(rSatisfy)).eq(satisfyName(p, c));
		final Formula f2 = c.join(q.join(rSatisfy)).eq(satisfyFormula(q, c));
		
		final Formula f3 = Formula.and(f1.forAll(p.oneOf(sigNameF)), f2.forAll(q.oneOf(sigForm)));
		
		return f3.forAll(c.oneOf(sigConfiguration));
	}
	
	/**
	 * 	fun satisfyName(f: NameF, c: Configuration): Bool {
	 * 		(f.name in c.value and f.size <= f.name.card) implies True else False
	 * 	}
	 */
	private Expression satisfyName(Expression f, Expression c) {
		Formula f1 = f.join(rName).in(c.join(rValue));
		Formula f2 = f.join(rSize).sum().lte(f.join(rName).join(rCard).sum());
		return Formula.and(f1, f2).thenElse(BooleanExpression.TRUE, BooleanExpression.FALSE);
	}
	
	/**
	 * fun satisfyFormula(f: Form, c: Configuration): Bool {
	 * 		f.op = AndF implies And[f.left.satisfy[c], f.right.satisfy[c]] else {
	 * 			f.op = OrF implies Or[f.left.satisfy[c], f.right.satisfy[c]] else {
	 * 				f.op = NotF implies Not[f.left.satisfy[c]] else {
	 * 					Or[Not[f.left.satisfy[c]], f.right.satisfy[c]]
	 * 				}
	 * 			}
	 * 		}
	 * 	}
	 */
	private Expression satisfyFormula(Expression f, Expression c) {
		Formula f1 = f.join(rOp).eq(sigAndF);
		Formula f2 = f.join(rOp).eq(sigOrF);
		Formula f3 = f.join(rOp).eq(sigNotF);
		
		Expression e1 = c.join(f.join(rLeft).join(rSatisfy));
		Expression e2 = c.join(f.join(rRight).join(rSatisfy));
		
		return f1.thenElse(BooleanExpression.And(e1, e2), 
				f2.thenElse(BooleanExpression.Or(e1, e2), 
						f3.thenElse(BooleanExpression.Not(e1), 
								BooleanExpression.Or(BooleanExpression.Not(e1), e2))));
	}
}
