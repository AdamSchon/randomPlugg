/// Note, even though it is not good Java practise -- you will be
/// required to write *all* your Java code in this file!!!
///
/// Remember, you can only have one public class/interface per file.
/// (Remaining classes/interfaces must be package scoped.)
///
/// Remember, you shall implement the following classes:
/// - Factory, NoRadiusException, Polygon, Rectangle, Circle, Square, Triangle
/// (Polygon, Rectangle, Circle, Square, Triangle must all implement the Shape interface.)
///
/// A factory should know how to build squares, rectangles,
/// triangles and circles, according to the specification given in
/// the ShapeFactory interface.
///
/// A NoRadiusException is thrown when asking a shape that is not a circle for its radius
/// 
/// It should not be possible to create instances of Polygon.
///
/// Rectangles, Squares and Triangles are polygons -- model that through inheritance.
/// Squares are rectangles -- model that through inheritance.
///
/// NOTE: Whether you calculate area and circumference correctly is NOT important!!!!
/// But if you do it, here are some helper methods/constants that you may want to use: 
/// Math.abs()  -- makes an absolute value
/// Math.sqrt() -- square root
/// Math.PI     -- the Pi constant
///
/// Circumference of circle: 2 * PI * radius
/// Aread of circle: PI * radius * radius 
/// Circumference of triangle: use Pythagoras to calculate hypothenuse...
public class Factory implements ShapeFactory {
    public Shape buildSquare(Point upperLeft, int side) {
        return new Square(upperLeft, side);
    }
    public Shape buildRectangle(Point upperLeft, Point lowerRight) {
        return new Rectangle(upperLeft, lowerRight); 
    }
    public Shape buildTriangle(Point top, int height, int base) {
        return new Triangle(top, height, base);
    }
    public Shape buildCircle(Point center, int radius) {
        return new Circle(center, radius);
    }
}

