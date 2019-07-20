package unsw.dungeon;

public class Door extends Entity {
	
	public int id;
	
    public Door(int x, int y, int id) {
        super(x, y);
        this.id = id;
    }
    
    public boolean isReachable() {
    	return true;
    }
    
}
