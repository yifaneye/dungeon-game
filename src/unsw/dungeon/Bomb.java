package unsw.dungeon;

public class Bomb extends Entity {
	/**
	 * 
	 */
	public Bomb(int x, int y, int num) {
		super(x, y, num);
	}
	/**
	 * 
	 */
	public boolean isCollectable() {
		return true;
	}
}
