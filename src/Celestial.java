import java.awt.*;

public class CelestialBody {
    public double x, y, vx, vy;
    public int radius;
    public Color color;

    public CelestialBody (double x, double y, double vx, double vy, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
        this.radius = radius;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        int d = radius * 2;
        g.fillOval((int) Math.round(x - radius), (int) Math.round(y - radius), d, d);
    }

    public boolean isOffscreen(int width, int height) {
        return (x < -radius) || (x > width + radius) || (y < -radius) || (y > height + radius);
    }
}