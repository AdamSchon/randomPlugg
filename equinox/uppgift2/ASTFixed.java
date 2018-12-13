////////////////////////////////////////////////////////////////////////////////
/// HELP CLASSES ///////////////////////////////////////////////////////////////

class Environment extends java.util.TreeMap<String, Constant> {
    public boolean hasVariable(String variableName) {
        return this.get(variableName) != null;
    }
    public void updateVariable(String variableName, Constant value) {
        this.put(variableName, value);
    }
    public Constant readVariable(String variableName) {
        return this.get(variableName);
    }
}

////////////////////////////////////////////////////////////////////////////////
/// STATEMENTS /////////////////////////////////////////////////////////////////

/// A statement does not return a value, e.g. x = 42; or print(42);
/// This is modelled by all statements returning Bottom
abstract class Statement {
    public Constant eval(Environment e) {
        return new Bottom();
    }
}

/// id = source;
class Assignment extends Statement {
    final Expression source;
    final String id;

    public Assignment(String id, Expression source) {
        this.id = id; 
        this.source = source;
    }

    public Constant eval(Environment e) {
        if (e.hasVariable(this.id)) {
            e.updateVariable(this.id, this.source.eval(e));
            return super.eval(e);
        } else {
            throw new RuntimeException("Attempt to assign undeclared variable");
        }
    }
}

// Makes the variable available for updating 
// var x; 
class VariableDeclaration extends Statement {
    final String id;

    public VariableDeclaration(String id) {
        this.id = id; 
    }

    public Constant eval(Environment e) {
        e.updateVariable(this.id, new Bottom());
        return super.eval(e);
    }
}

// A sequence of statements, e.g., var x; x = 1; x = x + 1;
class Sequence extends Statement {
    public final Statement[] statements;

    // new Sequence expects a variable number of arguments
    public Sequence(Statement... statements) {
        this.statements = statements;
    }

    public Constant eval(Environment e) {
        for (Statement s : this.statements) {
            s.eval(e);
        }
        return super.eval(e);
    }
}

// Print the result of an expression, e.g.,
// print(3 + 52)
class Print extends Statement {
    final Expression source;

    public Print(Expression source) {
        this.source = source;
    }

    public Constant eval(Environment e) {
        System.out.println(this.source.eval(e));
        return super.eval(e);
    }
}

////////////////////////////////////////////////////////////////////////////////
/// EXPRESSIONS ////////////////////////////////////////////////////////////////

/// An expression returns a value, e.g. 3 or x + 5
abstract class Expression extends Statement {}

class Variable extends Expression {
    final String id;

    public Variable(String id) {
        this.id = id; 
    }

    public Constant eval(Environment e) {
        Constant result = e.readVariable(this.id);
        if (result == null) {
            throw new RuntimeException("Access to undeclared variable!");
        } else {
            return result;
        }
    }
}

abstract class Binary extends Expression {
    public static final int ADDITION = 0;
    public static final int SUBTRACTION = 1;
    
    final Expression left;
    final Expression right;

    public Binary(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public abstract int operand();

    public Constant eval(Environment e) {
        Constant left = this.left.eval(e);
        Constant right = this.right.eval(e);
        
        switch (this.operand()) {
        case Binary.ADDITION:
            return Calculation.add(left, right);
        case Binary.SUBTRACTION:
            return Calculation.sub(left, right);
        default:
            throw new RuntimeException("Unknown operand in binary expression!");
        }
    }
}

class Addition extends Binary  {
    public Addition(Expression left, Expression right) {
        super(left, right);
    }
    
    public int operand() {
        return Binary.ADDITION;
    }
}

class Subtraction extends Binary  {
    public Subtraction(Expression left, Expression right) {
        super(left, right);
    }
    
    public int operand() {
        return Binary.SUBTRACTION;
    }
}

class Bottom extends Constant {
    public Bottom() {
        super(null);
    }
    
    public boolean isBottom() {
        return true;
    }

    public Number value() {
        throw new RuntimeException("_|_ used as a value");
    }

    public String toString() {
        return "<bottom>";
    }
}

final class Integer extends Constant {
    public Integer(int value) {
        super(value);
    }

    public boolean isInteger() {
        return true;
    }
}

////////////////////////////////////////////////////////////////////////////////
/// IGNORE /////////////////////////////////////////////////////////////////////

// Required by Java so we can name the file ASTFixed.java 
public class ASTFixed {}

