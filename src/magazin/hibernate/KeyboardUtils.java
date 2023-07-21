package magazin.hibernate;

import java.util.Scanner;

public class KeyboardUtils {
	private Scanner scan = new Scanner(System.in);

	public int getInt(String message) {
		System.out.print(message);
		return Integer.parseInt(scan.nextLine());
	}

	public String getString(String message) {
		System.out.print(message);
		return scan.nextLine();
	}
}