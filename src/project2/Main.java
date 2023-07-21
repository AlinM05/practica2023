package project2;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class Main {

	private DatabaseOp dbOp;

	public static void main(String[] args) {
		Main app = new Main();
		app.run();
	}

	private void run() {
		dbOp = new DatabaseOp();

		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Application");

		createMenu(shell);

		shell.setSize(200, 200);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	private void createMenu(Shell shell) {
		Menu bar = new Menu(shell, SWT.BAR);
		shell.setMenuBar(bar);

		// Administration menu
		MenuItem fileItem = new MenuItem(bar, SWT.CASCADE);
		fileItem.setText("Administration");

		Menu submenu = new Menu(shell, SWT.DROP_DOWN);
		fileItem.setMenu(submenu);

		createMenuItem(submenu, "Add Store", SWT.PUSH, this::addStore);
		createMenuItem(submenu, "Remove Store", SWT.PUSH, this::removeStore);
		createMenuItem(submenu, "Modify Store", SWT.PUSH, this::modifyStore);

		createMenuItem(submenu, "Add Article", SWT.PUSH, this::addArticle);
		createMenuItem(submenu, "Remove Article", SWT.PUSH, this::removeArticle);
		createMenuItem(submenu, "Modify Article", SWT.PUSH, this::modifyArticle);

		createMenuItem(submenu, "Add Price", SWT.PUSH, this::addPrice);
		createMenuItem(submenu, "Remove Price", SWT.PUSH, this::removePrice);
		createMenuItem(submenu, "Modify Price", SWT.PUSH, this::modifyPrice);

		shell.setMenuBar(bar);
	}

	private void createMenuItem(Menu parent, String text, int style, Listener listener) {
		MenuItem item = new MenuItem(parent, style);
		item.setText(text);
		item.addListener(SWT.Selection, listener);
	}

	private void addStore(Event event) {
		String storeName = "Sample Store";
		dbOp.addStore(storeName);
	}

	private void removeStore(Event event) {
		String storeName = "Sample Store";
		dbOp.removeStore(storeName);
	}

	private void modifyStore(Event event) {
		Long storeId = 1L;
		String newName = "New Store Name";
		dbOp.modifyStore(storeId, newName);
	}

	private void addArticle(Event event) {
		String articleName = "Sample Article";
		dbOp.addArticle(articleName);
	}

	private void removeArticle(Event event) {
		Long articleId = 1L;
		dbOp.removeArticle(articleId);
	}

	private void modifyArticle(Event event) {
		Long articleId = 1L;
		String newArticleName = "New Article Name";
		dbOp.modifyArticle(articleId, newArticleName);
	}

	private void addPrice(Event event) {
		String storeName = "Sample Store";
		String articleName = "Sample Article";
		int price = 100;
		dbOp.addPrice(storeName, articleName, price);
	}

	private void removePrice(Event event) {
		Long priceId = 1L;
		dbOp.removePrice(priceId);
	}

	private void modifyPrice(Event event) {
		Long priceId = 1L;
		int newPriceValue = 150;
		dbOp.modifyPrice(priceId, newPriceValue);
	}
}
