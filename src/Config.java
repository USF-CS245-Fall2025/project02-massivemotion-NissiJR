import java.io.*;
import java.util.*;

public class Config {
    public final int timerDelay;
    public final String listType;
    public final int windowSizeX;
    public final int windowSizeY;
    
    public final int starPosX;
    public final int starPosY;
    public final int starSize;
    public final double starMass;
    public final int starVelx;
    public final int starVely;

    public final double genX;
    public final double genY;
    public final int bodySize;
    public final double bodyMass;
    public final int bodyVel;

    public Config(String proPath) {
        Properties p = new Properties();
        if (proPath != null) {
            try (FileInputStream f = new FileInputStream(proPath)) {
                p.load(f);
            } catch (IOException e) {
                System.err.println("[Config] Could not read properties: " + e.getMessage());
            }
        }
        timerDelay = getInt(p, "timer_delay", 75);
<<<<<<< HEAD
        listType = p.getProperty("list", "arraylist").trim();

=======
        String listProp = p.getProperty("list");  // try to read 'list' from file
        if (listProp == null || listProp.trim().isEmpty()) { // No list type specified: pick a random one
            String[] listOptions = {"arraylist", "single", "double", "dummyhead", "circular"};
            listType = listOptions[new Random().nextInt(listOptions.length)];
            System.out.println("[Config] No 'list' specified; randomly selected: " + listType);
        } else {
            listType = listProp.trim().toLowerCase();
        }
>>>>>>> 548f765d72bf670da0298225c041b450b4ecbec2
        windowSizeX = getInt(p, "window_size_x", 1024);
        windowSizeY = getInt(p, "window_size_y", 768);

        starPosX = getInt(p, "star_position_x", windowSizeX / 2);
        starPosY = getInt(p, "star_position_y", windowSizeY / 2);
        starSize = getInt(p, "star_size", 30);
        starMass = getDouble(p, "star_mass", 2E29);
        starVelx = getInt(p, "star_velocity_x", 0);
        starVely = getInt(p, "star_velocity_y", 0);

        genX = getDouble(p, "gen_x", 0.06);
        genY = getDouble(p, "gen_y", 0.06);
        bodySize = getInt(p, "body_size", 10);
        bodyMass = getDouble(p, "body_mass", 1E21);
        bodyVel = getInt(p, "body_velocity", 3);      
    }

    private static int getInt(Properties p, String k, int def) {
        try { return Integer.parseInt(p.getProperty(k, String.valueOf(def)).trim()); }
        catch (Exception ignored) { return def; }
    }
    private static double getDouble(Properties p, String k, double def) {
        try { return Double.parseDouble(p.getProperty(k, String.valueOf(def)).trim()); }
        catch (Exception ignored) { return def; }
    }

}