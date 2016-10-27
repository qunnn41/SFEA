package moon.nju.edu.cn.fm.ui;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import moon.nju.edu.cn.fm.platform.GoogleAppEngineFM;
import moon.nju.edu.cn.fm.platform.HerokuFM;
import moon.nju.edu.cn.fm.platform.ValidConfigCallback;

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
	private Display display;
	private Shell shell;
	private final Set<String> featureSelected = new HashSet<String>();
	
	private Button deployButton, resetButton;
	private Group languageGroup, frameworkGroup, SQLGroup, NoSQLGroup, cacheGroup;
	private Button javaLanguageButton, pythonLanguageButton, rubyLanguageButton, 
		scalaLanguageButton, jsLanguageButton, phpLanguageButton, goLanguageButton;
	private Button springFrameworkButton, railsFrameworkButton, playFrameworkButton, nodejsFrameworkButton;
	private Button postgresSQLButton, clearDBSQLButton, redisNoSQLButton, mongoDBNoSQLButton;
	private Button ironCacheButton, memCacheButton;
	
	public SFEAApplication() {
		display = new Display();
		shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		
		this.setupInputArea();
		this.setupLanugage();
		this.setupFramework();
		this.setupDatabase();
		this.setupCaching();
		
		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		composite.setLayout(new GridLayout(2, true));
		deployButton = new Button(composite, SWT.PUSH);
		deployButton.setText("Deploy");
		
		resetButton = new Button(composite, SWT.PUSH);
		resetButton.setText("Reset");
		
		this.setupListeners();
	}
	
	private void setupInputArea() {
		Group applicationGroup = new Group(shell, SWT.NONE);
		applicationGroup.setText("Application Name");
		applicationGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		applicationGroup.setLayout(new GridLayout(1, true));
		Text applicationText = new Text(applicationGroup, SWT.NONE);
		applicationText.setText("");
		applicationText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		
		Group executionGroup = new Group(shell, SWT.NONE);
		executionGroup.setText("Execution Command");
		executionGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		executionGroup.setLayout(new GridLayout(1, true));
		Text executionText = new Text(executionGroup, SWT.NONE);
		executionText.setText("");
		executionText.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
	}
	
	private void setupLanugage() {
		// Language
		languageGroup = new Group(shell, SWT.NONE);
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
		frameworkGroup = new Group(shell, SWT.NONE);
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
		SQLGroup = new Group(shell, SWT.NONE);
		SQLGroup.setText("SQL Database");
		SQLGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		SQLGroup.setLayout(new GridLayout(4, true));
		postgresSQLButton = new Button(SQLGroup, SWT.CHECK);
		postgresSQLButton.setText("Postgres");
		clearDBSQLButton = new Button(SQLGroup, SWT.CHECK);
		clearDBSQLButton.setText("ClearDB");
		
		// NoSQL
		NoSQLGroup = new Group(shell, SWT.NONE);
		NoSQLGroup.setText("NoSQL Database");
		NoSQLGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));
		NoSQLGroup.setLayout(new GridLayout(4, true));
		redisNoSQLButton = new Button(NoSQLGroup, SWT.CHECK);
		redisNoSQLButton.setText("Redis");
		mongoDBNoSQLButton = new Button(NoSQLGroup, SWT.CHECK);
		mongoDBNoSQLButton.setText("MongoDB");
	}
	
	private void setupCaching() {
		cacheGroup = new Group(shell, SWT.NONE);
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
			}
		});
		
		javaLanguageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage("Java");
			}
		});
		
		pythonLanguageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage("Python");
			}
		});
		
		rubyLanguageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage("Ruby");
			}
		});
		
		scalaLanguageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage("Scala");
			}
		});
		
		jsLanguageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage("Javascript");
			}
		});
		
		phpLanguageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage("PHP");
			}
		});
		
		goLanguageButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectLanguage("Go");
			}
		});
		
		springFrameworkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectFramework("Spring");
			}
		});
		
		railsFrameworkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectFramework("Rails");
			}
		});
		
		playFrameworkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectFramework("Play");
			}
		});
		
		nodejsFrameworkButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectFramework("Node.js");
			}
		});
		
		postgresSQLButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectSql("Postgres");
			}
		});
		
		clearDBSQLButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectSql("ClearDB");
			}
		});
		
		redisNoSQLButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectNoSql("Redis");
			}
		});
		
		mongoDBNoSQLButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectNoSql("MongoDB");
			}
		});
		
		ironCacheButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectCache("Ironcache");
			}
		});
		
		memCacheButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectCache("Memcachier");
				selectCache("Memcache");
			}
		});
	}
	
	private void selectLanguage(String language) {
		featureSelected.add(language);
		featureSelected.add("Language");
	}
	
	private void selectFramework(String framework) {
		featureSelected.add(framework);
		featureSelected.add("Framework");
	}
	
	private void selectSql(String sql) {
		featureSelected.add(sql);
		featureSelected.add("SQL");
		featureSelected.add("Database");
		featureSelected.add("Service");
	}
	
	private void selectNoSql(String Nosql) {
		featureSelected.add(Nosql);
		featureSelected.add("SQL");
		featureSelected.add("Database");
		featureSelected.add("Service");
	}
	
	private void selectCache(String cache) {
		featureSelected.add(cache);
		featureSelected.add("Caching");
		featureSelected.add("Service");
	}
	
	private void deployApp() {
		System.out.println("deploying...");
		String[] feature = featureSelected.toArray(new String[featureSelected.size()]);
		
		for (String string : feature) {
			System.out.println(string);
		}
		
		// Currently we have two feature models
		ExecutorService executor = Executors.newCachedThreadPool();
		CountDownLatch latch = new CountDownLatch(2);
		
		HerokuFM herokuFM = new HerokuFM(latch, feature, new ValidConfigCallback() {
			
			@Override
			public void onValid() {
				System.out.println("heroku valid");
			}
			
			@Override
			public void onInvalid() {
				System.out.println("heroku invalid");
			}
		});
		
		GoogleAppEngineFM gaeFM = new GoogleAppEngineFM(latch, feature, new ValidConfigCallback() {
			
			@Override
			public void onValid() {
				System.out.println("google app engine valid");
			}
			
			@Override
			public void onInvalid() {
				System.out.println("google app engine invalid");
			}
		});
		
		executor.execute(herokuFM);
		executor.execute(gaeFM);
		executor.shutdown();
		
		try {
			latch.await();
		} catch (InterruptedException e) {
		}
		
		//TODO add NFS selection and generate script
		System.out.println("finish");
	}
	
	public void showUI() {
		shell.pack();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		
		display.dispose();
	}
	
	public static void main(String[] args) {
		SFEAApplication application = new SFEAApplication();
		application.showUI();
	}
}
