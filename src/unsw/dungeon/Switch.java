package unsw.dungeon;

import java.util.List;

public class Switch extends Entity {
	
	private Dungeon dungeon;
	
    public Switch(Dungeon dungeon, int x, int y) {
        super(x, y);
    }
    
    public boolean check_open() {
    	List<Entity> el = dungeon.getEntities();
		for (Entity e : el) {
			if ( e instanceof Boulder && this.getX() == e.getX() && this.getY() == e.getY()) {
				return true;
			}
		}
		return false;
    }

}
