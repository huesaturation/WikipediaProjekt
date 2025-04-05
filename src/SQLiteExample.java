import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class SQLiteExample {

    private static final String URL = "jdbc:sqlite:articles.db"; // Datenbank-URL

    public static void main(String[] args) {
        createNewDatabase(); // Datenbank erstellen
        createNewTable(); // Tabelle erstellen

        // Beispielverzeichnis, in dem die Textdateien gespeichert sind
        String directoryPath = "D:\\aadownload (3)\\Wikipediaprojekt\\articles";
        processArticles(directoryPath); // Artikel verarbeiten und in die Datenbank einfügen

        selectAllDocuments(); // Alle Dokumente aus der Datenbank auswählen und anzeigen
    }

    public static void createNewDatabase() {
        try (Connection conn = DriverManager.getConnection(URL)) {
            if (conn != null) {
                System.out.println("Verbindung zur Datenbank hergestellt.");
            }
        } catch (SQLException e) {
            System.out.println("Fehler bei der Verbindung zur Datenbank: " + e.getMessage());
        }
    }

    public static void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS articles (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " article_name TEXT NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabelle 'articles' wurde erstellt oder existiert bereits.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Erstellen der Tabelle: " + e.getMessage());
        }
    }

    public static void processArticles(String directoryPath) {
        File dir = new File(directoryPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt")); // Nur .txt-Dateien

        if (files != null) {
            for (File file : files) {
                String title = file.getName(); // Dateiname als Titel
                String content = readFileContent(file); // Inhalt der Datei lesen

                // Dokument in die Datenbank einfügen
                insertDocument(title, content);
            }
        } else {
            System.out.println("Keine Textdateien im Verzeichnis gefunden.");
        }
    }

    public static String readFileContent(File file) {
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Fehler beim Lesen der Datei: " + file.getName());
        }
        return contentBuilder.toString();
    }

    public static void insertDocument(String titel, String inhalt) {
        String sql = "INSERT INTO articles(id, article_name) VALUES(?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int randomId = new Random().nextInt(1000000); // Zufällige ID generieren
            pstmt.setInt(1, randomId);
            pstmt.setString(2, titel);
            pstmt.executeUpdate();
            System.out.println("Dokument hinzugefügt: " + titel);
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen des Dokuments: " + e.getMessage());
        }
    }

    public static void selectAllDocuments() {
        String sql = "SELECT * FROM articles";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Titel: " + rs.getString("article_name"));
                System.out.println("---------------------------");
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Auswählen der Dokumente: " + e.getMessage());
        }
    }
}