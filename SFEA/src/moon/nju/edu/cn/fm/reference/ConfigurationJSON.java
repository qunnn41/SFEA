package moon.nju.edu.cn.fm.reference;

import java.io.FileWriter;

import org.json.simple.JSONObject;

/**
 * generate configuration json file for NFS
 * @author wyq
 */
@SuppressWarnings("unchecked")
public class ConfigurationJSON {
	private JSONObject root;

	public ConfigurationJSON() {
		root = new JSONObject();
		root.put("dynos", initDynos());
		root.put("database", initDatabase());
		root.put("caching", initCaching());
		
		try {
			FileWriter file = new FileWriter("config.json");
			file.write(root.toJSONString());
			file.flush();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JSONObject initCaching() {
		JSONObject obj = new JSONObject();
		obj.put("Memcache", initMemCachier());
		obj.put("Ironcache", initIronCache());
		
		return obj;
	}
	
	// cache
	private JSONObject initMemCachier() {
		JSONObject obj = new JSONObject();
		
		JSONObject dev = new JSONObject();
		dev.put("PST Support", false);
		dev.put("bucket Size (MB)", 25.0);
		dev.put("connections", 10);
		dev.put("advanced analytics dashboard", false);
		dev.put("new relic integration", false);
		dev.put("price ($/mon)", 0.0);
		obj.put("dev", dev);
		
		JSONObject m100 = new JSONObject();
		m100.put("PST Support", true);
		m100.put("bucket Size (MB)", 100.0);
		m100.put("connections", 1000);
		m100.put("advanced analytics dashboard", false);
		m100.put("new relic integration", false);
		m100.put("price ($/mon)", 15.0);
		obj.put("100", m100);
		
		JSONObject m250 = new JSONObject();
		m250.put("PST Support", true);
		m250.put("bucket Size (MB)", 250.0);
		m250.put("connections", 1000);
		m250.put("advanced analytics dashboard", false);
		m250.put("new relic integration", false);
		m250.put("price ($/mon)", 25.0);
		obj.put("250", m250);
		
		JSONObject m500 = new JSONObject();
		m500.put("PST Support", true);
		m500.put("bucket Size (MB)", 500.0);
		m500.put("connections", 1000);
		m500.put("advanced analytics dashboard", true);
		m500.put("new relic integration", true);
		m500.put("price ($/mon)", 40.0);
		obj.put("500", m500);
		
		return obj;
	}
	
	private JSONObject initIronCache() {
		JSONObject obj = new JSONObject();
		
		JSONObject dev = new JSONObject();
		dev.put("size (GB)", 0.1);
		dev.put("request per month", 10000000);
		dev.put("unlimited caches", false);
		dev.put("ssl", false);
		dev.put("high availability", false);
		dev.put("phone support", false);
		dev.put("price ($/mon)", 0.0);
		obj.put("developer", dev);
		
		JSONObject small = new JSONObject();
		small.put("size (GB)", 1.0);
		small.put("request per month", 50000000);
		small.put("unlimited caches", true);
		small.put("ssl", true);
		small.put("high availability", false);
		small.put("phone support", false);
		small.put("price ($/mon)", 49.0);
		obj.put("small", small);
		
		JSONObject medium = new JSONObject();
		medium.put("size (GB)", 5.0);
		medium.put("request per month", 150000000);
		medium.put("unlimited caches", true);
		medium.put("ssl", true);
		medium.put("high availability", true);
		medium.put("phone support", false);
		medium.put("price ($/mon)", 149.0);
		obj.put("medium", medium);
		
		JSONObject large = new JSONObject();
		large.put("size (GB)", 20.0);
		large.put("request per month", 500000000);
		large.put("unlimited caches", true);
		large.put("ssl", true);
		large.put("high availability", true);
		large.put("phone support", true);
		large.put("price ($/mon)", 499.0);
		obj.put("developer", large);
		
		return obj;
	}
	
	// database
	private JSONObject initDatabase() {
		JSONObject obj = new JSONObject();
		obj.put("ClearDB", initClearDB());
		obj.put("Redis", initRedis());
		obj.put("MongoDB", initMongoDB());
		obj.put("Postgres", initPostgres());
		
		return obj;
	}
	
	private JSONObject initPostgres() {
		JSONObject obj = new JSONObject();
		
		JSONObject hobby_dev = new JSONObject();
		hobby_dev.put("RAM (GB)", 0.05);
		hobby_dev.put("connections", 20);
		hobby_dev.put("rollback", false);
		hobby_dev.put("high avaliability", false);
		hobby_dev.put("database forks", false);
		hobby_dev.put("price ($/mon)", 0.0);
		obj.put("hobby-dev", hobby_dev);
		
		JSONObject hobby_basic = new JSONObject();
		hobby_basic.put("RAM (GB)", 0.5);
		hobby_basic.put("connections", 20);
		hobby_basic.put("rollback", false);
		hobby_basic.put("high avaliability", false);
		hobby_basic.put("database forks", false);
		hobby_basic.put("price ($/mon)", 9.0);
		obj.put("hobby-basic", hobby_basic);
		
		JSONObject standard = new JSONObject();
		standard.put("RAM (GB)", 1.0);
		standard.put("connections", 120);
		standard.put("rollback", true);
		standard.put("high avaliability", false);
		standard.put("database forks", true);
		standard.put("price ($/mon)", 50.0);
		obj.put("standard-0", standard);
		
		JSONObject premium = new JSONObject();
		premium.put("RAM (GB)", 1.0);
		premium.put("connections", 120);
		premium.put("rollback", true);
		premium.put("high avaliability", true);
		premium.put("database forks", true);
		premium.put("price ($/mon)", 200.0);
		obj.put("premium-0", premium);

		return obj;
	}
	
	private JSONObject initMongoDB() {
		JSONObject obj = new JSONObject();
		
		JSONObject sandbox = new JSONObject();
		sandbox.put("size", 0.496);
		sandbox.put("monitoring", false);
		sandbox.put("dedicated", false);
		sandbox.put("high avaliability", false);
		sandbox.put("log", false);
		sandbox.put("price ($/mon)", 0.0);
		obj.put("sandbox", sandbox);
		
		JSONObject sharecluster1 = new JSONObject();
		sharecluster1.put("size", 1.0);
		sharecluster1.put("monitoring", true);
		sharecluster1.put("dedicated", true);
		sharecluster1.put("high avaliability", true);
		sharecluster1.put("log", true);
		sharecluster1.put("price ($/mon)", 18.0);
		obj.put("shared-cluster-1", sharecluster1);
		
		JSONObject sharecluster2 = new JSONObject();
		sharecluster2.put("size", 2.0);
		sharecluster2.put("monitoring", true);
		sharecluster2.put("dedicated", true);
		sharecluster2.put("high avaliability", true);
		sharecluster2.put("log", true);
		sharecluster2.put("price ($/mon)", 36.0);
		obj.put("shared-cluster-2", sharecluster2);
		
		JSONObject sharecluster4 = new JSONObject();
		sharecluster4.put("size", 4.0);
		sharecluster4.put("monitoring", true);
		sharecluster4.put("dedicated", true);
		sharecluster4.put("high avaliability", true);
		sharecluster4.put("log", true);
		sharecluster4.put("price ($/mon)", 72.0);
		obj.put("shared-cluster-4", sharecluster4);
		
		return obj;	
	}
	
	private JSONObject initRedis() {
		JSONObject obj = new JSONObject();
		
		JSONObject hobbyDev = new JSONObject();
		hobbyDev.put("size (GB)", 0.025);
		hobbyDev.put("connections", 20);
		hobbyDev.put("high avaliability", false);
		hobbyDev.put("persistence", false);
		hobbyDev.put("price ($/mon)", 0.0);
		obj.put("hobby-dev", hobbyDev);
		
		JSONObject P0 = new JSONObject();
		P0.put("size (GB)", 0.05);
		P0.put("connections", 40);
		P0.put("high avaliability", true);
		P0.put("persistence", true);
		P0.put("price ($/mon)", 15.0);
		obj.put("premium-0", P0);
		
		JSONObject P1 = new JSONObject();
		P1.put("size (GB)", 0.1);
		P1.put("connections", 80);
		P1.put("high avaliability", true);
		P1.put("persistence", true);
		P1.put("price ($/mon)", 30.0);
		obj.put("premium-1", P1);
		
		JSONObject P2 = new JSONObject();
		P2.put("size (GB)", 0.25);
		P2.put("connections", 200);
		P2.put("high avaliability", true);
		P2.put("persistence", true);
		P2.put("price ($/mon)", 60.0);
		obj.put("premium-2", P2);
		
		return obj;
	}
	
	private JSONObject initClearDB() {
		JSONObject obj = new JSONObject();
		
		JSONObject ignite = new JSONObject();
		ignite.put("size (GB)", 0.005);
		ignite.put("connections", 10);
		ignite.put("production", false);
		ignite.put("IO support", false);
		ignite.put("time support", false);
		ignite.put("price ($/mon)", 0.0);
		obj.put("ignite", ignite);
		
		JSONObject punch = new JSONObject();
		punch.put("size (GB)", 1.0);
		punch.put("connections", 15);
		punch.put("production", true);
		punch.put("IO support", false);
		punch.put("time support", false);
		punch.put("price ($/mon)", 10.0);
		obj.put("punch", punch);
		
		JSONObject drift = new JSONObject();
		drift.put("size (GB)", 5.0);
		drift.put("connections", 30);
		drift.put("production", true);
		drift.put("IO support", true);
		drift.put("time support", false);
		drift.put("price ($/mon)", 50.0);
		obj.put("drift", drift);
		
		JSONObject scream = new JSONObject();
		scream.put("size (GB)", 10.0);
		scream.put("connections", 40);
		scream.put("production", true);
		scream.put("IO support", true);
		scream.put("time support", true);
		scream.put("price ($/mon)", 100.0);
		obj.put("scream", scream);
		
		return obj;
	}
	
	// heroku dynos
	private JSONObject initDynos() {
		JSONObject obj = new JSONObject();
		obj.put("free", initFreeDynos());
		obj.put("hobby", initHobbyDynos());
		obj.put("standard-1x", initStandard1xDynos());
		obj.put("standard-2x", initStandard2xDynos());
		obj.put("performance-m", initPerformanceMDynos());
		obj.put("performance-l", initPerformanceLDynos());
		
		return obj;
	}
	
	private JSONObject initFreeDynos() {
		JSONObject obj = new JSONObject();
		obj.put("professional features(horizontal scalability, application metrics, faster build, preboot)", false);
		obj.put("memory (GB)", 0.5);
		obj.put("cpu", "1x");
		obj.put("price ($/mon)", 0.0);
		
		return obj;
	}
	
	private JSONObject initHobbyDynos() {
		JSONObject obj = new JSONObject();
		obj.put("professional features(horizontal scalability, application metrics, faster build, preboot)", false);
		obj.put("memory (GB)", 0.5);
		obj.put("cpu", "1x");
		obj.put("price ($/mon)", 7.0);
		
		return obj;
	}
	
	private JSONObject initStandard1xDynos() {
		JSONObject obj = new JSONObject();
		obj.put("professional features(horizontal scalability, application metrics, faster build, preboot)", true);
		obj.put("memory (GB)", 0.5);
		obj.put("cpu", "1x");
		obj.put("price ($/mon)", 2.5);
		
		return obj;
	}
	
	private JSONObject initStandard2xDynos() {
		JSONObject obj = new JSONObject();
		obj.put("professional features(horizontal scalability, application metrics, faster build, preboot)", true);
		obj.put("memory (GB)", 1.0);
		obj.put("cpu", "2x");
		obj.put("price ($/mon)", 50.0);
		
		return obj;
	}
	
	private JSONObject initPerformanceMDynos() {
		JSONObject obj = new JSONObject();
		obj.put("professional features(horizontal scalability, application metrics, faster build, preboot)", true);
		obj.put("memory (GB)", 2.5);
		obj.put("cpu", "100%");
		obj.put("price ($/mon)", 250.0);
		
		return obj;
	}
	
	private JSONObject initPerformanceLDynos() {
		JSONObject obj = new JSONObject();
		obj.put("professional features(horizontal scalability, application metrics, faster build, preboot)", true);
		obj.put("memory (GB)", 14.0);
		obj.put("cpu", "100%");
		obj.put("price ($/mon)", 500.0);
		
		return obj;
	}

	public static void main(String[] args) {
		new ConfigurationJSON();
	}
}
