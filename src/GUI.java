import javax.swing.*;
import javax.swing.JScrollBar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JScrollBar;


public class GUI
{
    private JFrame window = new JFrame("Prime Facts");

    //Do not use this to load page use the functions instead!
    private CardLayout pageLayout = new CardLayout();
    //last loaded search querry
    //needs to be not NULL because the page is preloaded with a searchquerry
    private String searchquerry = "Actin";
    private JPanel mainPanel = new JPanel();

    public GUI()
    {
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


        pageLayout.show(mainPanel , "home");  //Seite die beim Start geladen wird

        window.add(mainPanel);

    }

    public void loadArticlePage()
    {
        mainPanel.removeAll();

        mainPanel.add(getArticlePage() , "article");
        mainPanel.add(getSearchPage() , "home");

        pageLayout.show(mainPanel , "article");
    }

    public void loadSearchPage()
    {
        searchquerry = "Actin";

        mainPanel.removeAll();

        mainPanel.add(getArticlePage() , "article");
        mainPanel.add(getSearchPage() , "home");

        pageLayout.show(mainPanel , "home");
    }



    public JPanel getSearchPage()
    {
        FlowLayout flow = new FlowLayout(FlowLayout.CENTER , 0 ,0);
        BorderLayout layout = new BorderLayout();
        JPanel searchPanel = new JPanel(layout);
        JPanel centerPanel = new JPanel(flow);
        centerPanel.setBackground(Color.DARK_GRAY);
        searchPanel.setBackground(Color.DARK_GRAY);


        JTextField searchField = new JTextField(20);
        searchField.setForeground(Color.WHITE);
        searchField.setCaretColor(Color.WHITE);
        searchField.setBackground(Color.BLACK);
        searchField.setBorder(BorderFactory.createLineBorder(Color.GRAY));


        JButton searchButton = new JButton("Search");
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setBorderPainted(false);
        searchButton.setOpaque(true);

        searchField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 searchquerry = searchField.getText();
                 loadArticlePage();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchquerry = searchField.getText();  // Get text from the JTextField
                loadArticlePage();
            }
        });

        searchPanel.add(centerPanel , BorderLayout.CENTER);
        centerPanel.add(searchField);
        centerPanel.add(searchButton);

        return searchPanel;
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

        //Backend check the output for a valid search querry
        JTextArea contentTextArea = new JTextArea(ArticleRetriever.retrieveArticle(searchquerry));
        contentTextArea.setEditable(false);
        contentTextArea.setBackground(Color.DARK_GRAY);
        contentTextArea.setForeground(Color.white);

        // Add JTextArea to JScrollPane to enable scrolling (just warpping the Contenttextarea)
        JScrollPane scrollPane = new JScrollPane(contentTextArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBackground(Color.DARK_GRAY);
        scrollPane.getVerticalScrollBar().setBackground(Color.DARK_GRAY);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setBorder(null);





        JLabel titel = new JLabel(searchquerry);
        titel.setBackground(Color.DARK_GRAY);
        titel.setForeground(Color.white);


        //rightPanel.add(new JTextArea("Kapitel 1"));
        //rightPanel.add(new JTextArea("Kapitel 2"));
        //rightPanel.add(new JTextArea("Kapitel 3"));
        //Kapitel müssen etwas verschoben werden, ansonsten passt die Scrollbar nicht hin. Sind erstmal ausgeblendet, damit Scrollbar funktioniert

        JButton homebutton = new JButton("Zurueck zum Hauptmenu");
        homebutton.setBackground(Color.BLACK);
        homebutton.setForeground(Color.WHITE);
        homebutton.setFocusPainted(false);
        homebutton.setBorderPainted(false);
        homebutton.setOpaque(true);
        leftPanel.add(homebutton);
        homebutton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loadSearchPage();
            }
        });

        Putting_Green puttingGreen = new Putting_Green();


        centerPanel.add(titel);
        centerPanel.add(scrollPane);

        mainPanel.add(centerPanel , BorderLayout.CENTER);
        mainPanel.add(leftPanel , BorderLayout.WEST);
        mainPanel.add(rightPanel , BorderLayout.EAST);
        //TODO takes up way too much space
        JPanel interactive = InteractiveRetriever.retrieveInteractive(searchquerry);
        interactive.setSize(1920 , 10);
        mainPanel.add(interactive , BorderLayout.NORTH);
        bLayout.setHgap(50);
        bLayout.setVgap(100);

        return mainPanel;
    }

}
