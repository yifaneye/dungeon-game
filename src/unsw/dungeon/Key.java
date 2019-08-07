package unsw.dungeon;

public class Key extends Entity {

	public int id;

	public Key(int x, int y, int id, int num) {
		super(x, y, num);
		this.id = id;
	}

	public boolean isCollectable() {
		return true;
	}

}
