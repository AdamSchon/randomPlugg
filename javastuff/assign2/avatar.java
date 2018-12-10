

public class Avatar {

    private int HP;
    private Course<list> finished;
    private Course<list> unfinished;
    private int backpackWeight;

    public static Avatar() {
      this.HP = 60;
      this.backpackWeight = 10;
    }

    public String toString() {
      return("The avatar has finished " + this.HP + " HP and the backpack weights " + this.backpackWeight + ".");
    }
}
