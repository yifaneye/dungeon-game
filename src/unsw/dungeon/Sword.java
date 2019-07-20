package unsw.dungeon;

public class Sword extends Entity {

    public Sword(int x, int y) {
        super(x, y);
    }

    public boolean ableToCollect () {
    	return true;
    }
}
