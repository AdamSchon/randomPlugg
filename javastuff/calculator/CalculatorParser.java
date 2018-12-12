
public class CalculatorParser {

  public SymbolicExpression expression() {
     SymbolicExpression result = term();
     while (st.ttype == '+' || st.ttype == '-') {
        int operation = st.ttype;
        st.nextToken();
        if (operation == '+') {
            result = new SymbolicExpression("Addition", result, term());
        } else {
            result = new SymbolicExpression("Subtraction", result, term());
        }
     }
     return result;
  }
  
}
