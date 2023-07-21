package magazin.hibernate;

public class Main {
	private KeyboardUtils kb = new KeyboardUtils();
	private DatabaseOperations db = new DatabaseOperations();

	public static void main(String[] args) {
		Main app = new Main();
		app.run();
	}

	private void run() {
		while (true) {
			showMenu();
			String option = kb.getString("Option: ");
			switch (option) {
			case "1":
				addArticle();
				break;
			case "2":
				addStore();
				break;
			case "3":
				addPrice();
				break;
			}
		}
	}

	private void addPrice() {
		String storeName = kb.getString("Store name: ");
		String articleName = kb.getString("Article name: ");
		int price = kb.getInt("Price: ");
		db.addPrice(storeName, articleName, price);
	}

	private void addStore() {
		String name = kb.getString("Store Name: ");
		db.addStore(name);
	}

	private void addArticle() {
		String name = kb.getString("Article Name: ");
		db.addArticle(name);
	}

	private void showMenu() {
		System.out.println("1. Add article");
		System.out.println("2. Add store");
		System.out.println("3. Add price");
	}
}
