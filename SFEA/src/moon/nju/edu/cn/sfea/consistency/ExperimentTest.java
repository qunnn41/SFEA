package moon.nju.edu.cn.sfea.consistency;

import java.util.concurrent.CountDownLatch;

import kodkod.ast.Expression;

import moon.nju.edu.cn.fm.model.Feature;
import moon.nju.edu.cn.sfea.app.FMInterface;
import moon.nju.edu.cn.sfea.app.ValidConfigCallback;

public class ExperimentTest extends PlatformConstraints implements FMInterface, Runnable {
	private CountDownLatch downLatch;
	private String[] feature;
	private ValidConfigCallback callback;
	
	public ExperimentTest(String[] feature) {
		super("feature_model/openstack.fm");
		createInstance(feature);
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
	
	public static void main(String[] args) {
		ExperimentTest heroku = new ExperimentTest(new String[]{
				"Openstack", "Docker","OS","infrastructure","Service","DeployTool","Ubuntu","VirtualMachine","Ansible",
				"Node","Controller","Nova","nova-api","nova-compute","nova-scheduler","Keystone","Glance","glance-api","glance-registry",
				"Neutron","plugin","Bridge","neutron-server","agent","metadata","dhcp","Communication","NTP","RabbitMQ","Database","Mysql","MariaDB","Mountflag"});
		if (heroku.check()) {
			System.out.println("yes");
		} else {
			System.out.println("no");
		}
	}
}
