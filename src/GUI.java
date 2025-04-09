import javax.swing.*;
import java.awt.*;

public class GUI
{
    JFrame window = new JFrame("Prime Facts");
    CardLayout pageLayout = new CardLayout();
    JPanel mainPanel = new JPanel();

    public GUI()
    {
        //initialize
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(1920 , 1080);
        window.setVisible(true);

        // Seiten erstellen
        JPanel searchPage = createSearchPage();
        JPanel articlePage = createArticlePage();

        mainPanel.setLayout(new FlowLayout());
        mainPanel.add(createArticlePage());

        //pageLayout.show(articlePage , "Article");

        window.add(mainPanel);
    }

    public JPanel createSearchPage()
    {
        JPanel panel = new JPanel();

        return panel;
    }

    public JPanel createArticlePage()
    {
        CardLayout cardLayout = new CardLayout();
        BorderLayout bLayout = new BorderLayout();
        JPanel mainPanel = new JPanel(cardLayout);
        JPanel contentPanel = new JPanel(bLayout);
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

        contentPanel.add(contentTextArea , BorderLayout.CENTER);
        contentPanel.add(leftPanel , BorderLayout.WEST);
        contentPanel.add(rightPanel , BorderLayout.EAST);

        bLayout.setHgap(50);
        bLayout.setVgap(100);

        mainPanel.add(contentPanel);

        return mainPanel;
    }
}
