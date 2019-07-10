package unsw.dungeon;

/**
 * The player entity
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

    private Dungeon dungeon;

    /**
     * Create a player positioned in square (x,y)
     * @param x
     * @param y
     */
    public Player(Dungeon dungeon, int x, int y) {
        super(x, y);
        this.dungeon = dungeon;
    }

    public boolean canMove(int x, int y) {
    	for (Entity e : dungeon.getEntities()) {
    		if (e.getClass().getName() == "Wall" && x == e.getX() && y == e.getY()) {
    			return false;
    		}
    	}  	
    	return true;
    }
    
    public void moveUp() {
    	if (canMove(getX(), getY() - 1)) {
    		if (getY() > 0) {
    			y().set(getY() - 1);
    		}
    	}
    }

    public void moveDown() {
        if (getY() < dungeon.getHeight() - 1)
            y().set(getY() + 1);
    }

    public void moveLeft() {
    	if (canMove(getX() - 1, getY())) {
   		 	if (getX() > 0) {
   		 		x().set(getX() - 1);
   		 	}
    	}
    }

    public void moveRight() {
        if (getX() < dungeon.getWidth() - 1)
            x().set(getX() + 1);
    }
}
