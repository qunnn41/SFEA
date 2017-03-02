open util/boolean

sig FeatureModel {
	features: set Name,
	root: features,
	relations: set Relation,
	formulas: set Formula
}

sig Name {
	card: Int
}

sig Relation {
	parent: Name,
	child: some Name,
	type: Type,
	min: Int,
	max: Int
}

abstract sig Type {}
one sig Optional, Mandatory, OrFeature, XorFeature,featureCard, groupCard extends Type {}

abstract sig Formula {
	satisfy: Configuration -> Bool,
	welltyped: FeatureModel -> Bool
}

sig NameF extends Formula {
	name: Name,
	size: Int
}

sig Form extends Formula {
	left: Formula,
	right: Formula,
	op: Operation
}

abstract sig Operation {}
one sig AndF, OrF, ImpliesF, NotF extends Operation {}

// A Feature Model is well-formed <=> relations & formulas are well typed
pred wellFormed(fm: FeatureModel) {
	all relation: fm.relations | {
		relation.parent in fm.features
		relation.child in fm.features
		relation.type in Optional + Mandatory => #relation.child = 1
		relation.type in XorFeature + OrFeature => #relation.child > 1
	}

	all formula: fm.formulas | formula.welltyped[fm] = True
}

fact FormulaConstruction {
	all formula: Form | formula not in formula.^(left + right)
	all fm: FeatureModel | {
		all v1: NameF | v1.welltyped[fm] = welltypedName[v1, fm]
		all v2: Form | v2.welltyped[fm] = welltypedFormula[v2, fm]
	}
}

fun welltypedName(f: NameF, fm: FeatureModel): Bool {
	f.name in fm.features implies True else False
}

fun welltypedFormula(f: Form, fm: FeatureModel): Bool {
	f.op = NotF implies f.welltyped[fm] else And[f.left.welltyped[fm], f.right.welltyped[fm]]
}

//semantics
sig Configuration {
	value: set Name
}

fact configDatatype {
	all n: Name | some c: Configuration | c.value = n
	all disj c1, c2: Configuration | c1.value != c2.value
}

fun semantics(fm: FeatureModel): set Configuration {
	{c: Configuration | 
		satisfyRelations[fm,c] and 
		satisfyImplicitConstraints[fm,c] and 
		satisfyExplicitConstraints[fm,c]
	}
}

pred satisfyRelations(fm: FeatureModel, c: Configuration) {
	all r: fm.relations | {
		r.type = Optional implies 
			(r.child in c.value implies r.parent in c.value)

		r.type = Mandatory implies 
			(r.child in c.value <=> r.parent in c.value)

		r.type = XorFeature implies 
			(r.parent in c.value implies (one n : r.child | n in c.value))
		r.type = OrFeature implies 
			(r.parent in c.value implies (some n : r.child | n in c.value))
		
		r.type = featureCard implies 
			((r.child in c.value and #r.child >= r.min and #r.child <= max) => r.parent in c.value)
		r.type = groupCard implies 
			(r.parent in c.value implies #{n2: r.child | n2 in c.value} >= r.min and #{n2: r.child | n2 in c.value} <= r.max)
	}
}

pred satisfyImplicitConstraints(fm: FeatureModel, c: Configuration) {
	fm.root in c.value
	c.value in fm.features
}

pred satisfyExplicitConstraints(fm: FeatureModel, c:Configuration) {
	all formula: fm.formulas | formula.satisfy[c] = True
}

fact FormulaSatisfaction {
	all c: Configuration | {
		all f: NameF | f.satisfy[c] = satisfyName[f, c]
		all f: Form | f.satisfy[c] = satisfyFormula[f, c]
	}

	all n: Name | n.card = 1
}

fun satisfyName(f: NameF, c: Configuration): Bool {
	(f.name in c.value and f.size <= f.name.card) implies True else False
}

fun satisfyFormula(f: Form, c: Configuration): Bool {
	f.op = AndF implies And[f.left.satisfy[c], f.right.satisfy[c]] else {
		f.op = OrF implies Or[f.left.satisfy[c], f.right.satisfy[c]] else {
			f.op = NotF implies Not[f.left.satisfy[c]] else {
				Or[Not[f.left.satisfy[c]], f.right.satisfy[c]]
			}
		}
	}
}



one sig Cloud, Framework, Language, Java, Javascript, Nodejs, Spring extends Name {}
one sig fm1 extends FeatureModel{}
one sig r1, r2, r4, r5 extends Relation{}
one sig f1, f4 extends Form{}
one sig f2, f3, f5, f6 extends NameF{}

fact {
	fm1.root = Cloud
	fm1.features = Cloud+Framework+Language+Java+Javascript+Nodejs+Spring

	fm1.relations = r1+r2+r4+r5
	fm1.formulas = f1+f4

	Cloud.card = 1
  Framework.card = 1
  Language.card = 1
  Java.card = 1
	Javascript.card = 1
  Nodejs.card = 1
  Spring.card = 1
}

fact {
	r1.type = Optional
	r1.parent = Cloud
	r1.child = Framework

	r2.type = Mandatory
	r2.parent = Cloud
	r2.child = Language

	r4.type = XorFeature
	r4.parent = Framework
	r4.child = Nodejs+Spring

	r5.type = XorFeature
	r5.parent = Language
	r5.child = Java+Javascript
}

fact {
	f1.op = ImpliesF
	f1.left = f2
	f1.right = f3
	f2.name = Nodejs
	f2.size = 1
	f3.name = Javascript
	f3.size = 1

	f4.op = ImpliesF
	f4.left = f5
	f4.right = f6
	f5.name = Spring
	f5.size = 1
	f6.name = Java
	f6.size = 1
}

one sig Config1 extends Configuration{} {
	value = Cloud+Language+Java
}

assert validConfig1 {
	wellFormed[fm1]
	Config1 in semantics[fm1]
}

check validConfig1 for 1 FeatureModel, 7 Name, 4 Relation, 6 Formula, 128 Configuration

one sig Config2 extends Configuration{} {
	value = Cloud+Framework+Spring
}

assert validConfig2 {
	wellFormed[fm1]
	Config2 in semantics[fm1]
}

check validConfig2 for 1 FeatureModel, 7 Name, 4 Relation, 6 Formula, 128 Configuration

fun showConfig(): set Configuration {
	semantics[fm1]
}

run showConfig for 1 FeatureModel, 7 Name, 4 Relation, 6 Formula, 128 Configuration
