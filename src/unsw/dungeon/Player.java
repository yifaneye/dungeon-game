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
	
	public static int hasSwordHits = 0;
	public static int hasInvincibilityMoves = 0;
	public static int hasKeyID = -1;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	Goal goal = new Goal();
	
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
	
	public Enemy hasEnemy(int x, int y) {
		Enemy enemy = new Enemy(dungeon, -1, -1, dungeon.getPlayersubject());
		List<Entity> el = dungeon.getEntities();
		for (Entity e : el) {
			if (e instanceof Enemy && x == e.getX() && y == e.getY()) {
				return (Enemy) e;
			}
		}
		return enemy;
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
				if (hasInvincibilityMoves > 0) hasInvincibilityMoves--;
			} else if ((b.getY() != -1 && boulderCanMove(getX(), getY() - 2))) {
				b.y().set(getY() - 2);
				y().set(getY() - 1);
				if (hasInvincibilityMoves > 0) hasInvincibilityMoves--;
			}
		}
	}

	public void moveDown() {
		System.out.println("Switch " + dungeon.getTotalSwitch() +"\n");
		System.out.println("Enemy " + dungeon.getTotalEnemies() +"\n");
		System.out.println("Tresure " + dungeon.getTotalTreasure() +"\n");
		
		
		if (getX() < dungeon.getWidth() - 1) {
			Boulder b = hasBoulder(getX(), getY() + 1);
			if (playerCanMove(getX(), getY() + 1)) {
				y().set(getY() + 1);
				if (hasInvincibilityMoves > 0) hasInvincibilityMoves--;
			} else if (b.getY() != -1 && boulderCanMove(getX(), getY() + 2)) {
				b.y().set(getY() + 2);
				y().set(getY() + 1);
				if (hasInvincibilityMoves > 0) hasInvincibilityMoves--;
			}
		}
	}

	public void moveLeft() {
		if (getX() > 0) {
			Boulder b = hasBoulder(getX() - 1, getY());
			if (playerCanMove(getX() - 1, getY())) {
				x().set(getX() - 1);
				if (hasInvincibilityMoves > 0) hasInvincibilityMoves--;
			} else if ((b.getX() != -1 && boulderCanMove(getX() - 2, getY()))) {
				b.x().set(getX() - 2);
				x().set(getX() - 1);
				if (hasInvincibilityMoves > 0) hasInvincibilityMoves--;
			}
		}
	}

	public void moveRight() {
		if (getX() < dungeon.getWidth() - 1) {
			Boulder b = hasBoulder(getX() + 1, getY());
			if (playerCanMove(getX() + 1, getY())) {
				x().set(getX() + 1);
				if (hasInvincibilityMoves > 0) hasInvincibilityMoves--;
			} else if (b.getX() != -1 && boulderCanMove(getX() + 2, getY())) {
				b.x().set(getX() + 2);
				x().set(getX() + 1);
				if (hasInvincibilityMoves > 0) hasInvincibilityMoves--;
			}
		}
	}
	
	public void collect() {
		System.out.println("space pressed with "+ dungeon.getEntities().size() + " entities remained");
		Entity en = dungeon.findEntity(getX(), getY());
		if (!(en instanceof Player) && en.ableToCollect()) {
			if (en instanceof Sword && hasSwordHits == 0) hasSwordHits = 5;
			else if (en instanceof Invincibility && hasInvincibilityMoves == 0) hasInvincibilityMoves = 15;
			else if (en instanceof Key && hasKeyID == -1) hasKeyID = ((Key) en).id;
			en.x().set(getX()+ dungeon.getWidth());
			dungeon.removeEntity(en); 
			System.out.println("removed entity with " + dungeon.getEntities().size() + " entites remain");
		} else {
			System.out.println("nothing to collect");
		}
		
	}
	
	public void reach() {
		Entity en = dungeon.findEntity(getX(), getY());
		System.out.print(en.getClass().getName());
		System.out.print("\n");
		if (!(en instanceof Player)) {
			System.out.print(en.isReachable());
			if (en.isReachable()) {
				if (en instanceof Exit) {
					//goal.setExitGoal(true);
					boolean ret = goal.checkGoal();
					if (ret) System.out.println("---you win---");
					else System.out.println("---you lose---");
				}
			}
		}
	}
	
	public void kill() {
		List<Entity> el = dungeon.getEntities();
		for (Entity e : el) {
			if (e instanceof Enemy && getX() == e.getX() && getY() == e.getY()) {
				if (this.isUnarmedPlayer()) {
					System.out.println("--- you lose ---");
					System.exit(1);
				} else {
					e.x().set(0);
					dungeon.removeEntity(e);
					System.out.println("--- you killed the enemy ---");
				}
			}
		}
	}
	
	public boolean isUnarmedPlayer() {
		if (hasSwordHits == 0 && hasInvincibilityMoves == 0) return true;
		else if (hasInvincibilityMoves == 0) hasSwordHits--;
		return false;
	}
	
}
