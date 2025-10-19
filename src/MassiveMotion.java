import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.util.Properties;

public class MassiveMotion extends JPanel implements ActionListener {

    protected Timer tm;

    // TODO: Consider removing the next two lines (coordinates for two balls)
    protected int x1, y1;
    protected int x2, y2;

    private final Config cfg;

    public MassiveMotion(String propfile) {
        // TODO: insert your code to read from configuration file here.
        cfg = new Config(propfile);
        tm = new Timer(cfg.timerDelay, this); // TODO: Replace the first argument with delay with value from config file.

        // TODO: Consider removing the next two lines (coordinates) for random starting locations.
        x1 = cfg.starPosX; y1 = cfg.starPosY;
        x2 = cfg.windowSizeX / 3; y2 = cfg.windowSizeY / 2;

        setPreferredSize(new Dimension(cfg.windowSizeX, cfg.windowSizeY));
        setBackground(Color.BLACK);
    }

    public MassiveMotion() {
        this("MassiveMotion.txt");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.

        // TODO: Paint each ball. Here's how to paint two balls, one after the other:
        g.setColor(Color.BLUE);
        g.fillOval(x1, y1, 20, 20);

        g.setColor(Color.RED);
        g.fillOval(x2, y2, 20, 20);

        // Recommend you leave the next line as is
        tm.start();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // TODO: Change the location of each ball. Here's an example of them moving across the screen:
        //       ... but to be clear, you should change this.
        x1 += 10;
        x2 -= 15;
        // These two "if" statements keep the balls on the screen in case they go off one side.
        if (x1 > cfg.windowSizeX)
            x1 = 0;
        if (x2 < 0)
            x2 = cfg.windowSizeX;

        // Keep this at the end of the function (no matter what you do above):
        repaint();
    }

    public static void main(String[] args) {
        System.out.println("Massive Motion starting...");
        MassiveMotion mm = (args.length > 0) ? new MassiveMotion(args[0]) : new MassiveMotion("MassiveMotion.txt");

        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(mm.cfg.windowSizeX, mm.cfg.windowSizeY); // TODO: Replace with the size from configuration!
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
