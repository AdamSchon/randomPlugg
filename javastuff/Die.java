import java.util.Scanner;

public class Die {
  private int numberOfSides;
  private int value;

  public Die() {
      this.Die(6);
  }

  public Die(int numberOfSides) {
    if (numberOfSides < 1) {
      throw new IlligalArgumentException("Illegal number of sides for die");
    }
      this.numberOfSides = numberOfSides;
  }

  public int roll() {
    this.value = (int) (Math.random() * numberOfSides) + 1;
    return this.get();
  }

  public int get() {
      return value;
  }

  public static void main(String [] args) {
      Scanner sc = new Scanner(System.in);
      System.out.print("Number of sides: ");
      int sides = sc.nextInt();
      Die d = new Die(sides);
      System.out.println("Alea iacta est: " + d.roll());
  }
}
