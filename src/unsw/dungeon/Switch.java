package unsw.dungeon;

import java.util.List;

public class Switch extends Entity {
	
    public Switch(int x, int y) {
        super(x, y);
    }
    
    public boolean switchOpen(Dungeon dungeon) {
    	List<Entity> el = dungeon.getEntities();
    	for (Entity e : el) {
    		if (e instanceof Boulder && getX() == e.getX() && getY() == e.getY()) {
    			return true;
    		}
    	}
    	return false;
    }
    
}
