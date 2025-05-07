import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ArticleRetriever {

    private static final String URL = "jdbc:sqlite:Artikel.db"; // Pfad zur Datenbank

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Gib den Titel des Artikels ein (ohne .txt): ");
        String articleTitle = scanner.nextLine(); // Benutzereingabe für den Artikeltitel

        retrieveArticle(articleTitle); // Artikel abrufen
        scanner.close();
    }

    public static String retrieveArticle(String title) {
        String sql = "SELECT id, article_name, content FROM articles WHERE article_name = ?"; // SQL-Abfrage
        String content = "";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, title); // Titel in die Abfrage einfügen
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String articleName = rs.getString("article_name");
                 content = rs.getString("content");

                // Ausgabe der Artikelinformationen
                System.out.printf("ID: %d, Artikelname: %s, Inhalt: %s%n", id, articleName, content);
            } else {
                System.out.println("Kein Artikel mit dem Titel '" + title + "' gefunden.");
            }
        } catch (SQLException e) {
            System.out.println("Fehler beim Abrufen des Artikels: " + e.getMessage());
        }
       return content; }
}
