import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteExample {

    private static final String URL = "jdbc:sqlite:meineDatenbank.db";

    public static void main(String[] args) {
        createNewDatabase();
        createNewTable();
        insertDocument("Dokument 1", "Inhalt des Dokuments 1","Bärenessen Marmelade");
        insertDocument("Dokument 2", "Inhalt des Dokuments 2","Bärenessen Marmelade");
        selectAllDocuments();
    }


    public static void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                System.out.println("Verbindung zur Datenbank hergestellt.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS dokumente (\n"
                + " id integer PRIMARY KEY AUTOINCREMENT,\n"
                + " titel text NOT NULL,\n"
                + " inhalt text NOT NULL\n"
                + " Thema  text NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabelle erstellt.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void insertDocument(String titel, String inhalt, String Thema) {
        String sql = "INSERT INTO dokumente(titel, inhalt,Thema) VALUES(?, ?,? )";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, titel);
            pstmt.setString(2, inhalt);
            pstmt.setString(3, Thema);
            pstmt.executeUpdate();
            System.out.println("Dokument hinzugefügt: " + titel);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void selectAllDocuments() {
        String sql = "SELECT * FROM dokumente";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Titel: " + rs.getString("titel"));
                System.out.println("Inhalt: " + rs.getString("inhalt"));
                System.out.println("Thema" + rs.getString("Thema"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}