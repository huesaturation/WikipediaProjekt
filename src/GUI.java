import javax.swing.*;
import java.awt.*;

public class GUI
{
    JFrame window = new JFrame("Prime Facts");
    CardLayout pageLayout = new CardLayout();
    JPanel mainPanel = new JPanel(pageLayout);

    public GUI()
    {
        //initialize
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setSize(1920 , 1080);
        window.setVisible(true);

        // Seiten erstellen
        JPanel searchPage = createSearchPage();
        JPanel articlePage = createArticlePage();

        mainPanel.add(searchPage, "Search");
        mainPanel.add(articlePage, "Article");

        window.add(mainPanel);
    }

    public JPanel createSearchPage()
    {
        JPanel panel = new JPanel();

        return panel;
    }

    public JPanel createArticlePage()
    {
        JPanel panel = new JPanel();

        return panel;
    }
}
