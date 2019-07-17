package unsw.dungeon;

public class Invincibility extends Entity {

	public Invincibility(int x, int y) {
	    super(x, y);
	}
	
	public char collect(int x, int y) {
		if (x == this.getX() && y == this.getY()) return 'i';
		else return '-';
	}

}
