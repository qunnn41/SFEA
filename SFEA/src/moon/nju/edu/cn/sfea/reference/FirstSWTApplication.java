package moon.nju.edu.cn.sfea.reference;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class FirstSWTApplication {
	public static void main(String[] args) {
		Display display = new Display();
        Shell shell = new Shell(display);

        // create a new GridLayout with two columns
        // of different size
        GridLayout layout = new GridLayout(2, false);

        // set the layout to the shell
        shell.setLayout(layout);

        // create a label and a button
        Label label = new Label(shell, SWT.NONE);
        label.setText("A label");
        Button button = new Button(shell, SWT.PUSH);
        button.setText("Press Me");

        // create a new label that will span two columns
        label = new Label(shell, SWT.BORDER);
        label.setText("This is a label");
        // create new layout data
        GridData data = new GridData(SWT.FILL, SWT.TOP, true, false, 2, 1);
        label.setLayoutData(data);

        // create a new label which is used as a separator
        label = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);

        // create new layout data
        data = new GridData(SWT.FILL, SWT.TOP, true, false);
        data.horizontalSpan = 2;
        label.setLayoutData(data);

        // create a right aligned button
        Button b = new Button(shell, SWT.PUSH);
        b.setText("New Button");

        data = new GridData(SWT.LEFT, SWT.TOP, false, false, 2, 1);
        b.setLayoutData(data);

         // create a spinner with min value 0 and max value 1000
        Spinner spinner = new Spinner(shell, SWT.READ_ONLY);
        spinner.setMinimum(0);
        spinner.setMaximum(1000);
        spinner.setSelection(500);
        spinner.setIncrement(1);
        spinner.setPageIncrement(100);
        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        gridData.widthHint = SWT.DEFAULT;
        gridData.heightHint = SWT.DEFAULT;
        gridData.horizontalSpan = 2;
        spinner.setLayoutData(gridData);

        Composite composite = new Composite(shell, SWT.BORDER);
        gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        gridData.horizontalSpan = 2;
        composite.setLayoutData(gridData);
        composite.setLayout(new GridLayout(1, false));

        Text txtTest = new Text(composite, SWT.NONE);
        txtTest.setText("Testing");
        gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        txtTest.setLayoutData(gridData);

        Text txtMoreTests = new Text(composite, SWT.NONE);
        txtMoreTests.setText("Another test");
        

        Group group = new Group(shell, SWT.NONE);
        group.setText("This is my group");
        gridData = new GridData(SWT.FILL, SWT.FILL, true, false);
        gridData.horizontalSpan = 2;
        group.setLayoutData(gridData);
        group.setLayout(new RowLayout(SWT.VERTICAL));
        Text txtAnotherTest = new Text(group, SWT.NONE);
        txtAnotherTest.setText("Another test");
        
        Button b1 = new Button(shell, SWT.PUSH);
        b1.setText("Button1");
        Button b2 = new Button(shell, SWT.PUSH);
        b2.setText("Button2");

        Control[] controls = new Control[] { b2, b1};
        shell.setTabList(controls);
        
        Table table = new Table(shell, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
        table.setLinesVisible(true);
        table.setHeaderVisible(true);
        GridData data2 = new GridData(SWT.FILL, SWT.FILL, true, true);
        data2.heightHint = 200;
        table.setLayoutData(data2);

        String[] titles = { "First Name", "Last Name", "Age" };
        for (int i = 0; i < titles.length; i++) {
            TableColumn column = new TableColumn(table, SWT.NONE);
            column.setText(titles[i]);
            table.getColumn(i).pack();
        }

        for (int i = 0 ; i<= 50 ; i++){
            TableItem item = new TableItem(table, SWT.NONE);
            item.setText (0, "Person " +i );
            item.setText (1, "LastName " +i );
            item.setText (2, String.valueOf(i));
        }

        for (int i=0; i<titles.length; i++) {
            table.getColumn (i).pack ();
        }
        
        final Tree tree = new Tree(shell, SWT.V_SCROLL);
        for (int i=0; i<5;i++) {
            TreeItem item = new TreeItem(tree, SWT.NONE);
            item.setText(String.valueOf(i));
            for (int j=0; j<3;j++) {
                TreeItem subItem = new TreeItem(item, SWT.NONE);
                subItem.setText(String.valueOf(i) + " " + String.valueOf(j));
            }
        }
        tree.pack();
        Menu menu = new Menu(tree);
        MenuItem menuItem = new MenuItem(menu, SWT.NONE);
        menuItem.setText("Print Element");
        menuItem.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent event) {
                System.out.println(tree.getSelection()[0].getText());
            }
        });
        tree.setMenu(menu);
        
        CTabFolder folder = new CTabFolder(shell, SWT.BOTTOM);
        GridData data3 = new GridData(SWT.FILL,
                SWT.FILL, true, true,
                2, 1);
        folder.setLayoutData(data3);
        CTabItem cTabItem1 = new CTabItem(folder, SWT.NONE);
        cTabItem1.setText("Tab1");
        CTabItem cTabItem2 = new CTabItem(folder, SWT.NONE);
        cTabItem2.setText("Tab2");
        CTabItem cTabItem3 = new CTabItem(folder, SWT.NONE);
        cTabItem3.setText("Tab3");

        Text text = new Text(folder, SWT.BORDER);
        text.setText("Hello");
        cTabItem1.setControl(text);

        shell.pack();
        shell.open();
        while (!shell.isDisposed()) {
                if (!display.readAndDispatch()) {
                        display.sleep();
                }
        }
        display.dispose();
	}
}
