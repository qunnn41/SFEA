package moon.nju.edu.cn.sfea.app;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import moon.nju.edu.cn.sfea.consistency.GoogleAppEngineConsist;
import moon.nju.edu.cn.sfea.consistency.HerokuConsist;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
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
import org.eclipse.swt.widgets.Text;

public class SFEAApplication {
	private Display sfeaDisplay;
	private Shell sfeaShell;
	private final Set<String> featureSelected = new HashSet<String>();
	
	private Button deployButton, resetButton;
	private Group languageGroup, frameworkGroup, SQLGroup, NoSQLGroup, cacheGroup, logGroup, protocolGroup, monitorGroup, securityGroup, networkGroup;
	private Button javaButton, pythonButton, rubyButton, scalaButton, jsButton, phpButton, goButton;
	private Button springButton, railsButton, playButton, nodejsButton;
	private Button postgresButton, clearDBButton, redisButton, mongoDBButton;
	private Button ironCacheButton, memCacheButton;
	private Button papertrailButton, logdnaButton;
	private Button cloudAMQPButton, rabbitMQButton;
	private Button scoutButton, libratoButton, pingdomButton;
	private Button sslButton, secureKeyButton;
	private Button fixieButton, proximoButton, pointDNSButton;
	private Text applicationText, executionText;
	
	public SFEAApplication() {
		sfeaDisplay = new Display();
		sfeaShell = new Shell(sfeaDisplay);
		sfeaShell.setLayout(new GridLayout(1, false));
		
		this.setupInputArea();
		this.setupLanugage();
		this.setupFramework();
		this.setupDatabase();
		this.setupCaching();
		this.setupLog();
		this.setupMonitoring();
		this.setupProtocol();
		this.setupNetwork();
		this.setupSecurity();
		
		Composite composite = new Composite(sfeaShell, SWT.BORDER);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		composite.setLayout(new GridLayout(2, true));
		deployButton = new Button(composite, SWT.PUSH);
		deployButton.setText("Deploy");
		
		resetButton = new Button(composite, SWT.PUSH);
		resetButton.setText("Reset");
		
		this.setupListeners();
	}
	
	private void setupInputArea() {
		Group applicationGroup = new Group(sfeaShell, SWT.NONE);
		applicationGroup.setText("Application Name");
		applicationGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		applicationGroup.setLayout(new GridLayout(1, true));
		applicationText = new Text(applicationGroup, SWT.NONE);
		applicationText.setText("");
		applicationText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		
		Group executionGroup = new Group(sfeaShell, SWT.NONE);
		executionGroup.setText("Execution Command");
		executionGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		executionGroup.setLayout(new GridLayout(1, true));
		executionText = new Text(executionGroup, SWT.NONE);
		executionText.setText("");
		executionText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
	}
	
	private void setupLanugage() {
		languageGroup = new Group(sfeaShell, SWT.NONE);
		languageGroup.setText("Language");
		languageGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		languageGroup.setLayout(new GridLayout(4, true));
		javaButton = new Button(languageGroup, SWT.CHECK);
		javaButton.setText("Java");
		pythonButton = new Button(languageGroup, SWT.CHECK);
		pythonButton.setText("Python");
		rubyButton = new Button(languageGroup, SWT.CHECK);
		rubyButton.setText("Ruby");
		scalaButton = new Button(languageGroup, SWT.CHECK);
		scalaButton.setText("Scala");
		jsButton = new Button(languageGroup, SWT.CHECK);
		jsButton.setText("Javascript");
		phpButton = new Button(languageGroup, SWT.CHECK);
		phpButton.setText("PHP");
		goButton = new Button(languageGroup, SWT.CHECK);
		goButton.setText("Go");
	}
	
	private void setupLog() {
		logGroup = new Group(sfeaShell, SWT.NONE);
		logGroup.setText("Logging");
		logGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		logGroup.setLayout(new GridLayout(4, true));
		papertrailButton = new Button(logGroup, SWT.CHECK);
		papertrailButton.setText("Papertrail");
		logdnaButton = new Button(logGroup, SWT.CHECK);
		logdnaButton.setText("LogDNA");
	}
	
	private void setupProtocol() {
		protocolGroup = new Group(sfeaShell, SWT.NONE);
		protocolGroup.setText("Protocol");
		protocolGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		protocolGroup.setLayout(new GridLayout(4, true));
		cloudAMQPButton = new Button(protocolGroup, SWT.CHECK);
		cloudAMQPButton.setText("cloudAMQP");
		rabbitMQButton = new Button(protocolGroup, SWT.CHECK);
		rabbitMQButton.setText("rabbitMQ");
	}
	
	private void setupMonitoring() {
		monitorGroup = new Group(sfeaShell, SWT.NONE);
		monitorGroup.setText("Monitoring");
		monitorGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		monitorGroup.setLayout(new GridLayout(4, true));
		scoutButton = new Button(monitorGroup, SWT.CHECK);
		scoutButton.setText("Scout");
		libratoButton = new Button(monitorGroup, SWT.CHECK);
		libratoButton.setText("Librato");
		pingdomButton = new Button(monitorGroup, SWT.CHECK);
		pingdomButton.setText("Pingdom");
	}
	
	private void setupSecurity() {
		securityGroup = new Group(sfeaShell, SWT.NONE);
		securityGroup.setText("Security");
		securityGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		securityGroup.setLayout(new GridLayout(4, true));
		sslButton = new Button(securityGroup, SWT.CHECK);
		sslButton.setText("SSL");
		secureKeyButton = new Button(securityGroup, SWT.CHECK);
		secureKeyButton.setText("SecurityKey");
	}
	
	private void setupNetwork() {
		networkGroup = new Group(sfeaShell, SWT.NONE);
		networkGroup.setText("Network");
		networkGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		networkGroup.setLayout(new GridLayout(4, true));
		fixieButton = new Button(networkGroup, SWT.CHECK);
		fixieButton.setText("Fixie");
		proximoButton = new Button(networkGroup, SWT.CHECK);
		proximoButton.setText("Proximo");
		pointDNSButton = new Button(networkGroup, SWT.CHECK);
		pointDNSButton.setText("PointDNS");
	}
	
	private void setupFramework() {
		frameworkGroup = new Group(sfeaShell, SWT.NONE);
		frameworkGroup.setText("Framework");
		frameworkGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		frameworkGroup.setLayout(new GridLayout(4, true));
		springButton = new Button(frameworkGroup, SWT.CHECK);
		springButton.setText("Spring");
		railsButton = new Button(frameworkGroup, SWT.CHECK);
		railsButton.setText("Rails");
		playButton = new Button(frameworkGroup, SWT.CHECK);
		playButton.setText("Play");
		nodejsButton = new Button(frameworkGroup, SWT.CHECK);
		nodejsButton.setText("Node.js");
	}
	
	private void setupDatabase() {
		SQLGroup = new Group(sfeaShell, SWT.NONE);
		SQLGroup.setText("SQL Database");
		SQLGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		SQLGroup.setLayout(new GridLayout(4, true));
		postgresButton = new Button(SQLGroup, SWT.CHECK);
		postgresButton.setText("Postgres");
		clearDBButton = new Button(SQLGroup, SWT.CHECK);
		clearDBButton.setText("ClearDB");
		
		NoSQLGroup = new Group(sfeaShell, SWT.NONE);
		NoSQLGroup.setText("NoSQL Database");
		NoSQLGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		NoSQLGroup.setLayout(new GridLayout(4, true));
		redisButton = new Button(NoSQLGroup, SWT.CHECK);
		redisButton.setText("Redis");
		mongoDBButton = new Button(NoSQLGroup, SWT.CHECK);
		mongoDBButton.setText("MongoDB");
	}
	
	private void setupCaching() {
		cacheGroup = new Group(sfeaShell, SWT.NONE);
		cacheGroup.setText("Cache");
		cacheGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		cacheGroup.setLayout(new GridLayout(4, true));
		ironCacheButton = new Button(cacheGroup, SWT.CHECK);
		ironCacheButton.setText("IronCache");
		memCacheButton = new Button(cacheGroup, SWT.CHECK);
		memCacheButton.setText("MemCache");
	}
	
	private void setupListeners() {
		deployButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				deployApp();
			}
		});
		
		resetButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				featureSelected.clear();
				
				javaButton.setSelection(false);
				pythonButton.setSelection(false);
				rubyButton.setSelection(false);
				scalaButton.setSelection(false);
				jsButton.setSelection(false);
				phpButton.setSelection(false);
				goButton.setSelection(false);
				springButton.setSelection(false);
				railsButton.setSelection(false);
				playButton.setSelection(false);
				nodejsButton.setSelection(false);
				postgresButton.setSelection(false);
				clearDBButton.setSelection(false);
				redisButton.setSelection(false);
				mongoDBButton.setSelection(false);
				ironCacheButton.setSelection(false);
				memCacheButton.setSelection(false);
				papertrailButton.setSelection(false);
				logdnaButton.setSelection(false);
				cloudAMQPButton.setSelection(false);
				rabbitMQButton.setSelection(false);
				scoutButton.setSelection(false);
				libratoButton.setSelection(false);
				pingdomButton.setSelection(false);
				sslButton.setSelection(false);
				secureKeyButton.setSelection(false);
				fixieButton.setSelection(false);
				proximoButton.setSelection(false);
				pointDNSButton.setSelection(false);
				
				applicationText.setText("");
				executionText.setText("");
			}
		});
		
		javaButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage(e, "Java");
			}
		});
		
		pythonButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage(e, "Python");
			}
		});
		
		rubyButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage(e, "Ruby");
			}
		});
		
		scalaButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage(e, "Scala");
			}
		});
		
		jsButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage(e, "Javascript");
			}
		});
		
		phpButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage(e, "PHP");
			}
		});
		
		goButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage(e, "Go");
			}
		});
		
		springButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectFramework(e, "Spring");
			}
		});
		
		railsButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectFramework(e, "Rails");
			}
		});
		
		playButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectFramework(e, "Play");
			}
		});
		
		nodejsButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectFramework(e, "Node.js");
			}
		});
		
		postgresButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectSql(e, "Postgres");
			}
		});
		
		clearDBButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectSql(e, "ClearDB");
			}
		});
		
		redisButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectNoSql(e, "Redis");
			}
		});
		
		mongoDBButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectNoSql(e, "MongoDB");
			}
		});
		
		ironCacheButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectCache(e, "Ironcache");
			}
		});
		
		memCacheButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectCache(e, "Memcache");
			}
		});
		
		papertrailButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLog(e, "Papertrail");
			}
		});
		
		logdnaButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLog(e, "LogDNA");
			}
		});
		
		cloudAMQPButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectProtocol(e, "CloudAMQP");
			}
		});
		
		rabbitMQButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectProtocol(e, "RabbitMQ");
			}
		});
		
		scoutButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectMonitoring(e, "Scout");
			}
		});
		
		libratoButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectMonitoring(e, "Librato");
			}
		});
		
		pingdomButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectMonitoring(e, "Pingdom");
			}
		});
		
		sslButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectSecurity(e, "SSL");
			}
		});
		
		secureKeyButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectSecurity(e, "SecureKey");
			}
		});
		
		fixieButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectNetwork(e, "Fixie");
			}
		});
		
		proximoButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectNetwork(e, "Proximo");
			}
		});
		
		pointDNSButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectNetwork(e, "PointDNS");
			}
		});
	}
	
	private void selectLog(SelectionEvent event, String log) {
		Button button = (Button) event.getSource();
		if (button.getSelection()) {
			featureSelected.add(log);
			featureSelected.add("Log");
			featureSelected.add("Service");
		} else {
			featureSelected.remove(log);
			featureSelected.remove("Log");
			featureSelected.remove("Service");
		}
	}
	
	private void selectProtocol(SelectionEvent event, String protocol) {
		Button button = (Button) event.getSource();
		if (button.getSelection()) {
			featureSelected.add(protocol);
			featureSelected.add("Protocol");
			featureSelected.add("Service");
		} else {
			featureSelected.remove(protocol);
			featureSelected.remove("Protocol");
			featureSelected.remove("Service");
		}
	}
	
	private void selectMonitoring(SelectionEvent event, String monitor) {
		Button button = (Button) event.getSource();
		if (button.getSelection()) {
			featureSelected.add(monitor);
			featureSelected.add("Monitoring");
			featureSelected.add("Service");
		} else {
			featureSelected.remove(monitor);
			featureSelected.remove("Monitoring");
			featureSelected.remove("Service");
		}
	}
	
	private void selectSecurity(SelectionEvent event, String security) {
		Button button = (Button) event.getSource();
		if (button.getSelection()) {
			featureSelected.add(security);
			featureSelected.add("Security");
			featureSelected.add("Service");
		} else {
			featureSelected.remove(security);
			featureSelected.remove("Security");
			featureSelected.remove("Service");
		}
	}
	
	private void selectNetwork(SelectionEvent event, String network) {
		Button button = (Button) event.getSource();
		if (button.getSelection()) {
			featureSelected.add(network);
			featureSelected.add("Network");
			featureSelected.add("Service");
		} else {
			featureSelected.remove(network);
			featureSelected.remove("Network");
			featureSelected.remove("Service");
		}
	}
	
	private void selectLanguage(SelectionEvent event, String language) {
		Button button = (Button) event.getSource();
		if (button.getSelection()) {
			featureSelected.add(language);
			featureSelected.add("Language");
		} else {
			featureSelected.remove(language);
			featureSelected.remove("Language");
		}
	}
	
	private void selectFramework(SelectionEvent event, String framework) {
		Button button = (Button) event.getSource();
		if (button.getSelection()) {
			featureSelected.add(framework);
			featureSelected.add("Framework");
		} else {
			featureSelected.remove(framework);
			featureSelected.remove("Framework");
		}
	}
	
	private void selectSql(SelectionEvent event, String sql) {
		Button button = (Button) event.getSource();
		if (button.getSelection()) {
			featureSelected.add(sql);
			featureSelected.add("SQL");
			featureSelected.add("Database");
			featureSelected.add("Service");
		} else {
			featureSelected.remove(sql);
			featureSelected.remove("SQL");
			featureSelected.remove("Database");
			featureSelected.remove("Service");
		}
	}
	
	private void selectNoSql(SelectionEvent event, String Nosql) {
		Button button = (Button) event.getSource();
		if (button.getSelection()) {
			featureSelected.add(Nosql);
			featureSelected.add("NoSQL");
			featureSelected.add("Database");
			featureSelected.add("Service");
		} else {
			featureSelected.remove(Nosql);
			featureSelected.remove("NoSQL");
			featureSelected.remove("Database");
			featureSelected.remove("Service");
		}
	}
	
	private void selectCache(SelectionEvent event, String cache) {
		Button button = (Button) event.getSource();
		if (button.getSelection()) {
			featureSelected.add(cache);
			featureSelected.add("Caching");
			featureSelected.add("Service");
		} else {
			featureSelected.remove(cache);
			featureSelected.remove("Caching");
			featureSelected.remove("Service");
		}
	}
	
	private void deployApp() {
		String[] feature = featureSelected.toArray(new String[featureSelected.size()]);
		
		// Currently we have two feature models
		ExecutorService executor = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(2);
		
		final LinkedList<String> avaliablePlatform = new LinkedList<String>(); 
		HerokuConsist herokuFM = new HerokuConsist(latch, feature, new ValidConfigCallback() {
			
			@Override
			public void onValid() {
				avaliablePlatform.add("heroku");
			}
			
			@Override
			public void onInvalid() {
			}
		});
		
		GoogleAppEngineConsist gaeFM = new GoogleAppEngineConsist(latch, feature, new ValidConfigCallback() {
			
			@Override
			public void onValid() {
				avaliablePlatform.add("google app engine");
			}
			
			@Override
			public void onInvalid() {
			}
		});
		
		executor.execute(herokuFM);
		executor.execute(gaeFM);
		executor.shutdown();
		
		try {
			latch.await();
		} catch (InterruptedException e) {
		}
		
		System.out.println("valid configuration checking job is DONE!!!!");
		if (avaliablePlatform.size() == 0) {
			MessageDialog.openError(sfeaShell, "Error", "No platform is avaliable for this configuration");
			return;
		}
		
		String platform = null;
		while ((platform = selectPlatform(avaliablePlatform)) == null) {
			MessageDialog.openError(sfeaShell, "Error", "Please select a platform");
		}
		
		showNFRUI(platform, feature);
	}
	
	private void showNFRUI(String platfrom, String[] feature) {
		if (platfrom.equals("heroku")) {
			new HerokuNFR(executionText.getText(), applicationText.getText(), sfeaDisplay, feature);
		}
	}
	
	private String selectPlatform(LinkedList<String> platformList) {
		PlatformSelectionDialog dialog = new PlatformSelectionDialog(sfeaShell, platformList);
		dialog.create();
		if (dialog.open() == Window.OK) {
			return dialog.getSelectedPlatfrom();
		}
		
		return null;
	}
	
	public void showUI() {
		sfeaShell.pack();
		sfeaShell.open();
		while (!sfeaShell.isDisposed()) {
			if (!sfeaDisplay.readAndDispatch()) {
				sfeaDisplay.sleep();
			}
		}
		
		sfeaDisplay.dispose();
	}
	
	public static void main(String[] args) {
		SFEAApplication application = new SFEAApplication();
		application.showUI();
	}
}
