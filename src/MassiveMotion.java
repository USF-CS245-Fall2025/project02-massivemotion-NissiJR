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

    private static final int COMETS = 10;
    private final List<Comet> comets;

    private final Random rnd = new Random();
    
    private static final class Comet {
        int bx, by, vx, vy;
        public Comet(int x, int y, int vx, int vy) {
            this.bx = x;
            this.by = y;
            this.vx = vx;
            this.vy = vy;
        }
    }
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
