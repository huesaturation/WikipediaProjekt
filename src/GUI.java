import javax.swing.*;
import java.awt.*;

public class GUI
{

    public GUI()
    {
        JFrame window = new JFrame("Prime Facts");
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel();

        //initialize
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(1920 , 1080);
        window.setVisible(true);

        // Seiten erstellen
        JPanel searchPage = getSearchPage();
        JPanel articlePage = getArticlePage();

        mainPanel.setLayout(cardLayout);
        mainPanel.add(articlePage , "article");
        mainPanel.add(searchPage , "home");

        cardLayout.show(mainPanel , "home");

        window.add(mainPanel);
    }

    public JPanel getSearchPage()
    {
        JPanel mainPanel = new JPanel();

        JTextField searchField = new JTextField();

        mainPanel.add(searchField);

        return mainPanel;
    }

    public JPanel getArticlePage()
    {
        BorderLayout bLayout = new BorderLayout();
        JPanel mainPanel = new JPanel(bLayout);
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel , BoxLayout.Y_AXIS));
        JPanel leftPanel = new JPanel(new FlowLayout());

        //TODO Load Content from db
        JTextArea contentTextArea = new JTextArea("Lorem Ipsum dolor sit amet...");
        contentTextArea.setEditable(false);

        rightPanel.add(new JTextArea("Kapitel 1"));
        rightPanel.add(new JTextArea("Kapitel 2"));
        rightPanel.add(new JTextArea("Kapitel 3"));

        leftPanel.add(new JButton("Zurueck zum Hauptmenu"));

        mainPanel.add(contentTextArea , BorderLayout.CENTER);
        mainPanel.add(leftPanel , BorderLayout.WEST);
        mainPanel.add(rightPanel , BorderLayout.EAST);

        bLayout.setHgap(50);
        bLayout.setVgap(100);

        return mainPanel;
    }
}
