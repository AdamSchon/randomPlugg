import java.util.Scanner;
//package Die;
public class MyDieTest {
  public static void main(String [] args) {
    /*
    Scanner sc = new Scanner(System.in);
    System.out.println("How many sides should the die have?");
    int sides = sc.nextInt();
    PairOfDice d = new PairOfDice(sides);
    for (int i = 0; i < 10; i++) {
      d.roll();
      System.out.println(d.toString());
    }
    */
    String a = "Beefheart";
    String b = "Beefheart";
    String c = "heart";
    String d = "Beef" + c;
    int i = 1;
    int j = 1;
    Integer k = 2;
    Integer l = i + j;
    System.out.println(a == b);
    System.out.println(a.equals(b));
    System.out.println(a == d);
    System.out.println(a.equals(d));
    System.out.println(i == j);
    System.out.println(k == i + j);
    System.out.println(k.equals(i+j));
    System.out.println(k == l);
    System.out.println(k.equals(l));
  }
}
