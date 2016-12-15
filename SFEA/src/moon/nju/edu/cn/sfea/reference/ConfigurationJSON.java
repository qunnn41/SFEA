package moon.nju.edu.cn.sfea.reference;

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
		root.put("log", initLog());
		root.put("monitoring", initMonitoring());
		root.put("protocol", initProtocol());
		root.put("network", initNetwork());
		root.put("security", initSecurity());
		
		try {
			FileWriter file = new FileWriter("config.json");
			file.write(root.toJSONString());
			file.flush();
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private JSONObject initSecurity() {
		JSONObject object = new JSONObject();
		
		object.put("ssl", initSSL());
		object.put("securekey", initSecureKey());
		
		return object;
	}
	
	private JSONObject initSSL() {
		JSONObject obj = new JSONObject();
		
		JSONObject ssl = new JSONObject();
		ssl.put("Use with any hostname on your domain", true);
		ssl.put("User your own SSL certificate", true);
		ssl.put("price ($/mon)", 20.0);
		
		obj.put("endpoint", ssl);
		
		return obj;
	}
	
	private JSONObject initSecureKey() {
		JSONObject object = new JSONObject();
		
		JSONObject fornight = new JSONObject();
		fornight.put("Rotation Interval (Days)", 14);
		fornight.put("price ($/mon)", 0.0);
		object.put("fortnightly", fornight);
		
		return object;
	}
	
	private JSONObject initNetwork() {
		JSONObject obj = new JSONObject();
		obj.put("fixie", initFixie());
		obj.put("proximo", initProximo());
		obj.put("pointdns", initPointDNS());
		return obj;
	}
	
	private JSONObject initPointDNS() {
		JSONObject obj = new JSONObject();
		
		JSONObject dev = new JSONObject();
		dev.put("domains", 1);
		dev.put("record", 10);
		dev.put("mail redirects", false);
		dev.put("queries quota", 10);
		dev.put("price ($/mon)", 0.0);
		obj.put("developer", dev);
		
		JSONObject small = new JSONObject();
		small.put("domains", 10);
		small.put("record", 10000);
		small.put("mail redirects", true);
		small.put("queries quota", 1000);
		small.put("price ($/mon)", 15.0);
		obj.put("small", small);
		
		JSONObject medium = new JSONObject();
		medium.put("domains", 50);
		medium.put("record", 10000);
		medium.put("mail redirects", true);
		medium.put("queries quota", 5000);
		medium.put("price ($/mon)", 35.0);
		obj.put("medium", medium);
		
		JSONObject large = new JSONObject();
		large.put("domains", 500);
		large.put("record", 10);
		large.put("mail redirects", true);
		large.put("queries quota", 10000);
		large.put("price ($/mon)", 95.0);
		obj.put("large", large);
		
		return obj;
	}
	
	private JSONObject initProximo() {
		JSONObject object = new JSONObject();
		
		JSONObject dev = new JSONObject();
		dev.put("Request per month", 2000);
		dev.put("Bandwidth per month (GB)", 0.5);
		dev.put("price ($/mon)", 5.0);
		object.put("development", dev);
		
		JSONObject starter = new JSONObject();
		starter.put("Request per month", 50000);
		starter.put("Bandwidth per month (GB)", 20.0);
		starter.put("price ($/mon)", 25.0);
		object.put("starter", starter);
		
		JSONObject professional = new JSONObject();
		professional.put("Request per month", 500000);
		professional.put("Bandwidth per month (GB)", 200.0);
		professional.put("price ($/mon)", 75.0);
		object.put("professional", professional);
		
		JSONObject advanced = new JSONObject();
		advanced.put("Request per month", 1250000);
		advanced.put("Bandwidth per month (GB)", 500.0);
		advanced.put("price ($/mon)", 150.0);
		object.put("advanced", advanced);
		
		return object;
	}
	
	private JSONObject initFixie() {
		JSONObject object = new JSONObject();
		
		JSONObject tricycle = new JSONObject();
		tricycle.put("Request per month", 500);
		tricycle.put("Bandwidth per month (GB)", 0.1);
		tricycle.put("price ($/mon)", 0.0);
		object.put("tricycle", tricycle);
		
		JSONObject commuter = new JSONObject();
		commuter.put("Request per month", 2500);
		commuter.put("Bandwidth per month (GB)", 0.5);
		commuter.put("price ($/mon)", 5.0);
		object.put("commuter", commuter);
		
		JSONObject cruiser = new JSONObject();
		cruiser.put("Request per month", 25000);
		cruiser.put("Bandwidth per month (GB)", 10.0);
		cruiser.put("price ($/mon)", 19.0);
		object.put("cruiser", cruiser);
		
		JSONObject hybrid = new JSONObject();
		hybrid.put("Request per month", 250000);
		hybrid.put("Bandwidth per month (MG)", 50.0);
		hybrid.put("price ($/mon)", 49.0);
		object.put("hybrid", hybrid);
		
		return object;
	}
	
	private JSONObject initProtocol() {
		JSONObject obj = new JSONObject();
		obj.put("cloudAMQP", initCloudAMQP());
		obj.put("rabbitMQ", initRabbitMQ());
		
		return obj;
	}
	
	private JSONObject initCloudAMQP() {
		JSONObject obj = new JSONObject();
		
		JSONObject lemur = new JSONObject();
		lemur.put("Message per month", 1000000);
		lemur.put("Concurrent connections", 20);
		lemur.put("Queued messages", 10000);
		lemur.put("Queues", 1000);
		lemur.put("price ($/mon)", 0.0);
		obj.put("lemur", lemur);
		
		JSONObject tiger = new JSONObject();
		tiger.put("Message per month", 10000000);
		tiger.put("Concurrent connections", 100);
		tiger.put("Queued messages", 100000);
		tiger.put("Queues", 1000);
		tiger.put("price ($/mon)", 19.0);
		obj.put("tiger", tiger);
		
		JSONObject bunny = new JSONObject();
		bunny.put("Message per month", 100000000);
		bunny.put("Concurrent connections", 5000);
		bunny.put("Queued messages", 1000000);
		bunny.put("Queues", 10000);
		bunny.put("price ($/mon)", 99.0);
		obj.put("bunny", bunny);
		
		return obj;
	}
	
	private JSONObject initRabbitMQ() {
		JSONObject object = new JSONObject();
		
		JSONObject pipkin = new JSONObject();
		pipkin.put("Send Rate (MB/s)", 0.2);
		pipkin.put("SSL", false);
		pipkin.put("Memory threshold (GB)", 0.03);
		pipkin.put("vCPUs per provision", 0.003);
		pipkin.put("price ($/mon)", 0.0);
		object.put("pipkin", pipkin);
		
		JSONObject speedwell = new JSONObject();
		speedwell.put("Send Rate (MB/s)", 1.0);
		speedwell.put("SSL", false);
		speedwell.put("Memory threshold (GB)", 0.19);
		speedwell.put("vCPUs per provision", 0.04);
		speedwell.put("price ($/mon)", 20.0);
		object.put("speedwell", speedwell);
		
		JSONObject toadflax = new JSONObject();
		toadflax.put("Send Rate (MB/s)", 100.0);
		toadflax.put("SSL", true);
		toadflax.put("Memory threshold (GB)", 0.69);
		toadflax.put("vCPUs per provision", 1.0);
		toadflax.put("price ($/mon)", 100.0);
		object.put("toadflax", toadflax);
		
		JSONObject thunder = new JSONObject();
		thunder.put("Send Rate (MB/s)", 100.0);
		thunder.put("SSL", true);
		thunder.put("Memory threshold (GB)", 3.1);
		pipkin.put("vCPUs per provision", 2.0);
		thunder.put("price ($/mon)", 400.0);
		thunder.put("pipkin", pipkin);
		
		return object;
	}
	
	private JSONObject initMonitoring() {
		JSONObject object = new JSONObject();
		object.put("scout", initScout());
		object.put("librato", initLibrato());
		object.put("pingdom", initPingdom());
		
		return object;
	}
	
	private JSONObject initScout() {
		JSONObject object = new JSONObject();

		JSONObject chair = new JSONObject();
		chair.put("Deploy tracking", false);
		chair.put("Metric Retention (Hours)", 24);
		chair.put("Transactions per day", 10000);
		chair.put("Trace Retention (Hours)", 1);
		chair.put("price ($/mon)", 0.0);
		object.put("chair", chair);
		
		JSONObject eldora = new JSONObject();
		eldora.put("Deploy tracking", true);
		eldora.put("Metric Retention (Hours)", 24);
		eldora.put("Transactions per day", 65000);
		eldora.put("Trace Retention (Hours)", 24);
		eldora.put("price ($/mon)", 39.0);
		object.put("eldora", eldora);
		
		JSONObject loveland = new JSONObject();
		loveland.put("Deploy tracking", true);
		loveland.put("Metric Retention (Hours)", 30);
		loveland.put("Transactions per day", 100000);
		loveland.put("Trace Retention (Hours)", 168);
		loveland.put("price ($/mon)", 59.0);
		object.put("skiloveland", loveland);
		
		JSONObject keystone = new JSONObject();
		keystone.put("Deploy tracking", false);
		keystone.put("Metric Retention (Hours)", 30);
		keystone.put("Transactions per day", 130000);
		keystone.put("Trace Retention (Hours)", 168);
		keystone.put("price ($/mon)", 79.0);
		object.put("keystone", keystone);
		
		return object;
	}
	
	private JSONObject initLibrato() {
		JSONObject obj = new JSONObject();
		
		JSONObject dev = new JSONObject();
		dev.put("Days retention ", 1);
		dev.put("Metric Stream at 60s resolution", 0);
		dev.put("Custom metrics", false);
		dev.put("Custom Annotations", false);
		dev.put("price ($/mon)", 0.0);
		obj.put("development", dev);
		
		JSONObject nickel = new JSONObject();
		nickel.put("Days retention ", 365);
		nickel.put("Metric Stream at 60s resolution", 20);
		nickel.put("Custom metrics", true);
		nickel.put("Custom Annotations", false);
		nickel.put("price ($/mon)", 19.0);
		obj.put("nickel", nickel);
		
		JSONObject bronze = new JSONObject();
		bronze.put("Days retention ", 365);
		bronze.put("Metric Stream at 60s resolution", 30);
		bronze.put("Custom metrics", true);
		bronze.put("Custom Annotations", true);
		bronze.put("price ($/mon)", 29.0);
		obj.put("bronze", bronze);
		
		JSONObject silver = new JSONObject();
		silver.put("Days retention ", 265);
		silver.put("Metric Stream at 60s resolution", 125);
		silver.put("Custom metrics", true);
		silver.put("Custom Annotations", true);
		silver.put("price ($/mon)", 49.0);
		obj.put("silver", silver);
		
		return obj;
	}
	
	private JSONObject initPingdom() {
		JSONObject obj = new JSONObject();
		
		JSONObject starter = new JSONObject();
		starter.put("Uptime checks", 10);
		starter.put("Real User Monitoring Sites", 1);
		starter.put("SMS Credits per month", 50);
		starter.put("Advanced checks", 1);
		starter.put("price ($/mon)", 14.95);
		obj.put("starter", starter);
		
		JSONObject standard = new JSONObject();
		standard.put("Uptime checks", 50);
		standard.put("Real User Monitoring Sites", 5);
		standard.put("SMS Credits per month", 200);
		standard.put("Advanced checks", 3);
		standard.put("price ($/mon)", 45.95);
		obj.put("standard", standard);
		
		JSONObject advanced = new JSONObject();
		advanced.put("Uptime checks", 60);
		advanced.put("Real User Monitoring Sites", 5);
		advanced.put("SMS Credits per month", 350);
		advanced.put("Advanced checks", 5);
		advanced.put("price ($/mon)", 89.95);
		obj.put("advanced", advanced);
		
		JSONObject professional = new JSONObject();
		professional.put("Uptime checks", 10);
		professional.put("Real User Monitoring Sites", 1);
		professional.put("SMS Credits per month", 50);
		professional.put("Advanced checks", 1);
		professional.put("price ($/mon)", 15.00);
		obj.put("professional", professional);
		
		return obj;
	}
	
	private JSONObject initLog() {
		JSONObject obj = new JSONObject();
		obj.put("papertrail", initPapertrail());
		obj.put("LogDNA", initLogDNA());
		
		return obj;
	}
	
	private JSONObject initPapertrail() {
		JSONObject obj = new JSONObject();
		
		JSONObject choklad = new JSONObject();
		choklad.put("Log volume per day (MB)", 10.0);
		choklad.put("Search Duration (Days)", 2);
		choklad.put("Archive duration (Days)", 7);
		choklad.put("price ($/mon)", 0.0);
		obj.put("choklad", choklad);
		
		JSONObject fixa = new JSONObject();
		fixa.put("Log volume per day (MB)", 50.0);
		fixa.put("Search Duration (Days)", 7);
		fixa.put("Archive duration (Days)", 365);
		fixa.put("price ($/mon)", 7.0);
		obj.put("fixa", fixa);
		
		JSONObject ludvig = new JSONObject();
		ludvig.put("Log volume per day (MB)", 100.0);
		ludvig.put("Search Duration (Days)", 7);
		ludvig.put("Archive duration (Days)", 365);
		ludvig.put("price ($/mon)", 15.0);
		obj.put("ludvig", ludvig);
		
		JSONObject forsta = new JSONObject();
		forsta.put("Log volume per day (MB)", 200.0);
		forsta.put("Search Duration (Days)", 7);
		forsta.put("Archive duration (Days)", 365);
		forsta.put("price ($/mon)", 29.0);
		obj.put("forsta", forsta);
		
		return obj;
	}
	
	private JSONObject initLogDNA() {
		JSONObject obj = new JSONObject();
		
		JSONObject quaco = new JSONObject();
		quaco.put("Storage volume per day(MB)", 50.0);
		quaco.put("Search Retention", 5);
		quaco.put("Users", 1);
		quaco.put("Unlimited Saved Views", false);
		quaco.put("Real Time Alert", false);
		quaco.put("price ($/mon)", 0.0);
		obj.put("quaco", quaco);
		
		JSONObject zepto = new JSONObject();
		zepto.put("Storage volume per day(MB)", 90.0);
		zepto.put("Search Retention", 7);
		zepto.put("Users", 5);
		zepto.put("Unlimited Saved Views", true);
		zepto.put("Real Time Alert", true);
		zepto.put("price ($/mon)", 5.0);
		obj.put("zepto", zepto);
		
		JSONObject atto = new JSONObject();
		atto.put("Storage volume per day(MB)", 175.0);
		atto.put("Search Retention", 7);
		atto.put("Users", 5);
		atto.put("Unlimited Saved Views", true);
		atto.put("Real Time Alert", true);
		atto.put("price ($/mon)", 10.0);
		obj.put("atto", atto);
		
		JSONObject femto = new JSONObject();
		femto.put("Storage volume per day(MB)", 50.0);
		femto.put("Search Retention", 5);
		femto.put("Users", 1);
		femto.put("Unlimited Saved Views", false);
		femto.put("Real Time Alert", false);
		femto.put("price ($/mon)", 0.0);
		obj.put("femto", femto);
		
		return obj;
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
