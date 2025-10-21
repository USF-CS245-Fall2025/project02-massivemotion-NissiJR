import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;

/*
 * The MassiveMotion class creates a graphical simulation
 * of moving comet-like objects around a stationary red center (representing a star).
 * This simulation uses a configurable properties file to define behavior such as
 * timer delay, window size, and the type of list used to store comets. The simulation
 * uses Swing's Timer for animation updates and the JPanel paint
 * mechanism to render motion.
@author NissiJR
@version 8.0
*/
public class MassiveMotion extends JPanel implements ActionListener {

    protected Timer tm;
    private final Config cfg;
    // TODO: Consider removing the next two lines (coordinates for two balls)
    private final int redX;
    private final int redY;

    private static final int COMETS = 30;
    private final List<Comet> comets;

    private final Random rnd = new Random();
    
    /**
     * Constructs a new {@code Comet} instance with given position and velocity.
     *
     * @param x  initial X-coordinate
     * @param y  initial Y-coordinate
     * @param vx horizontal velocity
     * @param vy vertical velocity
    */
    private static final class Comet {
        int bx, by, vx, vy;
        public Comet(int x, int y, int vx, int vy) {
            this.bx = x;
            this.by = y;
            this.vx = vx;
            this.vy = vy;
        }
    }

    /**
     * Constructs the simulation using a configuration file path.
     * Initializes comet positions, velocities, and list type from configuration.
     *
     * @param propfile the name or path of the properties file used for configuration
     */
    public MassiveMotion(String propfile) {
        // TODO: insert your code to read from configuration file here.
        cfg = new Config(propfile);
        tm = new Timer(cfg.timerDelay, this); // TODO: Replace the first argument with delay with value from config file.

        switch (cfg.listType) {
            case "arraylist":
                comets = new ArrayList<>();
                break;
            case "singlylinkedlist":
                comets = new SinglyLinkedList<>();
                break;
            case "doublylinkedlist":
                comets = new DoublyLinkedList<>();
                break;
            case "dummyheadlinkedlist":
                comets = new DummyHeadLinkedList<>();
                break;
            default:
                comets = new ArrayList<>();
                break;
        }

        // TODO: Consider removing the next two lines (coordinates) for random starting locations.
        redX = cfg.windowSizeX / 2 - 10;
        redY = cfg.windowSizeY / 2 - 10;

        for (int i = 0; i < COMETS; i++) {
            int bx = rnd.nextInt(Math.max(1, cfg.windowSizeX - 20));
            int by = rnd.nextInt(Math.max(1, cfg.windowSizeY - 20));

            int vx = rnd.nextInt(11) - 5;
            int vy = rnd.nextInt(11) - 5;
            comets.add(new Comet(bx, by, vx, vy));
        }

        setPreferredSize(new Dimension(cfg.windowSizeY, cfg.windowSizeX));
        setBackground(Color.WHITE);
    }

    /**
     * Paints all comets and the stationary central red object on the panel.
     * The comets are drawn in black, and the red object represents the star at the center.
     *
     * @param g the Graphics context used for drawing
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Probably best you leave this as is.

        // TODO: Paint each ball. Here's how to paint two balls, one after the other:
        g.setColor(Color.BLACK);
        for (int i = 0; i < COMETS; i++) {
            Comet c = comets.get(i);
            g.fillOval(c.bx, c.by, 10, 10);

        }
        g.setColor(Color.RED);
        g.fillOval(redX, redY, 20, 20);

        // Recommend you leave the next line as is
        tm.start();
    }

    /**
     * Handles timer events to update comet positions and repaint the panel.
     * This method drives the comet motion across frames.
     *
     * @param actionEvent the action event triggered by the Swing Timer
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        // TODO: Change the location of each ball. Here's an example of them moving across the screen:
        //       ... but to be clear, you should change this.
        for (int i = 0; i < comets.size(); i++) {
            Comet c = comets.get(i);
            c.bx += c.vx;
            c.by += c.vy;
        }
        // Keep this at the end of the function (no matter what you do above):
        repaint();
    }

    /**
     * Main entry point of the program.
     * Initializes and displays the MassiveMotion simulation in a JFrame.
     *
     * @param args optional argument specifying a configuration file
     */
    public static void main(String[] args) {
        MassiveMotion mm = (args.length > 0) ? new MassiveMotion(args[0]) : new MassiveMotion("MassiveMotion.txt");

        JFrame jf = new JFrame();
        jf.setTitle("Massive Motion");
        jf.setSize(mm.cfg.windowSizeX, mm.cfg.windowSizeY); // TODO: Replace with the size from configuration!
        jf.add(mm);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}