/// This class is fully written 
public class Point {
    public final int x;
    public final int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    /// Creates a point (0..99, 0..99)
    public static Point random() {
        return new Point((int) Math.random() * 100, (int) Math.random() * 100);
    }
    /// Creates a new point translated by dx and dy
    public Point translate(int dx, int dy) {
        return new Point(this.x + dx, this.y + dy);
    }
}
