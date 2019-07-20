package unsw.dungeon;

public class Invincibility extends Entity {

	public Invincibility(int x, int y) {
	    super(x, y);
	}
	
    public boolean isCollectable() {
    	return true;
    }
    
}
