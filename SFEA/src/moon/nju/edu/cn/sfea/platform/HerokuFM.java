package moon.nju.edu.cn.sfea.platform;

import java.util.concurrent.CountDownLatch;

import kodkod.ast.Expression;
import kodkod.ast.Relation;
import moon.nju.edu.cn.fm.model.Feature;
import moon.nju.edu.cn.sfea.verification.CloudVerification;
import moon.nju.edu.cn.sfea.verification.MetaModelConstraints;

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
	
	public HerokuFM(String[] feature) {
		super("feature_model/heroku.fm");
		this.feature = feature;
		this.createInstance(feature);
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
	
	@Override
	public void run() {
		this.createInstance(feature);
		if (this.check()) {
			callback.onValid();
		} else {
			callback.onInvalid();
		}
		
		downLatch.countDown();
	}
	
	public static void main(String[] args) {
		HerokuFM herokuFM = new HerokuFM(new String[]{"Java", "Language", "Spring", "Framework"});
		if (herokuFM.check()) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}
}
