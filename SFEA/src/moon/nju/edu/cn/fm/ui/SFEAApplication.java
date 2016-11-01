package moon.nju.edu.cn.fm.ui;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import moon.nju.edu.cn.fm.platform.GoogleAppEngineFM;
import moon.nju.edu.cn.fm.platform.HerokuFM;
import moon.nju.edu.cn.fm.platform.ValidConfigCallback;

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
	private Group languageGroup, frameworkGroup, SQLGroup, NoSQLGroup, cacheGroup;
	private Button javaLanguageButton, pythonLanguageButton, rubyLanguageButton, 
		scalaLanguageButton, jsLanguageButton, phpLanguageButton, goLanguageButton;
	private Button springFrameworkButton, railsFrameworkButton, playFrameworkButton, nodejsFrameworkButton;
	private Button postgresSQLButton, clearDBSQLButton, redisNoSQLButton, mongoDBNoSQLButton;
	private Button ironCacheButton, memCacheButton;
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
		// Language
		languageGroup = new Group(sfeaShell, SWT.NONE);
		languageGroup.setText("Language");
		languageGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		languageGroup.setLayout(new GridLayout(4, true));
		javaLanguageButton = new Button(languageGroup, SWT.CHECK);
		javaLanguageButton.setText("Java");
		pythonLanguageButton = new Button(languageGroup, SWT.CHECK);
		pythonLanguageButton.setText("Python");
		rubyLanguageButton = new Button(languageGroup, SWT.CHECK);
		rubyLanguageButton.setText("Ruby");
		scalaLanguageButton = new Button(languageGroup, SWT.CHECK);
		scalaLanguageButton.setText("Scala");
		jsLanguageButton = new Button(languageGroup, SWT.CHECK);
		jsLanguageButton.setText("Javascript");
		phpLanguageButton = new Button(languageGroup, SWT.CHECK);
		phpLanguageButton.setText("PHP");
		goLanguageButton = new Button(languageGroup, SWT.CHECK);
		goLanguageButton.setText("Go");
	}
	
	private void setupFramework() {
		// Framework
		frameworkGroup = new Group(sfeaShell, SWT.NONE);
		frameworkGroup.setText("Framework");
		frameworkGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		frameworkGroup.setLayout(new GridLayout(4, true));
		springFrameworkButton = new Button(frameworkGroup, SWT.CHECK);
		springFrameworkButton.setText("Spring");
		railsFrameworkButton = new Button(frameworkGroup, SWT.CHECK);
		railsFrameworkButton.setText("Rails");
		playFrameworkButton = new Button(frameworkGroup, SWT.CHECK);
		playFrameworkButton.setText("Play");
		nodejsFrameworkButton = new Button(frameworkGroup, SWT.CHECK);
		nodejsFrameworkButton.setText("Node.js");
	}
	
	private void setupDatabase() {
		// SQL
		SQLGroup = new Group(sfeaShell, SWT.NONE);
		SQLGroup.setText("SQL Database");
		SQLGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		SQLGroup.setLayout(new GridLayout(4, true));
		postgresSQLButton = new Button(SQLGroup, SWT.CHECK);
		postgresSQLButton.setText("Postgres");
		clearDBSQLButton = new Button(SQLGroup, SWT.CHECK);
		clearDBSQLButton.setText("ClearDB");
		
		// NoSQL
		NoSQLGroup = new Group(sfeaShell, SWT.NONE);
		NoSQLGroup.setText("NoSQL Database");
		NoSQLGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		NoSQLGroup.setLayout(new GridLayout(4, true));
		redisNoSQLButton = new Button(NoSQLGroup, SWT.CHECK);
		redisNoSQLButton.setText("Redis");
		mongoDBNoSQLButton = new Button(NoSQLGroup, SWT.CHECK);
		mongoDBNoSQLButton.setText("MongoDB");
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
				
				javaLanguageButton.setSelection(false);
				pythonLanguageButton.setSelection(false);
				rubyLanguageButton.setSelection(false);
				scalaLanguageButton.setSelection(false);
				jsLanguageButton.setSelection(false);
				phpLanguageButton.setSelection(false);
				goLanguageButton.setSelection(false);
				
				springFrameworkButton.setSelection(false);
				railsFrameworkButton.setSelection(false);
				playFrameworkButton.setSelection(false);
				nodejsFrameworkButton.setSelection(false);
				
				postgresSQLButton.setSelection(false);
				clearDBSQLButton.setSelection(false);
				redisNoSQLButton.setSelection(false);
				mongoDBNoSQLButton.setSelection(false);
				
				ironCacheButton.setSelection(false);
				memCacheButton.setSelection(false);
				
				applicationText.setText("");
				executionText.setText("");
			}
		});
		
		javaLanguageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage(e, "Java");
			}
		});
		
		pythonLanguageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage(e, "Python");
			}
		});
		
		rubyLanguageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage(e, "Ruby");
			}
		});
		
		scalaLanguageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage(e, "Scala");
			}
		});
		
		jsLanguageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage(e, "Javascript");
			}
		});
		
		phpLanguageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage(e, "PHP");
			}
		});
		
		goLanguageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage(e, "Go");
			}
		});
		
		springFrameworkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectFramework(e, "Spring");
			}
		});
		
		railsFrameworkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectFramework(e, "Rails");
			}
		});
		
		playFrameworkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectFramework(e, "Play");
			}
		});
		
		nodejsFrameworkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectFramework(e, "Node.js");
			}
		});
		
		postgresSQLButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectSql(e, "Postgres");
			}
		});
		
		clearDBSQLButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectSql(e, "ClearDB");
			}
		});
		
		redisNoSQLButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectNoSql(e, "Redis");
			}
		});
		
		mongoDBNoSQLButton.addSelectionListener(new SelectionAdapter() {
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
		
		final List<String> avaliablePlatform = new LinkedList<String>(); 
		HerokuFM herokuFM = new HerokuFM(latch, feature, new ValidConfigCallback() {
			
			@Override
			public void onValid() {
				avaliablePlatform.add("heroku");
			}
			
			@Override
			public void onInvalid() {
			}
		});
		
		GoogleAppEngineFM gaeFM = new GoogleAppEngineFM(latch, feature, new ValidConfigCallback() {
			
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
	
	private String selectPlatform(List<String> platformList) {
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
