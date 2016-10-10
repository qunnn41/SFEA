package moon.nju.edu.cn.fm.verification;

import java.util.LinkedList;
import java.util.List;

import kodkod.ast.Expression;
import kodkod.ast.Formula;
import kodkod.ast.IntConstant;
import kodkod.ast.Relation;

public class DemoVerification {
	private List<Formula> formulas = new LinkedList<Formula>();
	private BasicFMVerification basic;
	
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
		basic = new BasicFMVerification();
		init();
		createInstance();
		validConfiguration();
	}
	
	public List<Formula> getFormulas() {
//		formulas.addAll(basic.getFormulas());
		return formulas;
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
		
		formulas.add(fm1.join(BasicFMVerification.rRoot).eq(mobilePhone));
		formulas.add(fm1.join(BasicFMVerification.rFeatures).eq(Expression.union(mobilePhone, earphone, mp3, camera)));
		formulas.add(fm1.join(BasicFMVerification.rRelations).eq(Expression.union(r1, r2)));
		formulas.add(fm1.join(BasicFMVerification.rFormulas).eq(Expression.union(f1, f4)));
		
		formulas.add(r1.join(BasicFMVerification.rType).eq(BasicFMVerification.sigOptional));
		formulas.add(r1.join(BasicFMVerification.rParent).eq(mobilePhone));
		formulas.add(r1.join(BasicFMVerification.rChild).eq(earphone));
		
		formulas.add(r2.join(BasicFMVerification.rType).eq(BasicFMVerification.sigOrFeature));
		formulas.add(r2.join(BasicFMVerification.rParent).eq(mobilePhone));
		formulas.add(r2.join(BasicFMVerification.rChild).eq(Expression.union(mp3, camera)));
		formulas.add(r2.join(BasicFMVerification.rMin).sum().eq(IntConstant.constant(1)));
		formulas.add(r2.join(BasicFMVerification.rMax).sum().eq(IntConstant.constant(2)));
		
		formulas.add(f1.join(BasicFMVerification.rOp).eq(BasicFMVerification.sigImpliesF));
		formulas.add(f1.join(BasicFMVerification.rLeft).eq(f2));
		formulas.add(f1.join(BasicFMVerification.rRight).eq(f3));
		
		formulas.add(f2.join(BasicFMVerification.rName).eq(earphone));
		formulas.add(f3.join(BasicFMVerification.rName).eq(mp3));
		
		formulas.add(f4.join(BasicFMVerification.rOp).eq(BasicFMVerification.sigImpliesF));
		formulas.add(f4.join(BasicFMVerification.rLeft).eq(f3));
		formulas.add(f4.join(BasicFMVerification.rRight).eq(f2));
	}
	
	private void createInstance() {
		config1 = Relation.unary("Config1");
		formulas.add(config1.one());
		formulas.add(config1.join(BasicFMVerification.rValue).eq(Expression.union(mobilePhone, earphone, mp3)));
	}
	
	private void validConfiguration() {
		formulas.add(config1.in(basic.semantics(fm1)));
	}
	
	public static void main(String[] args) {
		DemoVerification demo = new DemoVerification();
		System.out.println(demo.getFormulas());
		System.out.println();
	}
}
