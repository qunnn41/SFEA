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
		
		try {
			FileWriter file = new FileWriter("config.json");
			file.write(root.toJSONString());
			file.flush();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// database
	private JSONObject initDatabase() {
		JSONObject obj = new JSONObject();
		obj.put("clearDB", initClearDB());
		obj.put("Redis", initRedis());
		obj.put("MongoDB", initMongoDB());
		
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
		sandbox.put("prize", 0.0);
		obj.put("sandbox", sandbox);
		
		JSONObject sharecluster1 = new JSONObject();
		sharecluster1.put("size", 1);
		sharecluster1.put("monitoring", true);
		sharecluster1.put("dedicated", true);
		sharecluster1.put("high avaliability", true);
		sharecluster1.put("log", true);
		sharecluster1.put("prize", 18.0);
		obj.put("sharecluster1", sharecluster1);
		
		JSONObject sharecluster2 = new JSONObject();
		sharecluster2.put("size", 2);
		sharecluster2.put("monitoring", true);
		sharecluster2.put("dedicated", true);
		sharecluster2.put("high avaliability", true);
		sharecluster2.put("log", true);
		sharecluster2.put("prize", 36.0);
		obj.put("sharecluster2", sharecluster2);
		
		JSONObject sharecluster4 = new JSONObject();
		sharecluster4.put("size", 4);
		sharecluster4.put("monitoring", true);
		sharecluster4.put("dedicated", true);
		sharecluster4.put("high avaliability", true);
		sharecluster4.put("log", true);
		sharecluster4.put("prize", 72.0);
		obj.put("sharecluster4", sharecluster4);
		
		return obj;	
	}
	
	private JSONObject initRedis() {
		JSONObject obj = new JSONObject();
		
		JSONObject hobbyDev = new JSONObject();
		hobbyDev.put("size", 0.025);
		hobbyDev.put("connections", 20);
		hobbyDev.put("high avaliability", false);
		hobbyDev.put("persistence", false);
		hobbyDev.put("prize", 0.0);
		obj.put("hobby", hobbyDev);
		
		JSONObject P0 = new JSONObject();
		P0.put("size", 0.05);
		P0.put("connections", 40);
		P0.put("high avaliability", true);
		P0.put("persistence", true);
		P0.put("prize", 15.0);
		obj.put("Premium0", P0);
		
		JSONObject P1 = new JSONObject();
		P1.put("size", 0.1);
		P1.put("connections", 80);
		P1.put("high avaliability", true);
		P1.put("persistence", true);
		P1.put("prize", 30.0);
		obj.put("Premium1", P1);
		
		JSONObject P2 = new JSONObject();
		P2.put("size", 0.25);
		P2.put("connections", 200);
		P2.put("high avaliability", true);
		P2.put("persistence", true);
		P2.put("prize", 60.0);
		obj.put("Premium2", P2);
		
		return obj;
	}
	
	private JSONObject initClearDB() {
		JSONObject obj = new JSONObject();
		
		JSONObject ignite = new JSONObject();
		ignite.put("size", 0.005);
		ignite.put("connections", 10);
		ignite.put("backup", true);
		ignite.put("production", false);
		ignite.put("IO support", false);
		ignite.put("time support", false);
		ignite.put("prize", 0.0);
		obj.put("ignite", ignite);
		
		JSONObject punch = new JSONObject();
		punch.put("size", 1.0);
		punch.put("connections", 15);
		punch.put("backup", true);
		punch.put("production", true);
		punch.put("IO support", false);
		punch.put("time support", false);
		punch.put("prize", "10");
		obj.put("punch", punch);
		
		JSONObject drift = new JSONObject();
		drift.put("size", 5.0);
		drift.put("connections", 30);
		drift.put("backup", true);
		drift.put("production", true);
		drift.put("IO support", true);
		drift.put("time support", false);
		drift.put("prize", 50.0);
		obj.put("drift", drift);
		
		JSONObject scream = new JSONObject();
		scream.put("size", 10.0);
		scream.put("connections", 40);
		scream.put("backup", true);
		scream.put("production", true);
		scream.put("IO support", true);
		scream.put("time support", true);
		scream.put("prize", 100.0);
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
		obj.put("sleep", true);
		obj.put("professional features(horizontal scalability, application metrics, faster build, preboot)", false);
		obj.put("memory", 0.5);
		obj.put("cpu", "1x");
		obj.put("price", 0.0);
		
		return obj;
	}
	
	private JSONObject initHobbyDynos() {
		JSONObject obj = new JSONObject();
		obj.put("sleep", false);
		obj.put("professional features(horizontal scalability, application metrics, faster build, preboot)", false);
		obj.put("memory", 0.5);
		obj.put("cpu", "1x");
		obj.put("price", 7.0);
		
		return obj;
	}
	
	private JSONObject initStandard1xDynos() {
		JSONObject obj = new JSONObject();
		obj.put("sleep", false);
		obj.put("professional features(horizontal scalability, application metrics, faster build, preboot)", true);
		obj.put("memory", 0.5);
		obj.put("cpu", "1x");
		obj.put("price", 2.5);
		
		return obj;
	}
	
	private JSONObject initStandard2xDynos() {
		JSONObject obj = new JSONObject();
		obj.put("sleep", false);
		obj.put("professional features(horizontal scalability, application metrics, faster build, preboot)", true);
		obj.put("memory", 1.0);
		obj.put("cpu", "2x");
		obj.put("price", 50.0);
		
		return obj;
	}
	
	private JSONObject initPerformanceMDynos() {
		JSONObject obj = new JSONObject();
		obj.put("sleep", false);
		obj.put("professional features(horizontal scalability, application metrics, faster build, preboot)", true);
		obj.put("memory", 2.5);
		obj.put("cpu", "100%");
		obj.put("price", 250.0);
		
		return obj;
	}
	
	private JSONObject initPerformanceLDynos() {
		JSONObject obj = new JSONObject();
		obj.put("sleep", false);
		obj.put("professional features(horizontal scalability, application metrics, faster build, preboot)", true);
		obj.put("memory", 14.0);
		obj.put("cpu", "100%");
		obj.put("price", 500.0);
		
		return obj;
	}

	public static void main(String[] args) {
		new ConfigurationJSON();
	}
}
