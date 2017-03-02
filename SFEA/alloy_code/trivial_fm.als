sig FM {
	features: set Name
}

sig Name {
	value: Int
}

pred optional(A,B: Name, conf: set Name) {
	B in conf implies A in conf
}

pred mandatory(A, B: Name, conf: set Name) {
	A in conf <=> B in conf
}

pred root(A: Name, conf: set Name) {
	A in conf
}

pred orFeature(A: Name, children: set Name, conf: set Name) {
	A in conf <=> some c: children | c in conf
	#children > 1
}

pred XorFeature(A: Name, children: set Name, conf: set Name) {
	orFeature[A, children, conf]
	#children & conf <= 1
}

pred featureCardinality(A, B: Name, conf: set Name, m, n: Int) {
	B in conf implies (A in conf && A.value >=m && A.value <= n)
}

pred groupCardinality(A: Name, children: set Name, conf: set Name, m, n: Int) {
	A in conf <=> #children & conf >=m && #children & conf <=n
}

one sig M extends FM {}
one sig Cloud, Framework, Language, Database, Java, Javascript, Nodejs, Spring, Redis, Mysql extends Name {}

fact {
	M.features = Cloud+Framework+Language+Database+Java+Javascript+Nodejs+Spring+Redis+Mysql
	Language.value = 1
	Cloud.value = 1
	Java.value = 1
	Framework.value = 1
	Spring.value = 1
}

pred semantics(conf: set Name) {
	conf in M.features
	root[Cloud, conf]

	optional[Cloud, Framework, conf]
	optional[Cloud, Database, conf]
	mandatory[Cloud, Language, conf]

	orFeature[Database, Redis+Mysql, conf]
	XorFeature[Framework, Nodejs+Spring, conf]
	XorFeature[Language, Javascript+Java, conf]

	Nodejs in conf implies Javascript in conf
	Spring in conf implies Java in conf
}

assert validConfig1 {
	semantics[Language+Cloud+Java]
}

check validConfig1 for 1 FM, 10 Name

assert validConfig2 {
	semantics[Framework+Cloud+Spring]
}

check validConfig2 for 1 FM, 10 Name
