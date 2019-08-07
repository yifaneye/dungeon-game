package unsw.dungeon;

public class Sword extends Entity {

	public Sword(int x, int y, int num) {
		super(x, y, num);
	}

	public boolean isCollectable() {
		return true;
	}
}
