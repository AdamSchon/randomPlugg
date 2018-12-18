public interface ShapeFactory {
    /// This function will be inherited by your factory class -- no need to change anything
    default public Shape buildRandomShape(int percent) {
        if (percent < 25) {
            return buildSquare(Point.random(), (int) Math.random() * 100);
        } 
        if (percent < 50) {
            return buildRectangle(Point.random(), Point.random());
        } 
        if (percent < 75) {
            return buildTriangle(Point.random(), (int) Math.random() * 100, (int) Math.random() * 100);
        } 
        if (percent < 100) {
            return buildCircle(Point.random(), (int) Math.random() * 100);
        }
        throw new IllegalArgumentException("Invalid percentage: " + percent);
    }
    /// Returns a Square. 
    /// All Squares are shaped like this:
    ///     
    ///  upperLeft________
    ///          |        |
    ///          |        |
    ///          |        | side
    ///          |        |
    ///          |________| 
    ///             side
    ///
    /// Squares are specialisations of rectangles -- which should
    /// be modelled through inheritance.
    public Shape buildSquare(Point upperLeft, int side);
    /// Returns a Rectangle 
    /// All Rectangles are shaped like this:
    ///     
    ///  upperLeft________
    ///          |        |
    ///          |        |
    ///          |        | 
    ///          |        |
    ///          |________|lowerRight
    ///
    public Shape buildRectangle(Point upperLeft, Point lowerRight);
    /// Returns a Triangle
    /// For simplicity -- all triangles are shaped like this:
    ///
    ///              top
    ///              /\        |
    ///             /  \       |
    ///            /    \      | height
    ///           /      \     |
    ///          /________\    | 
    ///             base
    ///
    public Shape buildTriangle(Point top, int height, int base);
    /// Returns a Circle 
    /// All Circles are shaped like this:
    ///     
    ///              | 
    ///              | radius
    ///              |
    ///              *
    ///            center
    ///
    public Shape buildCircle(Point center, int radius);
}
