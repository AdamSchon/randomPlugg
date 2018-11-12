public class PairOfDice {
  private Die DieOne;
  private Die DieTwo;

  public PairOfDice(int numberOfSides) {
    this.DieOne = new Die(numberOfSides);
    this.DieTwo = new Die(numberOfSides);
  }

  public void roll() {
    DieOne.roll();
    DieTwo.roll();
  }

  public int getOne() {
    return(DieOne.get());
  }

  public int getTwo() {
    return(DieTwo.get());
  }

  public String toString() {
    return("Die one is: " + DieOne.get() + ". Die two is: " + DieTwo.get() + ".");
  }
}
