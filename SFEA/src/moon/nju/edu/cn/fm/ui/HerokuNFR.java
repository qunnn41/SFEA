package moon.nju.edu.cn.fm.ui;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeSet;

import moon.nju.edu.cn.fm.script.HerokuScript;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class HerokuNFR {
	private String PRICE = "price ($/mon)";
	private Shell herokuShell;
	
	private List<String> features;
	private JSONObject jsonRoot;
	private Map<String, JSONObject> configMap;
	private Map<String, Map<String, Object>> selectedConfig;
	
	private String execution;
	private String appName;
	private Map<String, String> addons;
	private String dynoType;
	private int dynoSize;
	
	private Button doneButton, cancelButton;
	
	public HerokuNFR(String execution, String appName, Display display, String[] features) {
		this.execution = execution;
		this.appName = appName;
		this.features = Arrays.asList(features);
		this.addons = new HashMap<String, String>();
		this.configMap = new HashMap<String, JSONObject>();
		this.selectedConfig = new HashMap<String, Map<String,Object>>();
		readJSON();
		setupUI(display);
	}
	
	private void readJSON() {
		JSONParser parser = new JSONParser();
		try {
			jsonRoot = (JSONObject) parser.parse(new FileReader("config.json"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setupUI(Display display) {
		herokuShell = new Shell(display);
		herokuShell.setLayout(new GridLayout(1, false));
		
		setupConfig(jsonRoot, "dynos");
		if (features.contains("Database")) {
			JSONObject obj = (JSONObject) jsonRoot.get("database");
			if (features.contains("ClearDB")) {
				setupConfig(obj, "ClearDB");
			} 
			
			if (features.contains("MongoDB")) {
				setupConfig(obj, "MongoDB");
			}
		}
		
		Composite composite = new Composite(herokuShell, SWT.BORDER);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		composite.setLayout(new GridLayout(2, true));
		doneButton = new Button(composite, SWT.PUSH);
		doneButton.setText("Done");
		
		cancelButton = new Button(composite, SWT.PUSH);
		cancelButton.setText("Cancel");
		
		herokuShell.pack();
		herokuShell.open();
		
		setupListeners();
	}
	
	private void setupListeners() {
		doneButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				extractConfig();
				try {
					new HerokuScript(execution, appName, addons, dynoType, dynoSize);
					MessageDialog.openInformation(herokuShell, "Script", "Script Generation is done");
					herokuShell.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		cancelButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				herokuShell.close();
			}
		});
	}
	
	private void extractConfig() {
		double totalPrice = 0.0;
		for (String config: selectedConfig.keySet()) {
//			for (String key: selectedConfig.get(config).keySet()) {
//				System.out.println(config + "\t" + key + "\t" + selectedConfig.get(config).get(key));
//			}
			
			PriorityQueue<PriceInfo> queue = new PriorityQueue<PriceInfo>(16, new Comparator<PriceInfo>() {
				@Override
				public int compare(PriceInfo o1, PriceInfo o2) {
					return (int)o1.price - (int)o2.price;
				}
			});
			
			JSONObject jsonObject = configMap.get(config);
			for (Object typeObject: jsonObject.keySet()) {
				JSONObject type = (JSONObject) jsonObject.get(typeObject);
				
				boolean flag = true;
				for (String key: selectedConfig.get(config).keySet()) {
					Object selectedValue = selectedConfig.get(config).get(key);
					Object providedValue = type.get(key);
					
					if (selectedValue instanceof String) {
						flag = selectedValue.toString().equals(providedValue.toString());
					} else if (selectedValue instanceof Double) {
						flag = (Double)selectedValue <= (Double)providedValue;
					} else if (selectedValue instanceof Boolean) {
						if ((Boolean)selectedValue == true) {
							flag = (Boolean) providedValue;
						}
					}
					
					if (!flag) {
//						System.out.println("Violated: " + config + "\t" + typeObject + "\t" + key + "\t" + selectedValue + "\t" + providedValue);
						break;
					}
				}
				
				if (flag) {
					// satisfy
					queue.add(new PriceInfo(typeObject.toString(), (Double)type.get(PRICE)));
				}
			}
			
			PriceInfo info = queue.poll();
			System.out.println("selected: " + config.toLowerCase() + "\t" + info.name);
			addons.put(config.toLowerCase(), info.name);
			totalPrice += info.price;
		}
		
		dynoType = addons.get("dynos");
		dynoSize = 1;
		
		MessageDialog.openConfirm(herokuShell, "Price Info", "It will cost you " + totalPrice + "$/month");
	}
	
	private class PriceInfo{
		String name;
		double price;
		
		public PriceInfo(String name, double price) {
			this.name = name;
			this.price = price;
		}
	}
	
	private void setupConfig(JSONObject json, final String config) {
		JSONObject jsonObj = (JSONObject)json.get(config);
		configMap.put(config, jsonObj);
		selectedConfig.put(config, new HashMap<String, Object>());
		Group dynoGroup = new Group(herokuShell, SWT.BORDER);
		dynoGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		dynoGroup.setLayout(new GridLayout(1, true));
		dynoGroup.setText(config);
		
		Map<String, TreeSet<Object>> values = getValues(jsonObj);
		for (final String key: values.keySet()) {
			Group group = new Group(dynoGroup, SWT.BORDER);
			group.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
			group.setLayout(new GridLayout(values.get(key).size(), true));
			group.setText(key);
			
			for (final Object value: values.get(key)) {
				Button button = new Button(group, SWT.RADIO);
				button.setText(value.toString());
				
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						selectedConfig.get(config).put(key, value);
					}
				});
			}
		}
	}
	
	private Map<String, TreeSet<Object>> getValues(JSONObject obj) {
		Map<String, TreeSet<Object>> result = new HashMap<String, TreeSet<Object>>();
		for (Object typeObj: obj.keySet()) {
			JSONObject details = (JSONObject) obj.get(typeObj);
			for (Object keyObj: details.keySet()) {
				String key = (String) keyObj;
				if (!result.containsKey(key)) {
					result.put(key, new TreeSet<Object>());
				}
				
				result.get(key).add(details.get(key));
			}
		}
		
		result.remove(PRICE);
		return result;
	}
}
