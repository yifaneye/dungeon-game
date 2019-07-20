package unsw.dungeon;

public class Exit extends Entity {

    public Exit(int x, int y) {
        super(x, y);
    }
    
    public boolean isReachable() {
    	return true;
    }

}
