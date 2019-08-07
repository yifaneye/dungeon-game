package unsw.dungeon;

public class Invincibility extends Entity {

	public Invincibility(int x, int y, int num) {
		super(x, y, num);
	}

	public boolean isCollectable() {
		return true;
	}

}
