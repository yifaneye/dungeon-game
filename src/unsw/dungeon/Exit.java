package unsw.dungeon;

public class Exit extends Entity {

    public Exit(int x, int y, int num) {
		super(x, y, num);
	}
    
    public boolean isReachable() {
    	return true;
    }

}
