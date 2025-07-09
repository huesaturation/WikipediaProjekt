import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteArticleSaver {

    private static final String URL = "jdbc:sqlite:Artikel.db"; //ort an dem die datenbank ist sucht immer neu nach der datenbank egal wo sie ist wenn sie im projekt ist

    public static void main(String[] args) {
        createNewDatabase(); // Datenbank erstellen
        createNewTable(); // Tabelle erstellen

        // Beispielverzeichnis, in dem die Textdateien gespeichert sind
        String directoryPath = "D:\\aadownload (3)\\Wikipediaprojekt\\articles";//muss durch einen ordner mit txt datein ersetzt werden
        processArticles(directoryPath); // Artikel verarbeiten und in die Datenbank einfügen
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
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " article_name TEXT NOT NULL,\n"
                + " content TEXT NOT NULL\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Tabelle 'articles' wurde erstellt oder existiert bereits.");
        } catch (SQLException e) {
            System.out.println("Fehler beim Erstellen der Tabelle: " + e.getMessage());
        }
    }
    public static void showProgressBar(int current, int total) {
        int barLength = 40;
        int filledLength = (int) ((double) current / total * barLength);
        String bar = repeat("=", filledLength) + repeat(" ", barLength - filledLength);
        int percent = (int) ((double) current / total * 100);

        System.out.print("\r[" + bar + "] " + percent + "% (" + current + "/" + total + ")");
        System.out.flush();
    }
    public static String repeat(String str, int times) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }


    public static void processArticles(String directoryPath) {
        File dir = new File(directoryPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt")); // Nur .txt-Dateien

        if (files != null && files.length > 0) {
            int totalFiles = files.length;

            for (int i = 0; i < totalFiles; i++) {
                File file = files[i];
                String title = file.getName().replace(".txt", ""); // Dateiname ohne .txt
                String content = cleanWikipediaText(readFileContent(file)); // Wikipedia-Text bereinigen

                insertDocument(title, content); // Dokument in die Datenbank einfügen

                showProgressBar(i + 1, totalFiles); // Fortschritt anzeigen
            }

            System.out.println("\nAlle Artikel wurden verarbeitet.");
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

    public static void insertDocument(String title, String content) {
        String sql = "INSERT INTO articles (article_name, content) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, content); // Inhalt hinzufügen
            pstmt.executeUpdate();
            System.out.println("Dokument hinzugefügt: " + title);
        } catch (SQLException e) {
            System.out.println("Fehler beim Einfügen des Dokuments: " + e.getMessage());
        }
    }
    public static String cleanWikipediaText(String rawText) {
        String cleaned = rawText;

        // Entferne HTML-ähnliche Tags wie <ref>, <nowiki>, <br />, etc.
        cleaned = cleaned.replaceAll("<[^>]+>", "");

        // Entferne Kategorien und Dateiverweise: [[Kategorie:...]] oder [[Datei:...]]
        cleaned = cleaned.replaceAll("\\[\\[(Kategorie|Datei|File):[^\\]]+\\]\\]", "");

        // Entferne Formatierungen wie '''fett''' oder ''kursiv''
        cleaned = cleaned.replaceAll("'''", "");
        cleaned = cleaned.replaceAll("''", "");

        // Ersetze interne Links: [[Seite|Anzeigename]] → Anzeigename, [[Seite]] → Seite
        cleaned = cleaned.replaceAll("\\[\\[([^\\]|]+)\\|([^\\]]+)\\]\\]", "$2"); // mit |
        cleaned = cleaned.replaceAll("\\[\\[([^\\]]+)\\]\\]", "$1");             // ohne |

        // Entferne externe Links wie [http://example.com Text]
        cleaned = cleaned.replaceAll("\\[http[^\\s]+\\s([^\\]]+)\\]", "$1");
        cleaned = cleaned.replaceAll("\\[http[^\\]]+\\]", "");

        // Entferne Templates {{...}}
        cleaned = cleaned.replaceAll("\\{\\{[^\\}]*\\}\\}", "");

        // Entferne überflüssige Leerzeichen und Zeilen
        cleaned = cleaned.replaceAll("[\\t ]+", " ");
        cleaned = cleaned.replaceAll("(?m)^[ \\t]*\\r?\\n", ""); // leere Zeilen

        // Trim
        cleaned = cleaned.trim();

        return cleaned;
    }

}