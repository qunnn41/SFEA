sig FM {
	features: set Name
}

sig Name {}

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

pred alternative(A: Name, children: set Name, conf: set Name) {
	orFeature[A, children, conf]
	#children & conf <= 1
}

one sig M extends FM{}
one sig mobile, earphone, mp3, camera extends Name{}

fact {
	M.features = mobile + earphone + mp3 + camera
}

pred semantics(conf: set Name) {
	conf in M.features
	root[mobile, conf]

	optional[mobile, earphone, conf]
	orFeature[mobile, earphone+camera, conf]
	earphone in conf <=> mp3 in conf
}

pred validConfig {
	semantics[mobile+mp3+earphone]
}

run validConfig for 1 FM, 4 Name
