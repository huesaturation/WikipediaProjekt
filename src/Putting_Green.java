import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class Putting_Green extends JPanel implements ActionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 400;
    private static final double HOLE_RADIUS = 15;
    private static final double BALL_RADIUS = 12;
    private static final int MAX_SHOTS = 5;

    private Point holePos;
    private Point.Double ballPos;
    private int shots;
    private boolean gameOver;

    private Timer timer;
    private double moveDx;
    private double moveDy;
    private int animationSteps;
    private int currentStep;

    // UI Components
    //private JFrame frame;
    private JButton shootButton;
    private JSlider angleSlider;
    private JSlider strengthSlider;
    private JLabel statusLabel;

    public Putting_Green() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(60, 180, 75)); // Grüner Rasen

        holePos = new Point(WIDTH - 80, HEIGHT / 2);
        ballPos = new Point.Double(80, HEIGHT / 2.0);
        shots = 0;
        gameOver = false;

        timer = new Timer(15, this);

        initUI();
    }
    //TODO make it fit with the color Scheme of the App

    //fixing horrible ai code to actually work with our system

    public  JPanel createPuttingGreenPanel() {
        // Hauptpanel für alles
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Steuerpanel für Winkel und Stärke
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(40, 130, 55));
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8,8,8,8);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel angleLabel = new JLabel("Winkel (Grad):");
        angleLabel.setForeground(Color.white);
        c.gridx = 0; c.gridy = 0;
        controlPanel.add(angleLabel, c);

        angleSlider = new JSlider(0, 360, 0);
        angleSlider.setMajorTickSpacing(90);
        angleSlider.setMinorTickSpacing(15);
        angleSlider.setPaintTicks(true);
        angleSlider.setPaintLabels(true);
        c.gridx = 1; c.gridy = 0;
        controlPanel.add(angleSlider, c);

        JLabel strengthLabel = new JLabel("Stärke (1-100):");
        strengthLabel.setForeground(Color.white);
        c.gridx = 0; c.gridy = 1;
        controlPanel.add(strengthLabel, c);

        strengthSlider = new JSlider(1, 100, 50);
        strengthSlider.setMajorTickSpacing(25);
        strengthSlider.setMinorTickSpacing(5);
        strengthSlider.setPaintTicks(true);
        strengthSlider.setPaintLabels(true);
        c.gridx = 1; c.gridy = 1;
        controlPanel.add(strengthSlider, c);

        shootButton = new JButton("Schießen");
        shootButton.setBackground(new Color(255, 215, 0));
        shootButton.setFocusPainted(false);
        shootButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        shootButton.addActionListener(e -> shoot());
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        controlPanel.add(shootButton, c);

        statusLabel = new JLabel("Schläge: 0 / " + MAX_SHOTS + "  |  Winkel: 0°  |  Stärke: 50");
        statusLabel.setForeground(Color.white);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        angleSlider.addChangeListener(e -> updateStatus());
        strengthSlider.addChangeListener(e -> updateStatus());

        mainPanel.add(this, BorderLayout.CENTER);
        mainPanel.add(controlPanel, BorderLayout.SOUTH);
        mainPanel.add(statusLabel, BorderLayout.NORTH);

        return mainPanel;
    }

    private void initUI() {
        //frame = new JFrame("Putting Green Simulator");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setLayout(new BorderLayout());

        // Steuerpanel für Winkel und Stärke
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(40, 130, 55));
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(8,8,8,8);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel angleLabel = new JLabel("Winkel (Grad):");
        angleLabel.setForeground(Color.white);
        c.gridx = 0; c.gridy = 0;
        controlPanel.add(angleLabel, c);

        angleSlider = new JSlider(0, 360, 0);
        angleSlider.setMajorTickSpacing(90);
        angleSlider.setMinorTickSpacing(15);
        angleSlider.setPaintTicks(true);
        angleSlider.setPaintLabels(true);
        c.gridx = 1; c.gridy = 0;
        controlPanel.add(angleSlider, c);

        JLabel strengthLabel = new JLabel("Stärke (1-100):");
        strengthLabel.setForeground(Color.white);
        c.gridx = 0; c.gridy = 1;
        controlPanel.add(strengthLabel, c);

        strengthSlider = new JSlider(1, 100, 50);
        strengthSlider.setMajorTickSpacing(25);
        strengthSlider.setMinorTickSpacing(5);
        strengthSlider.setPaintTicks(true);
        strengthSlider.setPaintLabels(true);
        c.gridx = 1; c.gridy = 1;
        controlPanel.add(strengthSlider, c);

        shootButton = new JButton("Schießen");
        shootButton.setBackground(new Color(255, 215, 0));
        shootButton.setFocusPainted(false);
        shootButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        shootButton.addActionListener(e -> shoot());
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        controlPanel.add(shootButton, c);

        statusLabel = new JLabel("Schläge: 0 / " + MAX_SHOTS + "  |  Winkel: 0°  |  Stärke: 50");
        statusLabel.setForeground(Color.white);
        statusLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        angleSlider.addChangeListener(e -> updateStatus());
        strengthSlider.addChangeListener(e -> updateStatus());

        //frame.add(this, BorderLayout.CENTER);
        //frame.add(controlPanel, BorderLayout.SOUTH);
        //frame.add(statusLabel, BorderLayout.NORTH);

        //frame.pack();
        //frame.setLocationRelativeTo(null);
        //frame.setVisible(true);
    }

    private void updateStatus() {
        statusLabel.setText(String.format("Schläge: %d / %d  |  Winkel: %d°  |  Stärke: %d",
                shots, MAX_SHOTS, angleSlider.getValue(), strengthSlider.getValue()));
    }

    private void shoot() {
        if (gameOver) return;
        if (timer.isRunning()) return; // keine neuen Schüsse während Animation

        if (shots >= MAX_SHOTS) {
            statusLabel.setText("Keine Schläge mehr! Spiel beendet.");
            gameOver = true;
            shootButton.setEnabled(false);
            return;
        }

        shots++;
        updateStatus();

        // Berechne Bewegung
        double angleRad = Math.toRadians(angleSlider.getValue());
        double distance = strengthSlider.getValue() * 3.0 ; // max ca 60 px

        moveDx = distance * Math.cos(angleRad);
        moveDy = -distance * Math.sin(angleRad);

        animationSteps = 40;
        currentStep = 0;

        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentStep < animationSteps) {
            ballPos.x += moveDx / animationSteps;
            ballPos.y += moveDy / animationSteps;

            // Grenzen prüfen
            ballPos.x = Math.max(BALL_RADIUS, Math.min(WIDTH - BALL_RADIUS, ballPos.x));
            ballPos.y = Math.max(BALL_RADIUS, Math.min(HEIGHT - BALL_RADIUS, ballPos.y));

            currentStep++;
            repaint();
        } else {
            timer.stop();
            checkHole();
        }
    }

    private void checkHole() {
        double dx = ballPos.x - holePos.x;
        double dy = ballPos.y - holePos.y;
        double dist = Math.sqrt(dx*dx + dy*dy);
        if (dist <= HOLE_RADIUS) {
            statusLabel.setText("Glückwunsch! Die Kugel ist im Loch nach " + shots + " Schlägen.");
            gameOver = true;
            shootButton.setEnabled(false);
        } else if (shots >= MAX_SHOTS) {
            statusLabel.setText(String.format("Spiel vorbei! Kugel nicht im Loch (%.1f, %.1f).", ballPos.x, ballPos.y));
            gameOver = true;
            shootButton.setEnabled(false);
        } else {
            statusLabel.setText(String.format("Kein Treffer. Noch %d Schläge übrig.", MAX_SHOTS - shots));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Besseren Grafik Kontext
        Graphics2D g2d = (Graphics2D) g.create();

        // Anti-Aliasing für glatt zeichnen
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Putting Green Hintergrund (dunkler grün etwa)
        g2d.setColor(new Color(34,139,34));
        g2d.fillRect(0, 0, WIDTH, HEIGHT);

        // Loch zeichnen (schwarz mit hellem Rand)
        g2d.setColor(Color.BLACK);
        Ellipse2D.Double hole = new Ellipse2D.Double(holePos.x - HOLE_RADIUS, holePos.y - HOLE_RADIUS,
                2*HOLE_RADIUS, 2*HOLE_RADIUS);
        g2d.fill(hole);
        g2d.setColor(new Color(100, 100, 100));
        g2d.draw(hole);

        // Kugel zeichnen (weiß mit Schatten)
        g2d.setColor(new Color(220,220,220));
        Ellipse2D.Double ball = new Ellipse2D.Double(ballPos.x - BALL_RADIUS, ballPos.y - BALL_RADIUS,
                2*BALL_RADIUS, 2*BALL_RADIUS);
        g2d.fill(ball);

        // Kugelschatten unten rechts
        g2d.setColor(new Color(150,150,150,100));
        g2d.fillOval((int)(ballPos.x - BALL_RADIUS + 5), (int)(ballPos.y - BALL_RADIUS + 5),
                (int)(2*BALL_RADIUS), (int)(2*BALL_RADIUS));

        // Rahmen
        g2d.setColor(Color.DARK_GRAY);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawRect(0, 0, WIDTH-1, HEIGHT-1);

        g2d.dispose();
    }

    public static void main(String[] args) {
        // UI im Event Dispatch Thread starten
        SwingUtilities.invokeLater(() -> {
            new Putting_Green();
        });
    }
}

