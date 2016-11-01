package moon.nju.edu.cn.fm.script;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HerokuScript {
	public HerokuScript(String procfile, String appName, Map<String, String> addons, String dynoType, int dynoSize) throws IOException {
		FileWriter file = new FileWriter("derivation/command.sh");
		file.write("#!/bin/bash\n");
		file.write("echo \"web: " + procfile + "\" > Procfile\n");
		file.write("git init\n");
		file.write("git add -A\n");
		file.write("git commit -m \"first commit\"\n");
		file.write("heroku create " + appName + "\n");
		
		for (String addon: addons.keySet()) {
			String type = addons.get(addon);
			if (addon.equals("cleardb")) {
				file.write("heroku addons:create cleardb:" + type + "\n");
				file.write("url=$(heroku config | grep CLEARDB_DATABASE_URL)\n");
				file.write("IFS=\": // / ? @\"\n");
				file.write("set $url\n");
				file.write("array=$@\n");
				file.write("db_user=$(echo $array | cut -d \" \" -f 3)\n");
				file.write("db_password=$(echo $array | cut -d \" \" -f 4)\n");
				file.write("db_host=$(echo $array | cut -d \" \" -f 5)\n");
				file.write("db_database=$(echo $array | cut -d \" \" -f 6)\n");
				file.write("heroku config:set DB_USER=$db_user DB_PASSWORD=$db_password DB_HOST=$db_host DB_DATABASE=$db_database\n");
			} else if (addon.equals("mongodb")) {
				file.write("heroku addons:create mongolab:" + type + "\n");
			} else if (addon.equals("redis")) {
				file.write("heroku addons:create redis:" + type + "\n");
			} else if (addon.equals("postgres")) {
				file.write("heroku addons:create heroku-postgresql:" + type + "\n");
			} else if (addon.equals("memcache")) {
				file.write("heroku addons:create memcachier:" + type + "\n");
			} else if (addon.equals("ironcache")) {
				file.write("heroku addons:create iron cache:" + type + "\n");
			}
		}
		
		file.write("git push heroku master\n");
		file.write("heroku dyno:type " + dynoType + "\n");
		file.write("heroku ps: scale web=" + dynoSize + "\n");
		file.write("heroku open\n");
		
		file.flush();
		file.close();
		
		Runtime.getRuntime().exec("chmod u+x derivation/command.sh");
	}
	
	public static void main(String[] args) throws IOException {
		Map<String, String> addons = new HashMap<String, String>();
		addons.put("cleardb", "ignite");
		new HerokuScript("node web.js", "wyq-app-name", addons, "free", 1);
	}
}
