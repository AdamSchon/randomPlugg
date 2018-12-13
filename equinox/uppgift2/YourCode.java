/*
  NOTE! 
  This is the only file you are allowed to make changes too as it
  is the only file that is handed in by make handin!

  To help you, this file is divided into four parts:

  0. Additions -- a space for adding any new classes needed
  1. Dirty extensions -- classes whose methods you will likely have to change
  2. Clean extensions -- classes that you will likely have to add new methods to
  3. Ignore -- stuff you can likely safely ignore

 */

////////////////////////////////////////////////////////////////////////////////
/// ADDITIONS //////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////////////
/// DIRTY EXTENSIONS ///////////////////////////////////////////////////////////

/// Holds all the logic for + and - and is used by Addition and Subtraction in ASTFixed.java.
class Calculation {
    public static Constant add(Constant a, Constant b) {
        if (a.isInteger() && b.isInteger()) {
            return new Integer(a.value().intValue() + b.value().intValue());
        } else {
            throw new RuntimeException("Bottom used as a value!");
        }
    }

    public static Constant sub(Constant a, Constant b) {
        if (a.isInteger() && b.isInteger()) {
            return new Integer(a.value().intValue() - b.value().intValue());
        } else {
            throw new RuntimeException("Bottom used as a value!");
        }
    }
}

////////////////////////////////////////////////////////////////////////////////
/// CLEAN EXTENSIONS ///////////////////////////////////////////////////////////

abstract class Constant extends Expression {
    private final Number value;  /// You can see how this is used with Integer!

    public Constant(Number value) {
        this.value = value;
    }

    public boolean isInteger() {
        return false;
    }

    public boolean isBottom() {
        return false;
    }

    public Constant eval(Environment e) {
        return this;
    }

    public Number value() {
        return this.value; 
    }
    
    public String toString() {
        return this.value().toString(); 
    }
}

////////////////////////////////////////////////////////////////////////////////
/// IGNORE /////////////////////////////////////////////////////////////////////

// Required by Java so we can name the file YourCode.java 
public class YourCode {}

