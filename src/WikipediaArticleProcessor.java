import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class WikipediaArticleProcessor {

    // Methode zur Verarbeitung der Artikel
    public static void processArticles(String directoryPath, String dbUrl) {
        File dir = new File(directoryPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));

        if (files != null) {
            try (Connection conn = DriverManager.getConnection(dbUrl)) {
                String sql = "INSERT INTO articles (id, article_name) VALUES (?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    Random random = new Random();
                    for (File file : files) {
                        int randomId = random.nextInt(1000000); // Generiere eine zufällige ID
                        String articleName = file.getName(); // Der Name der Datei wird als Artikelname verwendet

                        pstmt.setInt(1, randomId);
                        pstmt.setString(2, articleName);
                        pstmt.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                System.err.println("Datenbankfehler: " + e.getMessage());
            }
        }
    }

    // Methode zum Abrufen und Öffnen der Textdatei
    public static void openArticle(String dbUrl, String articleName) {
        String sql = "SELECT article_name FROM articles WHERE article_name = ?";
        try (Connection conn = DriverManager.getConnection(dbUrl);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, articleName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String filePath = "C:/Users/Lucas Siedenhans/iCloudDrive/articles/" + articleName; // Pfad zur Datei
                try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    System.err.println("Fehler beim Öffnen der Datei: " + articleName);
                }
            } else {
                System.out.println("Artikel nicht gefunden: " + articleName);
            }
        } catch (SQLException e) {
            System.err.println("Datenbankfehler: " + e.getMessage());
        }
    }

    // Hauptmethode
    public static void main(String[] args) {
        String directoryPath = "C:/Users/Lucas Siedenhans/iCloudDrive/articles"; // Pfad zu deinem Verzeichnis angeben
        String dbUrl = "jdbc:sqlite:articles.db"; // SQLite-Datenbank-URL

        // Artikel in die Datenbank einfügen
        processArticles(directoryPath, dbUrl);
        System.out.println("Artikel wurden erfolgreich in die Datenbank eingefügt.");

        // Beispiel: Artikel öffnen
        String articleNameToOpen = "example.txt"; // Ersetze dies durch den Namen der Datei, die du öffnen möchtest
        openArticle(dbUrl, articleNameToOpen);
    }
}