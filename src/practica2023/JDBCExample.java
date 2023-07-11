package practica2023;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCExample {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");

		try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tema1", "postgres",
				"bd019fda")) {
			if (conn != null) {
				System.out.println("Connected to PostgreSQL server!");
			}

			insertArticol(conn, 1, "Laptop");
			insertArticol(conn, 2, "Telefon");

			insertMagazin(conn, 1, "eMag");
			insertMagazin(conn, 2, "Altex");

			insertPret(conn, 1, 1, 1000);
			insertPret(conn, 2, 2, 7000);

			PreparedStatement statement = conn
					.prepareStatement("SELECT a.nume AS nume_articol, s.nume AS nume_magazin, p.pret " + "FROM ("
							+ "  SELECT article_id, MIN(pret) AS min_pret " + "  FROM preturi "
							+ "  GROUP BY article_id " + ") min_preturi "
							+ "JOIN preturi p ON p.article_id = min_preturi.article_id AND p.pret = min_preturi.min_pret "
							+ "JOIN articol a ON a.id = p.article_id " + "JOIN magazin s ON s.id = p.magazin_id");
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				String numeArticol = resultSet.getString("nume_articol");
				String numeMagazin = resultSet.getString("nume_magazin");
				int pret = resultSet.getInt("pret");

				System.out.println(numeArticol + " - " + numeMagazin + " - " + pret);
			}

			resultSet.close();
		}
	}

	public static void insertArticol(Connection conn, int id, String name) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("INSERT INTO articol values (?, ?)");
		statement.setInt(1, id);
		statement.setString(2, name);
		statement.execute();
	}

	public static void insertMagazin(Connection conn, int id, String name) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("INSERT INTO magazin values (?, ?)");
		statement.setInt(1, id);
		statement.setString(2, name);
		statement.execute();

	}

	public static void insertPret(Connection conn, int articolId, int magazinId, int pret) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("INSERT INTO preturi values (?, ?, ?)");
		statement.setInt(1, articolId);
		statement.setInt(2, magazinId);
		statement.setInt(3, pret);
		statement.execute();
	}
}