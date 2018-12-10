
public class Room {
  private Room northRoom;
  private Room southRoom;
  private Room eastRoom;
  private Room westRoom;

  private Door northDoor;
  private Door southDoor;
  private Door eastDoor;
  private Door westDoor;

  private String name;
  private int keys;

  public Room(String newName) {
    this.name = newName;
  }

  public void setRoom(Room room, String n) {
    if (n == "North") {
      this.northRoom = room;
    }
    if (n == "South") {
      this.southRoom = room;
    }
    if (n == "East") {
      this.eastRoom = room;
    }
    if (n == "West") {
      this.westRoom = room;
    }
  }

  public String getName() {
    return this.name;
  }

  public String toString() {
    String thing = "You are in " + this.name + ". The room contains " + this.keys + "keys.";
    if (this.northDoor != null) {
      if (this.northDoor.isLocked()) {
        thing += "\n The north door is locked";
      } else {
        thing += "\n To the north is " + this.northRoom.getName() + ".";
      }
    }
    if (this.southDoor != null) {
      if (this.southDoor.isLocked()) {
        thing += "\n The south door is locked";
      } else {
        thing += "\n To the south is " + this.southRoom.getName() + ".";
      }
    }
    if (this.eastDoor != null) {
      if (this.eastDoor.isLocked()) {
        thing += "\n The east door is locked";
      } else {
        thing += "\n To the east is " + this.eastRoom.getName() + ".";
      }
    }
    if (this.westDoor != null) {
      if (this.westDoor.isLocked()) {
        thing += "\n The west door is locked";
      } else {
        thing += "\n To the west is " + this.westRoom.getName() + ".";
      }
    }
    return(thing);
  }

}
