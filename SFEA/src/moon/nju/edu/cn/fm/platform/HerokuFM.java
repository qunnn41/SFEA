package moon.nju.edu.cn.fm.platform;

import java.util.concurrent.CountDownLatch;

import kodkod.ast.Expression;
import kodkod.ast.Relation;
import moon.nju.edu.cn.fm.model.Feature;
import moon.nju.edu.cn.fm.verification.CloudVerification;
import moon.nju.edu.cn.fm.verification.MetaModelConstraints;

public class HerokuFM extends CloudVerification implements FMInterface, Runnable {
	private CountDownLatch downLatch;
	private String[] feature;
	private ValidConfigCallback callback;
	
	public HerokuFM(CountDownLatch downLatch, String[] feature, ValidConfigCallback callback) {
		super("feature_model/heroku.fm");
		this.feature = feature;
		this.downLatch = downLatch;
		this.callback = callback;
	}

	@Override
	public void createInstance(String[] string) {
		config1 = Relation.unary("Config1");
		formulas.add(config1.one());
		
		Expression featureSelection = signMap.get(rootFeature);
		for (String str : string) {
			for (Feature feature: signMap.keySet()) {
				if (str.equals(feature.getName())) {
					featureSelection = featureSelection == null ? signMap.get(feature) : featureSelection.union(signMap.get(feature));
					break;
				}
			}
		}
		
		formulas.add(config1.join(MetaModelConstraints.rValue).eq(featureSelection));
		this.validConfiguration();
	}
	
//	public static void main(String[] args) {
//		HerokuFM demo = new HerokuFM();
//		demo.createInstance(new String[] {"Language", "Javascript", "Service", "Database", "SQL", "ClearDB", "Framework", "Node.js"});
//		demo.check();
//	}

	@Override
	public void run() {
		this.createInstance(feature);
		callback.onValid();
//		if (this.check()) {
//			callback.onValid();
//		} else {
//			callback.onInvalid();
//		}
		
		downLatch.countDown();
	}
}
