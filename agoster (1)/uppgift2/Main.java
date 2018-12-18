public class Main {
    private static void assertSubclass(Shape s, String className) {
        try {
            Class<?> expectedClass = Class.forName(className);

            for (Class<?> c = s.getClass(); c != null; c = c.getSuperclass()) {
                if (c == expectedClass) return;
            }
                
            System.out.println("Error: " + s.name() + " is not a subclass of " + className); 
        } catch (ClassNotFoundException e) {
            System.out.println("Warning: could not find class/interface " + className + ". Maybe you did not yet implement it?");
        } 
    }

    private static void assertNotSubclass(Shape s, String className) {
        try {
            Class<?> expectedClass = Class.forName(className);

            for (Class<?> c = s.getClass(); c != null; c = c.getSuperclass()) {
                if (c == expectedClass) {
                    System.out.println("Error: " + s.name() + " is a subclass of " + className + " -- it should not be!");
                    return;
                }
            }
                
        } catch (ClassNotFoundException e) {
            System.out.println("Warning: could not find class/interface " + className + ". Maybe you did not yet implement it?");
        } 
    }

    private static void assertPoints(Shape s, int expectedNumberOfPoints) {
        if (s.getPoints().length != expectedNumberOfPoints) {
            System.out.println("Error: found a " + s.name() + " with " + s.getPoints().length + " number of sides (expected " + expectedNumberOfPoints + ")");
            /// To provoke errors if points are broken
            for (Point p : s.getPoints())
                {
                    p.translate(0, 0);
                }
        }
    }

    private static void assertNoRadius(Shape s) {
        try {
            s.radius();
            System.out.println("Error: was able to ask a " + s.name() + " for its radius!");
        } catch (NoRadiusException e) {
            /// All good! 
        }
    }

    private static void assertRadius(Shape s) {
        try {
            s.radius();
            /// All good! 
        } catch (NoRadiusException e) {
            System.out.println("Error: was unable to ask a " + s.name() + " for its radius!");
        }
    }

    private Main(ShapeFactory factory) {
        for (int i = 0; i < 4; ++i) {
            Shape s = factory.buildRandomShape(i * 25);

            if (s == null) {
                System.out.println("Error: got null from factory! (stopping program)");
                break;
            }
            if (s.name().equalsIgnoreCase("Square")) {
                assertPoints(s, 2);
                Point[] points = s.getPoints();
                int side1 = Math.abs(points[0].x - points[1].x);
                int side2 = Math.abs(points[0].y - points[1].y);
                if (side1 != side2) System.out.println("Error: found a square with sides of different length!");
                assertNoRadius(s);
                assertSubclass(s, "Square");
                assertSubclass(s, "Rectangle");
                assertSubclass(s, "Polygon");
                continue;
            } 
            if (s.name().equalsIgnoreCase("Rectangle")) {
                assertPoints(s, 2);
                assertNoRadius(s);                
                assertSubclass(s, "Rectangle");
                assertSubclass(s, "Polygon");
                continue;
            } 
            if (s.name().equalsIgnoreCase("Triangle")) {
                assertPoints(s, 3);
                assertNoRadius(s);
                assertSubclass(s, "Triangle");
                assertSubclass(s, "Polygon");
                continue;
            } 
            if (s.name().equalsIgnoreCase("Circle")) {
                assertPoints(s, 1);
                assertRadius(s);
                assertSubclass(s, "Circle");
                assertNotSubclass(s, "Polygon");
                continue;
            } 
        }
        
        System.out.println("-----\nIf you don't see any errors above the line, your program is probably fine!");
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java Main MyFactoryClassName");
        } else {
            try {
                ShapeFactory f = (ShapeFactory) Class.forName(args[0]).newInstance();
                Main m = new Main(f);
            } catch (ClassNotFoundException e) {
                System.out.println("Error " + e.getMessage());
                e.printStackTrace();
            } catch (InstantiationException e) {
                System.out.println("Error " + e.getMessage());
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                System.out.println("Error " + e.getMessage());
                e.printStackTrace();
            } 
        }
    }
}



