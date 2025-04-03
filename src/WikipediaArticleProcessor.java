import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WikipediaArticleProcessor {

    // Klasse zur Repräsentation eines Wikipedia-Artikels
    public static class Article {
        private String id;
        private String title;
        private String topic;

        public Article(String id, String title, String topic) {
            this.id = id;
            this.title = title;
            this.topic = topic;
        }

        @Override
        public String toString() {
            return "ID: " + id + ", Title: " + title + ", Topic: " + topic;
        }
    }

    // Methode zur Verarbeitung der Artikel
    public static List<Article> processArticles(String directoryPath) {
        List<Article> articles = new ArrayList<>();
        File dir = new File(directoryPath);
        File[] files = dir.listFiles((d, name) -> name.endsWith(".txt"));

        if (files != null) {
            for (File file : files) {
                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String title = br.readLine(); // Annahme: Die erste Zeile ist der Titel
                    String topic = br.readLine(); // Annahme: Die zweite Zeile ist das Thema
                    String id = generateId(title); // ID basierend auf dem Titel generieren

                    articles.add(new Article(id, title, topic));
                } catch (IOException e) {
                    System.err.println("Fehler beim Lesen der Datei: " + file.getName());
                }
            }
        }
        return articles;
    }

    // Methode zur Generierung einer ID
    private static String generateId(String title) {
        return String.valueOf(title.hashCode()); // Einfache ID-Generierung mit Hash-Code
    }

    // Hauptmethode
    public static void main(String[] args) {
        String directoryPath = "C:/Users/Lucas Siedenhans/iCloudDrive/articles"; // Pfad zu deinem Verzeichnis angeben
        List<Article> articles = processArticles(directoryPath);

        for (Article article : articles) {
            System.out.println(article);
        }
    }
}