public class PairOfDice {
  private Die DieOne;
  private Die DieTwo;

  public void PairOfDice(int numberOfSides) {
    this.DieOne = new Die(numberOfSides);
    this.DieTwo = new Die(numberOfSides);
  }

  public int roll() {
    DieOne.roll();
    DieTwo.roll();
    return(DieOne.get(), DieTwo.get());
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
