package unsw.dungeon;

public class Treasure extends Entity {

	public Treasure(int x, int y, int num) {
		super(x, y, num);
	}

	public boolean isCollectable() {
		return true;
	}

}
