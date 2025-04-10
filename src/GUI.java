import javax.swing.*;
import java.awt.*;

public class GUI
{
    public JFrame window = new JFrame("Prime Facts");
    public CardLayout pageLayout = new CardLayout();

    public GUI()
    {
        JPanel mainPanel = new JPanel();

        //initialize
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(1920 , 1080);
        window.setVisible(true);

        // Seiten erstellen
        JPanel searchPage = getSearchPage();
        JPanel articlePage = getArticlePage();

        mainPanel.setLayout(pageLayout);
        mainPanel.add(articlePage , "article");
        mainPanel.add(searchPage , "home");

        pageLayout.show(mainPanel , "home");

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
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel , BoxLayout.Y_AXIS));

        //TODO Load Content from db
        JTextArea contentTextArea = new JTextArea("Lorem Ipsum dolor sit amet...");
        contentTextArea.setEditable(false);

        //TODO Load Content from db
        JLabel titel = new JLabel("Test");

        rightPanel.add(new JTextArea("Kapitel 1"));
        rightPanel.add(new JTextArea("Kapitel 2"));
        rightPanel.add(new JTextArea("Kapitel 3"));

        leftPanel.add(new JButton("Zurueck zum Hauptmenu"));

        centerPanel.add(titel);
        centerPanel.add(contentTextArea);

        mainPanel.add(centerPanel , BorderLayout.CENTER);
        mainPanel.add(leftPanel , BorderLayout.WEST);
        mainPanel.add(rightPanel , BorderLayout.EAST);

        bLayout.setHgap(50);
        bLayout.setVgap(100);

        return mainPanel;
    }
}
