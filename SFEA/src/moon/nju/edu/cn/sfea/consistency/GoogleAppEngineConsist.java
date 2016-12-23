package moon.nju.edu.cn.sfea.consistency;

import java.util.concurrent.CountDownLatch;

import kodkod.ast.Expression;

import moon.nju.edu.cn.fm.model.Feature;
import moon.nju.edu.cn.sfea.app.FMInterface;
import moon.nju.edu.cn.sfea.app.ValidConfigCallback;

public class GoogleAppEngineConsist extends PlatformConstraints implements FMInterface, Runnable {
	private CountDownLatch downLatch;
	private String[] feature;
	private ValidConfigCallback callback;
	
	public GoogleAppEngineConsist(CountDownLatch downLatch, String[] feature, ValidConfigCallback callback) {
		super("feature_model/gae.fm");
		this.feature = feature;
		this.downLatch = downLatch;
		this.callback = callback;
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
		
		this.semantics(featureSelection);
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
}
