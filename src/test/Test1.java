package test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Test1 {
	public static void main(String[] args) {
		InitUI();
	}

	static void InitUI() {
		Display display = new Display();
		Shell shell = new Shell(display);

		Button button1 = new Button(shell, SWT.PUSH);
		button1.setText("close");
		button1.setBounds(75, 40, 80, 30);

		Button button2 = new Button(shell, SWT.PUSH);
		button2.setText("cancel");
		button2.setSize(80, 30);
		button2.setLocation(75, 75); // use setLocation instead of setLocale

		shell.setText("SWT DEMO");
		shell.setSize(300, 200);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}