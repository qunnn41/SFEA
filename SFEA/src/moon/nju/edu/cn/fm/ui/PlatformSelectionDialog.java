package moon.nju.edu.cn.fm.ui;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class PlatformSelectionDialog extends Dialog {
	private List<String> platformList;
	private String selectedPlatform = null;
	
	public PlatformSelectionDialog(Shell shell, List<String> platformList) {
		super(shell);
		this.platformList = platformList;
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new RowLayout());
		
		for (final String platform: platformList) {
			Button button = new Button(parent, SWT.RADIO);
			button.setText(platform);
			button.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					setPlatform(platform);
				}
			});
		}
		
		return container;
	}
	
	private void setPlatform(String platfrom) {
		this.selectedPlatform = platfrom;
	}
	
	public String getSelectedPlatfrom() {
		return this.selectedPlatform;
	}
	
	@Override
	protected void configureShell(Shell shell) {
		super.configureShell(shell);
		shell.setText("Platform Selection");
	}
}
