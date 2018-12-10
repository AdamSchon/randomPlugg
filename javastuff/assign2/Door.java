public class Door {
  private boolean locked;

  public Door(lock) {
    this.locked = lock;
  }

  public boolean isLocked() {
    return this.locked;
  }

  public boolean unlock() {
    if (this.locked) {
      this.locked = false;
      return true;
    }
    return false;
  }
}
