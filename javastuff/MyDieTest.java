import java.util.Scanner;
//package Die;
public class MyDieTest {
  public static void main(String [] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("How many sides should the die have?");
    int sides = sc.nextInt();
    PairOfDice d = new PairOfDice(sides);
    for (int i = 0; i < 10; i++) {
      d.roll();
      System.out.println(d.toString());
    }
  }
}
