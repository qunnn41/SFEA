package moon.nju.edu.cn.sfea.search;

import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import kodkod.ast.Expression;
import kodkod.ast.Relation;
import moon.nju.edu.cn.fm.model.Feature;
import moon.nju.edu.cn.sfea.app.FMInterface;
import moon.nju.edu.cn.sfea.app.ValidConfigCallback;

public class ExperimenTTest extends CloudVerification implements FMInterface, Runnable {
	private CountDownLatch downLatch;
	private String[] feature;
	private ValidConfigCallback callback;
	
	public ExperimenTTest(CountDownLatch downLatch, String[] feature, ValidConfigCallback callback) {
		super("feature_model/openstack.fm");
		this.feature = feature;
		this.downLatch = downLatch;
		this.callback = callback;
	}
	
	public ExperimenTTest(String[] feature) {
		super("feature_model/ex11.fm");
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
		
		Expression importantFeature = null;
		for (Entry<Feature, Relation> entry: signMap.entrySet()) {
			Feature feature = entry.getKey();
			Relation relation = entry.getValue();
			if (feature.getName().endsWith("Ansible")) {
				importantFeature = importantFeature == null ? relation : importantFeature.union(relation);
			}
		}
		
		//TODO tradeoff
//		this.searchSimilarConfig(featureSelection, importantFeature, string.length * 3 / 4, string.length, 3);
		
		
		config1 = Relation.unary("config1");
		formulas.add(config1.one());
		formulas.add(config1.join(MetaModelConstraints.rValue).eq(featureSelection));
		this.checkConfig();
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
//		ExperimenTTest heroku = new ExperimenTTest(new String[]{
//				"Openstack", "Docker","OS","infrastructure","Service","DeployTool","Ubuntu","VirtualMachine","Ansible",
//				"Node","Controller","Nova","nova-api","nova-compute","nova-scheduler","Keystone","Glance","glance-api","glance-registry",
//				"Neutron","plugin","Bridge","neutron-server","agent","metadata","dhcp","Communication","NTP","RabbitMQ","Database","Mysql","MariaDB"});
//		if (heroku.check()) {
//			System.out.println("yes");
//		} else {
//			System.out.println("no");
//		}
		
		ExperimenTTest heroku = new ExperimenTTest(new String[]{"f1","f2"});
		if (heroku.check()) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
		
		ExperimenTTest heroku1 = new ExperimenTTest(new String[]{"f1","f2","f3","f4","f5"});
		if (heroku1.check()) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
		
		ExperimenTTest heroku2 = new ExperimenTTest(new String[]{"f1","f3","f9","f40","f45","f41"});
		if (heroku2.check()) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
		
		ExperimenTTest heroku3 = new ExperimenTTest(new String[]{"f1","f2","f40","f46"});
		if (heroku3.check()) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
		
		ExperimenTTest heroku4 = new ExperimenTTest(new String[]{"f1","f2","f11"});
		if (heroku4.check()) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
		
		ExperimenTTest heroku5 = new ExperimenTTest(new String[]{"f1","f2"});
		if (heroku5.check()) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}
}
