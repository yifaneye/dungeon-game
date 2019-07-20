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

	public int hasSwordHits = 0;
	public int hasInvincibilityMoves = 0;
	public int hasKeyID = -1;

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
		Boulder boulder = null;
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
			if (x == e.getX() && y == e.getY() && (e instanceof Wall || e instanceof Boulder ) ) {
				return false;
			}
			if (x == e.getX() && y == e.getY() && e instanceof Door) {
				if(!((Door)e).canOpen(this) && !((Door)e).canGetThough()) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean boulderCanMove(int x, int y) {
		List<Entity> el = dungeon.getEntities();
		for (Entity e : el) {
			if ((e instanceof Wall || e instanceof Boulder || e instanceof Door) && x == e.getX() && y == e.getY()) {
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
				if (hasInvincibilityMoves > 0)
					hasInvincibilityMoves--;
			} else if ((b != null && boulderCanMove(getX(), getY() - 2))) {
				b.y().set(getY() - 2);
				y().set(getY() - 1);
				if (hasInvincibilityMoves > 0)
					hasInvincibilityMoves--;
			}
		}
	}

	public void moveDown() {
		if (getX() < dungeon.getWidth() - 1) {
			Boulder b = hasBoulder(getX(), getY() + 1);
			if (playerCanMove(getX(), getY() + 1)) {
				y().set(getY() + 1);
				if (hasInvincibilityMoves > 0)
					hasInvincibilityMoves--;
			} else if (b != null && boulderCanMove(getX(), getY() + 2)) {
				b.y().set(getY() + 2);
				y().set(getY() + 1);
				if (hasInvincibilityMoves > 0)
					hasInvincibilityMoves--;
			}
		}
	}

	public void moveLeft() {
		if (getX() > 0) {
			Boulder b = hasBoulder(getX() - 1, getY());
			if (playerCanMove(getX() - 1, getY())) {
				x().set(getX() - 1);
				if (hasInvincibilityMoves > 0)
					hasInvincibilityMoves--;
			} else if ((b != null && boulderCanMove(getX() - 2, getY()))) {
				b.x().set(getX() - 2);
				x().set(getX() - 1);
				if (hasInvincibilityMoves > 0)
					hasInvincibilityMoves--;
			}
		}
	}

	public void moveRight() {
		if (getX() < dungeon.getWidth() - 1) {
			Boulder b = hasBoulder(getX() + 1, getY());
			if (playerCanMove(getX() + 1, getY())) {
				x().set(getX() + 1);
				if (hasInvincibilityMoves > 0)
					hasInvincibilityMoves--;
			} else if (b != null && boulderCanMove(getX() + 2, getY())) {
				b.x().set(getX() + 2);
				x().set(getX() + 1);
				if (hasInvincibilityMoves > 0)
					hasInvincibilityMoves--;
			}
		}
	}

	public void collect() {

		System.out.print("space pressed with " + dungeon.getEntities().size() + " entities remained\n");
		Entity en = dungeon.findEntity(getX(), getY());
		if (!(en instanceof Player) && en.ableToCollect() == true) {
			if (en instanceof Sword && hasSwordHits == 0) {
				en.x().set(getX() + dungeon.getWidth());
				dungeon.removeEntity(en);
				hasSwordHits = 5;
			} else if (en instanceof Invincibility && hasInvincibilityMoves == 0) {
				en.x().set(getX() + dungeon.getWidth());
				dungeon.removeEntity(en);
				hasInvincibilityMoves = 15;
			} else if (en instanceof Key && hasKeyID == -1) {
				en.x().set(getX() + dungeon.getWidth());
				dungeon.removeEntity(en);
				hasKeyID = ((Key) en).id;
			} else if (en instanceof Treasure) {
				en.x().set(getX() + dungeon.getWidth());
				dungeon.removeEntity(en);
				dungeon.setTreasureNumber(dungeon.getTreasureNumber() + 1);
			}
			System.out.print("removed entity with " + dungeon.getEntities().size() + " entites remain\n");

		} else {
			System.out.print("nothing to collect\n");
		}

	}

	public void reach() {
		Entity en = dungeon.findEntity(getX(), getY());
		System.out.print(en.getClass().getName());
		System.out.print("\n");
		if (en instanceof Exit) {
			dungeon.getGoal().setReachExit(true);
			boolean ret = dungeon.getGoal().checkGoals();
			if (ret)
				System.out.print("--- you win ---");
			else
				System.out.print("--- you lose ---");
			System.exit(0);
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
		if (hasSwordHits == 0 && hasInvincibilityMoves == 0)
			return true;
		else if (hasInvincibilityMoves == 0)
			hasSwordHits--;
		return false;
	}

	public Dungeon getDungeon() {
		return dungeon;
	}

	public int getHasKeyID() {
		return hasKeyID;
	}
}
