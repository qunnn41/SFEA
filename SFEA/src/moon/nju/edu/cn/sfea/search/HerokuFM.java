package moon.nju.edu.cn.sfea.search;

import java.util.concurrent.CountDownLatch;

import kodkod.ast.Expression;
import moon.nju.edu.cn.fm.model.Feature;
import moon.nju.edu.cn.sfea.app.FMInterface;
import moon.nju.edu.cn.sfea.app.ValidConfigCallback;

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
		super("feature_model/cloud.fm");
		this.feature = feature;
		this.createInstance(feature);
	}

	@Override
	public void createInstance(String[] string) {
		Expression featureSelection = signMap.get(rootFeature);
		for (String str : string) {
			for (Feature feature: signMap.keySet()) {
				if (str.equals(feature.getName())) {
					featureSelection = featureSelection == null ? signMap.get(feature) : featureSelection.union(signMap.get(feature));
					break;
				}
			}
		}
		
//		formulas.add(config1.join(MetaModelConstraints.rValue).in(featureSelection));
		this.searchSimilarConfig(featureSelection, string.length / 3);
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
		HerokuFM herokuFM = new HerokuFM(new String[]{"Java", "Language", "Rails", "Framework"});
		if (herokuFM.check()) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}
}
