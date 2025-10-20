import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

public class MassiveMotion extends JPanel implements ActionListener {

    protected Timer tm;
    private final Config cfg;
    // TODO: Consider removing the next two lines (coordinates for two balls)
    private final int redX;
    private final int redY;

    private static final int NUM_BLUE = 10;
    private final int[] bx = new int[NUM_BLUE];
    private final int[] by = new int[NUM_BLUE];
    private final int[] vx = new int[NUM_BLUE];
    private final int[] vy = new int[NUM_BLUE];

    private final Random rnd = new Random();
    

    public MassiveMotion(String propfile) {
        // TODO: insert your code to read from configuration file here.
        cfg = new Config(propfile);
        tm = new Timer(cfg.timerDelay, this); // TODO: Replace the first argument with delay with value from config file.

        // TODO: Consider removing the next two lines (coordinates) for random starting locations.
        redX = cfg.windowSizeX / 2 - 10;
        redY = cfg.windowSizeY / 2 - 10;

        for (int i = 0; i < NUM_BLUE; i++) {
            bx[i] = rnd.nextInt(cfg.windowSizeX - 20);
            by[i] = rnd.nextInt(cfg.windowSizeY - 20);

            vx[i] = rnd.nextInt(11) - 5;
            vy[i] = rnd.nextInt(11) - 5;
            if (vx[i] == 0) vx[i] = 2;
            if (vy[i] == 0) vy[i] = -3;
        }

        setPreferredSize(new Dimension(cfg.windowSizeX, cfg.windowSizeY));
        setBackground(Color.WHITE);
    }

    public MassiveMotion() {
        this("MassiveMotion.txt");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.

        // TODO: Paint each ball. Here's how to paint two balls, one after the other:
        g.setColor(Color.BLUE);
        for (int i = 0; i < NUM_BLUE; i++) {
            g.fillOval(bx[i], by[i], 10, 10);

        }
        g.setColor(Color.RED);
        g.fillOval(redX, redY, 20, 20);

        // Recommend you leave the next line as is
        tm.start();
    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // TODO: Change the location of each ball. Here's an example of them moving across the screen:
        //       ... but to be clear, you should change this.
        for (int i = 0; i < NUM_BLUE; i++) {
            bx[i] += vx[i];
            by[i] += vy[i];
        // These two "if" statements keep the balls on the screen in case they go off one side.
            if (bx[i] <= 0 || bx[i] >= cfg.windowSizeX - 20) {
                vx[i] *= -1;
            }
                
            if (by[i] <= 0 || by[i] >= cfg.windowSizeY - 20) {
                vy[i] *= -1;
            }
        }
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
