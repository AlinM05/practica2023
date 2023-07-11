package practica2023;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");

		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres",
				"bd019fda")) {
			if (conn != null) {
				System.out.println("Connected to PostgreSQL server!");
			}

			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from vw_note_studenti");

			while (resultSet.next()) {
				System.out.println(//
						resultSet.getInt(1) + " " + //
								resultSet.getString(2) + " " + //
								resultSet.getDouble(3) + " " + //
								resultSet.getInt(4) //
				);
			}
			resultSet.close();
		}
	}
}