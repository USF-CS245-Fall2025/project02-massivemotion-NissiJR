/**
 * Config class to read and store configuration properties for the MassiveMotion simulation.
 * It reads properties from a specified file or uses default values if the file is not found
 * or properties are missing.
 *
 * @author NissiJR
 * @version 8.0
 */
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

    /**
     * Constructs a Config object by reading properties from the specified file.
     * If the file is not found or properties are missing, default values are used.
     *
     * @param proPath the path to the properties file
     */
    public Config(String proPath) {
        Properties p = new Properties();
        if (proPath != null) {
            try (FileInputStream f = new FileInputStream(proPath)) {
                p.load(f);
            } catch (IOException e) {
                System.err.println("[Config] Could not read properties: " + e.getMessage());
            }
        }

        //Display and timing
        timerDelay = getInt(p, "timer_delay", 75);
        listType = p.getProperty("list", "arraylist").trim();
        windowSizeX = getInt(p, "window_size_x", 1024);
        windowSizeY = getInt(p, "window_size_y", 768);

        //Star properties
        starPosX = getInt(p, "star_position_x", windowSizeX / 2);
        starPosY = getInt(p, "star_position_y", windowSizeY / 2);
        starSize = getInt(p, "star_size", 30);
        starMass = getDouble(p, "star_mass", 2E29);
        starVelx = getInt(p, "star_velocity_x", 0);
        starVely = getInt(p, "star_velocity_y", 0);

        //Comet properties
        genX = getDouble(p, "gen_x", 0.06);
        genY = getDouble(p, "gen_y", 0.06);
        bodySize = getInt(p, "body_size", 10);
        bodyMass = getDouble(p, "body_mass", 1E21);
        bodyVel = getInt(p, "body_velocity", 3);      
    }

    /**
     * Retrieves an integer property value from the Properties object.
     * If the property is missing or invalid, returns the provided default value.
     *
     * @param p   the Properties object containing configuration keys
     * @param k   the key of the property to retrieve
     * @param def the default value if parsing fails or the property is missing
     * @return the parsed integer value, or the default if invalid
     */
    private static int getInt(Properties p, String k, int def) {
        try { 
            return Integer.parseInt(p.getProperty(k, String.valueOf(def)).trim()); 
        } catch (Exception ignored) { 
            return def; 
        }
    }

    /**
     * Retrieves a double property value from the Properties object.
     * If the property is missing or invalid, returns the provided default value.
     *
     * @param p   the Properties object containing configuration keys
     * @param k   the key of the property to retrieve
     * @param def the default value if parsing fails or the property is missing
     * @return the parsed double value, or the default if invalid
     */
    private static double getDouble(Properties p, String k, double def) {
        try { 
            return Double.parseDouble(p.getProperty(k, String.valueOf(def)).trim()); 
        } catch (Exception ignored) { 
            return def; 
        }
    }
}