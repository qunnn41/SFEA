package moon.nju.edu.cn.fm.platform;

import kodkod.ast.Expression;
import kodkod.ast.Relation;
import moon.nju.edu.cn.fm.model.Feature;
import moon.nju.edu.cn.fm.verification.MetaModelConstraints;

public class HerokuFeatureModel extends CloudVerification implements CloudFeatureModelInterface {
	
	public HerokuFeatureModel(String file) {
		super(file);
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
	
	
	public static void main(String[] args) {
		CloudVerification demo = new CloudVerification("feature_model/heroku.fm");
		demo.createInstance(new String[] {"Language", "Spring", "Framework", "Java", "Service", "Log"});
		demo.check();
	}
}
