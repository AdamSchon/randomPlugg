public class Driver {
    private static Statement decl(String id) { return new VariableDeclaration(id); }
    private static Statement assign(String id, Expression e) { return new Assignment(id, e); }
    private static Statement print(Expression e) { return new Print(e); }
    private static Statement seq(Statement... ss) { return new Sequence(ss); }

    private static Expression var(String id) { return new Variable(id); }
    private static Expression add(Expression e1, Expression e2) { return new Addition(e1, e2); }
    private static Expression sub(Expression e1, Expression e2) { return new Subtraction(e1, e2); }

    private static Expression i(int i) { return new Integer(i); }
    private static Expression f(double i) { return new Float(i); } /// TODO: return new float instead
    private static Expression b() { return new Bottom(); }

    private static void execute(String msg, Statement s) {
        System.out.print(msg + ": ");
        try {
            s.eval(new Environment());
        } catch (Exception e) {
            System.out.println("A/An " + e.getClass().getName() + " was thrown (" + e.getMessage() + ")");
        }
    }

    public static void example1() {
        Statement s = seq(decl("x"),                                 /// var x;
                          decl("y"),                                 /// var y;
                          decl("z"),                                 /// var z;
                          assign("x", i(7)),                         /// x = 7;
                          assign("y", i(42)),                        /// y = 42;
                          assign("z", add(var("x"), var("y"))),      /// z = x + y;
                          print(var("z")));                          /// print(z);
        execute("example1", s);
    }

    public static void example2() {
        Statement s = seq(decl("z"),                                 /// var z;
                          print(var("z")));                          /// print(z);
        execute("example2", s);
    }

    public static void example3() {
        Statement s = seq(decl("x"),                                 /// var x;
                          decl("y"),                                 /// var y;
                          print(add(var("x"), var("y"))));           /// print(x + y);
        execute("example3", s);
    }

    public static void example4() {
        Statement s = seq(print(var("z")));                          /// print(z);
        execute("example4", s);
    }

    public static void test0() {
        Statement s = seq(print(f(42.0)));                           /// print(42.0);
        execute("test0", s);
    }

    public static void test1() {
        Statement s = seq(decl("x"),                                 /// var x;
                          assign("x", f(1.0)),                       /// x = 1.0;
                          print(var("x")));                          /// print(x);
        execute("test1", s);
    }

    public static void test2() {
        Statement s = seq(decl("x"),                                 /// var x;
                          decl("y"),                                 /// var y;
                          assign("x", f(1.0)),                       /// x = 1.0;
                          assign("y", f(5.0)),                       /// y = 5.0;
                          print(add(var("x"), var("y"))));           /// print(x + y);
        execute("test2", s);
    }

    public static void test3() {
        Statement s = seq(decl("x"),                                 /// var x;
                          decl("y"),                                 /// var y;
                          assign("x", i(2)),                         /// x = 2;
                          assign("y", f(5.0)),                       /// y = 5.0;
                          print(add(var("x"), var("y"))));           /// print(x + y);
        execute("test3", s);
    }

    public static void test4() {
        Statement s = seq(decl("x"),                                 /// var x;
                          decl("y"),                                 /// var y;
                          assign("x", f(2.0)),                       /// x = 2.0;
                          assign("y", i(6)),                         /// y = 6;
                          print(add(var("x"), var("y"))));           /// print(x + y);
        execute("test4", s);
    }

    public static void test5() {
        Statement s = seq(decl("x"),                                 /// var x;
                          decl("y"),                                 /// var y;
                          assign("x", f(1.0)),                       /// x = 1.0;
                          print(add(var("x"), var("y"))));           /// print(x + y);
        execute("test5", s);
    }

    public static void test6() {
        Statement s = seq(decl("x"),                                 /// var x;
                          decl("y"),                                 /// var y;
                          assign("y", f(5.0)),                       /// y = 5.0;
                          print(add(var("x"), var("y"))));           /// print(x + y);
        execute("test6", s);
    }

    public static void test7() {
        Statement s = seq(decl("x"),                                 /// var x;
                          decl("y"),                                 /// var y;
                          assign("x", f(1.0)),                       /// x = 1.0;
                          assign("y", f(5.0)),                       /// y = 5.0;
                          print(sub(var("x"), var("y"))));           /// print(x - y);
        execute("test7", s);
    }

    public static void test8() {
        Statement s = seq(decl("x"),                                 /// var x;
                          decl("y"),                                 /// var y;
                          assign("x", i(1)),                         /// x = 1;
                          assign("y", f(5.0)),                       /// y = 5.0;
                          print(sub(var("x"), var("y"))));           /// print(x - y);
        execute("test8", s);
    }

    public static void test9() {
        Statement s = seq(decl("x"),                                 /// var x;
                          decl("y"),                                 /// var y;
                          assign("x", f(1.0)),                       /// x = 1.0;
                          assign("y", i(5)),                         /// y = 5;
                          print(sub(var("x"), var("y"))));           /// print(x - y);
        execute("test9", s);
    }

    public static void test10() {
        Statement s = seq(decl("x"),                                 /// var x;
                          decl("y"),                                 /// var y;
                          assign("x", f(9.0)),                       /// x = 9.0;
                          print(sub(var("x"), var("y"))));           /// print(x - y);
        execute("test10", s);
    }

    public static void test11() {
        Statement s = seq(decl("x"),                                 /// var x;
                          decl("y"),                                 /// var y;
                          assign("y", f(12.0)),                      /// y = 12.0;
                          print(sub(var("x"), var("y"))));           /// print(x - y);
        execute("test11", s);
    }

    public static void test12() {
        Statement s = seq(decl("x"),                                 /// var x;
                          decl("y"),                                 /// var y;
                          decl("z"),                                 /// var z;
                          assign("x", f(7.0)),                       /// x = 7.0;
                          assign("y", var("x")),                     /// y = x;
                          assign("x", add(var("x"), i(1))),          /// x = x + 1;
                          assign("z", sub(var("x"), var("y"))),      /// z = x - y;
                          print(add(var("z"), var("z"))));           /// print(z + z);
        execute("test12", s);
    }

    public static void testRegression() {
        Statement s = seq(decl("x"),                                 /// var x;
                          decl("y"),                                 /// var y;
                          assign("x", i(2)),                         /// x = 2;
                          assign("y", i(6)),                         /// y = 6;
                          print(add(var("x"), var("y"))));           /// print(x + y);
        execute("testRegression", s);
    }

    public static void main(String[] args) {
        try {
            System.err.println("---- Examples from exam text (should work from the start) ----");
            example1();
            example2();
            example3();
            example4();

            System.err.println("\n---- Tests from exam text and beyond (should NOT work from the start) ----");
            System.err.println("  Note that you need to change one line in Driver.java to get these to work!!!");
            test0();
            test1();
            test2();
            test3();
            test4();
            test5();
            test6();
            test7();
            test8();
            test9();
            test10();
            test11();
            test12();

            testRegression();
        } catch (DriverNotFixedException e) {
            System.exit(1);
        }
    }
}

class DriverNotFixedException extends RuntimeException {}
