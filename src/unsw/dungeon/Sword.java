package unsw.dungeon;

public class Sword extends Entity {

    public Sword(int x, int y) {
        super(x, y);
    }

	public char collect(int x, int y) {
		if (x == this.getX() && y == this.getY()) return 's';
		else return '-';
	}
}
