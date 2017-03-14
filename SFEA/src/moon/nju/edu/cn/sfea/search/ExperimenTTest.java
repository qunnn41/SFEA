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
		super("feature_model/openstack.fm");
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
		this.searchSimilarConfig(featureSelection, importantFeature, string.length * 3 / 4, string.length, 3);
		
		
//		
//		config1 = Relation.unary("config1");
//		formulas.add(config1.one());
//		formulas.add(config1.join(MetaModelConstraints.rValue).eq(featureSelection));
//		this.checkConfig();
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
		ExperimenTTest heroku = new ExperimenTTest(new String[]{
				"Openstack", "Docker","OS","infrastructure","Service","DeployTool","Ubuntu","VirtualMachine","Ansible",
				"Node","Controller","Nova","nova-api","nova-compute","nova-scheduler","Keystone","Glance","glance-api","glance-registry",
				"Neutron","plugin","Bridge","neutron-server","agent","metadata","dhcp","Communication","NTP","RabbitMQ","Database","Mysql","MariaDB"});
		if (heroku.check()) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}
}
