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
        window.getContentPane().setBackground(Color.DARK_GRAY);

        // Seiten erstellen
        JPanel searchPage = getSearchPage();
        JPanel articlePage = getArticlePage();

        mainPanel.setLayout(pageLayout);
        mainPanel.add(articlePage , "article");
        mainPanel.add(searchPage , "home");

        pageLayout.show(mainPanel , "article");

        window.add(mainPanel);
    }

    public JPanel getSearchPage()
    {
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.DARK_GRAY);

        JTextField searchField = new JTextField();

        mainPanel.add(searchField);

        return mainPanel;
    }

    public JPanel getArticlePage()
    {
        BorderLayout bLayout = new BorderLayout();
        JPanel mainPanel = new JPanel(bLayout);
        mainPanel.setBackground(Color.DARK_GRAY);


        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.DARK_GRAY);
        rightPanel.setForeground(Color.white);
        rightPanel.setLayout(new BoxLayout(rightPanel , BoxLayout.Y_AXIS));

        JPanel leftPanel = new JPanel(new FlowLayout());
        leftPanel.setBackground(Color.DARK_GRAY);
        leftPanel.setForeground(Color.white);

        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.DARK_GRAY);
        centerPanel.setForeground(Color.white);
        centerPanel.setLayout(new BoxLayout(centerPanel , BoxLayout.Y_AXIS));

        //TODO Load Content from db
        JTextArea contentTextArea = new JTextArea("Lorem Ipsum dolor sit amet...");
        contentTextArea.setEditable(false);
        contentTextArea.setBackground(Color.DARK_GRAY);
        contentTextArea.setForeground(Color.white);


        //TODO Load Content from db
        JLabel titel = new JLabel("Test");
        titel.setBackground(Color.DARK_GRAY);
        titel.setForeground(Color.white);


        rightPanel.add(new JTextArea("Kapitel 1"));
        rightPanel.add(new JTextArea("Kapitel 2"));
        rightPanel.add(new JTextArea("Kapitel 3"));

        JButton homebutton = new JButton("Zurueck zum Hauptmenu");
        homebutton.setBackground(Color.black);
        homebutton.setForeground(Color.white);
        leftPanel.add(homebutton);

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
