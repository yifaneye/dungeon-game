package unsw.dungeon;

import java.util.List;

/**
 * The player entity
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Player extends Entity {

	private Dungeon dungeon;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Player(Dungeon dungeon, int x, int y) {
		super(x, y);
		this.dungeon = dungeon;
	}

	public Boulder hasBoulder(int x, int y) {
		Boulder boulder = new Boulder(-1, -1);
		List<Entity> el = dungeon.getEntities();
		for (Entity e : el) {
			if (e instanceof Boulder && x == e.getX() && y == e.getY()) {
				return (Boulder) e;
			}
		}
		return boulder;
	}
	
	public boolean playerCanMove(int x, int y) {
		List<Entity> el = dungeon.getEntities();
		for (Entity e : el) {
			if ((e instanceof Wall || e instanceof Boulder) && x == e.getX() && y == e.getY()) {
				return false;
			}
		}
		return true;
	}

	public boolean boulderCanMove(int x, int y) {
		List<Entity> el = dungeon.getEntities();
		for (Entity e : el) {
			if ((e instanceof Wall || e instanceof Boulder) && x == e.getX() && y == e.getY()) {
				return false;
			}
		}
		return true;
	}

	public void moveUp() {
		if (getX() > 0) {
			Boulder b = hasBoulder(getX(), getY() - 1);
			if (playerCanMove(getX(), getY() - 1)) {
				y().set(getY() - 1);
			} else if ((b.getY() != -1 && boulderCanMove(getX(), getY() - 2))) {
				b.y().set(getY() - 2);
				y().set(getY() - 1);
			}
		}
	}

	public void moveDown() {
		if (getX() < dungeon.getWidth() - 1) {
			Boulder b = hasBoulder(getX(), getY() + 1);
			if (playerCanMove(getX(), getY() + 1)) {
				y().set(getY() + 1);
			} else if (b.getY() != -1 && boulderCanMove(getX(), getY() + 2)) {
				b.y().set(getY() + 2);
				y().set(getY() + 1);
			}
		}
	}

	public void moveLeft() {
		if (getX() > 0) {
			Boulder b = hasBoulder(getX() - 1, getY());
			if (playerCanMove(getX() - 1, getY())) {
				x().set(getX() - 1);
			} else if ((b.getX() != -1 && boulderCanMove(getX() - 2, getY()))) {
				b.x().set(getX() - 2);
				x().set(getX() - 1);
			}
		}
	}

	public void moveRight() {
		if (getX() < dungeon.getWidth() - 1) {
			Boulder b = hasBoulder(getX() + 1, getY());
			if (playerCanMove(getX() + 1, getY())) {
				x().set(getX() + 1);
			} else if (b.getX() != -1 && boulderCanMove(getX() + 2, getY())) {
				b.x().set(getX() + 2);
				x().set(getX() + 1);
			}
		}
	}
	
	public void collect() {
		
		System.out.print("space pressed with "+ dungeon.getEntities().size() + " entities remained\n");
		Entity en = dungeon.findEntity(getX(), getY());
		if (!(en instanceof Player) && en.ableToCollect() == true) {
			dungeon.removeEntity(en); 
			en.x().set(getX()+ dungeon.getWidth());
			System.out.print("removed entity with " + dungeon.getEntities().size() + " entites remain\n");
		}else {
			System.out.print("nothing to collect\n");
		}
		
	}
	
}
