package unsw.dungeon;

public class Key extends Entity {

	public int id;

	public Key(int x, int y, int id) {
		super(x, y);
		this.id = id;
	}

	public boolean ableToCollect() {
		return true;
	}

}
