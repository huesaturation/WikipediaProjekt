import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Wikirace extends JFrame //Jpanel needed
{
    private String currentArticle;
    private String targetArticle;
    private JTextArea articleContent;
    private JLabel targetLabel;
    private JLabel timerLabel;
    private Timer timer;
    private int timeElapsed;

    public Wikirace() {
        setTitle("Wikirace");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        targetLabel = new JLabel("Zielartikel: ");
        timerLabel = new JLabel("Zeit: 0 Sekunden");
        articleContent = new JTextArea();
        articleContent.setEditable(false);

        add(targetLabel, BorderLayout.NORTH);
        add(new JScrollPane(articleContent), BorderLayout.CENTER);
        add(timerLabel, BorderLayout.SOUTH);

        initializeGame();
        startTimer();
        loadArticle();
    }

    private void initializeGame() {
        // Hier sollten die Start- und Zielartikel vom Backend geladen werden
        String[] articles = {"Artikel A", "Artikel B", "Artikel C", "Artikel D", "Artikel E"};
        Random random = new Random();
        currentArticle = articles[random.nextInt(articles.length)];
        targetArticle = articles[random.nextInt(articles.length)];

        // Sicherstellen, dass der Zielartikel nicht der Startartikel ist
        while (currentArticle.equals(targetArticle)) {
            targetArticle = articles[random.nextInt(articles.length)];
        }

        targetLabel.setText("Zielartikel: " + targetArticle);
    }

    private void startTimer() {
        timeElapsed = 0;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timeElapsed++;
                timerLabel.setText("Zeit: " + timeElapsed + " Sekunden");
            }
        });
        timer.start();
    }

    private void loadArticle() {
        // Hier sollte der Artikelinhalt vom Backend geladen werden
        // Beispielinhalt
        articleContent.setText("Inhalt des Artikels: " + currentArticle);
    }

    private void loadNextArticle() {
        // Hier sollte die Logik implementiert werden, um den nächsten Artikel zu laden
        String[] nextArticles = {"Artikel A", "Artikel B", "Artikel C", "Artikel D", "Artikel E"};
        Random random = new Random();
        currentArticle = nextArticles[random.nextInt(nextArticles.length)];
        loadArticle();

        // Überprüfen, ob der Zielartikel erreicht wurde
        if (currentArticle.equals(targetArticle)) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Herzlichen Glückwunsch! Sie haben den Zielartikel erreicht in " + timeElapsed + " Sekunden.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Wikirace app = new Wikirace();
            app.setVisible(true);
        });
    }
}

