public interface Shape {
    /// Returns upperleft and lowerright for square and rectangle
    /// Return the three corners of a triangle
    /// Returns the center of a circle
    Point[] getPoints();
    /// Returns the radius of a circle, othewise throws an exception
    int radius();
    /// Returns the area of the shape
    double area();
    /// Returns the circumference (omkrets) of the shape
    double circumference();
    /// Returns "Square", "Rectangle", "Triangle" or "Circle" 
    default String name() {
        return this.getClass().getName();
    }
}
