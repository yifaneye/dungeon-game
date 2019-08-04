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
	public int hasUnlitBombs = 0;

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
			if (x == e.getX() && y == e.getY() && (e instanceof Wall || e instanceof Boulder)) {
				return false;
			} else if (x == e.getX() && y == e.getY() && e instanceof Door) {
				if (!((Door) e).canOpen(this) && !((Door) e).canGetThough()) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean boulderCanMove(int x, int y) {
		List<Entity> el = dungeon.getEntities();
		for (Entity e : el) {
			if ((e instanceof Wall || e instanceof Boulder || e instanceof Door || e instanceof Enemy) 
					&& x == e.getX() && y == e.getY()) {
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
				action();
			} else if ((b != null && boulderCanMove(getX(), getY() - 2))) {
				b.y().set(getY() - 2);
				y().set(getY() - 1);
				action();
			}
		}
	}

	public void moveDown() {
		if (getX() < dungeon.getWidth() - 1) {
			Boulder b = hasBoulder(getX(), getY() + 1);
			if (playerCanMove(getX(), getY() + 1)) {
				y().set(getY() + 1);
				action();
			} else if (b != null && boulderCanMove(getX(), getY() + 2)) {
				b.y().set(getY() + 2);
				y().set(getY() + 1);
				action();
			}
		}
	}

	public void moveLeft() {
		if (getX() > 0) {
			Boulder b = hasBoulder(getX() - 1, getY());
			if (playerCanMove(getX() - 1, getY())) {
				x().set(getX() - 1);
				action();
			} else if ((b != null && boulderCanMove(getX() - 2, getY()))) {
				b.x().set(getX() - 2);
				x().set(getX() - 1);
				action();
			}
		}
	}

	public void moveRight() {
		if (getX() < dungeon.getWidth() - 1) {
			Boulder b = hasBoulder(getX() + 1, getY());
			if (playerCanMove(getX() + 1, getY())) {
				x().set(getX() + 1);
				action();
			} else if (b != null && boulderCanMove(getX() + 2, getY())) {
				b.x().set(getX() + 2);
				x().set(getX() + 1);
				action();
			}
		}
	}
	
	public void action() {
		kill();
		countdown();
		if (hasInvincibilityMoves > 0)
			this.setHasInvincibilityMoves(hasInvincibilityMoves-1);
	}
	
	public void drop() {
		if (hasUnlitBombs > 0) {
			Drop drop = new Drop(getX(), getY());
			dungeon.addEntity(drop);
			setHasUnlitBombs(hasUnlitBombs-1);
		}
	}

	public void countdown() {
		List<Entity> el = dungeon.getEntities();
		if (el == null) return;
		el.forEach(e->{
			if (e instanceof Drop) {
				if (((Drop) e).getCountdown() > 0)
					((Drop) e).setCountdown(((Drop) e).getCountdown() - 1);
				else {
					explode(e.getX(), e.getY());
					explode(e.getX() - 1, e.getY());
					explode(e.getX() + 1, e.getY());
					explode(e.getX(), e.getY() - 1);
					explode(e.getX(), e.getY() + 1);
					dungeon.removeEntity(e);
				}
			}
		});
	}

	public void explode(int x, int y) {
		List<Entity> el = dungeon.getEntities();
		if (el == null) return;
		el.forEach(e->{
			if (e.getX() == x && e.getY() == y && e instanceof Player) {
				System.out.println("--- you lose ---");
				System.exit(1);
			} else if (e.getX() == x && e.getY() == y && (e instanceof Enemy || e instanceof Boulder)) {
				e.x().set(getX() + dungeon.getWidth());
			}
		});
	}

	public void collect() {
		Entity e = dungeon.findEntity(getX(), getY());
		if (!(e instanceof Player) && e.isCollectable() == true) {
			if (e instanceof Sword) {
				e.x().set(getX() + dungeon.getWidth());
				dungeon.removeEntity(e);
				this.setHasSwordHits(5);
			} else if (e instanceof Invincibility) {
				e.x().set(getX() + dungeon.getWidth());
				dungeon.removeEntity(e);
				this.setHasInvincibilityMoves(15);
			} else if (e instanceof Key && hasKeyID == -1) {
				e.x().set(getX() + dungeon.getWidth());
				dungeon.removeEntity(e);
				this.setHasKeyID(((Key) e).id);
			} else if (e instanceof Treasure) {
				e.x().set(getX() + dungeon.getWidth());
				dungeon.removeEntity(e);
				dungeon.getGoal().setTreasureNumber(dungeon.getGoal().getTreasureNumber() + 1);
			} else if (e instanceof Bomb) {
				e.x().set(getX() + dungeon.getWidth());
				dungeon.removeEntity(e);
				this.setHasUnlitBombs(hasUnlitBombs+1);
			}
		}
	}

	public void reach() {
		Entity e = dungeon.findEntity(getX(), getY());
		//System.out.println(e.getClass().getName());
		if (e instanceof Exit) {
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
		if (el == null) return;
		el.forEach(e->{
			if (e instanceof Enemy && getX() == e.getX() && getY() == e.getY()) {
				if (this.isUnarmedPlayer()) {
					System.out.println("--- you lose ---");
					x().set(getX() + dungeon.getWidth());
					System.exit(0);
				} else {
					System.out.println("--- you killed the enemy ---");
					e.x().set(getX() + dungeon.getWidth());
					dungeon.getGoal().setEnemyNumber(dungeon.getGoal().getEnemyNumber() + 1);
					return;
				}
			}
		});
	}

	public boolean isUnarmedPlayer() {
		if (hasSwordHits <= 0 && hasInvincibilityMoves <= 0)
			return true;
		else if (hasInvincibilityMoves <= 0)
			this.setHasSwordHits(hasSwordHits-1);
		return false;
	}
	
	//setter & getter
	public Dungeon getDungeon() {
		return dungeon;
	}
	
	public void setDungeon(Dungeon dungeon) {
		this.dungeon = dungeon;
	}
	
	public int getHasSwordHits() {
		return hasSwordHits;
	}

	public void setHasSwordHits(int hasSwordHits) {
		this.hasSwordHits = hasSwordHits;
		this.dungeon.setSwordhitDisplay(hasSwordHits);
	}
	

	public int getHasInvincibilityMoves() {
		return hasInvincibilityMoves;
	}

	public void setHasInvincibilityMoves(int hasInvincibilityMoves) {
		this.hasInvincibilityMoves = hasInvincibilityMoves;
		this.dungeon.setInvincibleDisplay(hasInvincibilityMoves);
	}

	public int getHasUnlitBombs() {
		return hasUnlitBombs;
	}

	public void setHasUnlitBombs(int hasUnlitBombs) {
		this.hasUnlitBombs = hasUnlitBombs;
		this.dungeon.setBombDisplay(hasUnlitBombs);
	}

	public void setHasKeyID(int hasKeyID) {
		this.hasKeyID = hasKeyID;
		this.dungeon.setKeyIDDisplay(hasKeyID);
	}

	public int getHasKeyID() {
		return hasKeyID;
	}
	
}
