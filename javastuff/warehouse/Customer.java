public class Customer {
  private int bornTime;
  private int groceries;

  public Customer(int born, int groc) {
    this.bornTime = born;
    this.groceries = groc;
  }

  public void serve() {
    if (groceries < 1) {
      throw new IllegalArgumentException("Customer has no groceries left");
    }
    this.groceries--;
  }

  public boolean isDone() {
    if (groceries < 0) {
      throw new IllegalArgumentException("Customer has negative amount of groceries");
    }
    if (groceries == 0) {
      return true;
    } else {
      return false;
    }
  }

  public String toString() {
    return("["+this.bornTime+ ", " + this.groceries+"]");
  }

  public static void main(String [] args) {
      Customer c = new Customer(1,4);
      while(!c.isDone()){
        c.serve();
        System.out.println("Isn't done");
      }
      System.out.println("Is done");
  }
}
