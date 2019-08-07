package unsw.dungeon;

import java.util.List;

import javafx.scene.image.Image;

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
	public int hasKeyID = 0;
	public int hasUnlitBombs = 0;

	/**
	 * Create a player positioned in square (x,y)
	 * 
	 * @param x for x axis
	 * @param y for y axis
	 * @param dungeon add dungeon so that player can access dungeon data
	 * @param num is the index of entity
	 */
	public Player(Dungeon dungeon, int x, int y, int num) {
		super(x, y, num);
		this.dungeon = dungeon;
	}
	
	/**
	 * Check if boulder exist in square (x,y)
	 * 
	 * @param x for x axis
	 * @param y for y axis
	 * @return Boulder entity on position (x,y)
	 */
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
	
	/**
	 * Check if square (x,y) is blocked for player to be in
	 * 
	 * @param x for x axis
	 * @param y for y axis
	 * @return true if (x,y) can be access by player, false if (x,y) cannot be access by player
	 */
	public boolean playerCanMove(int x, int y) {
		List<Entity> el = dungeon.getEntities();
		for (Entity e : el) {
			if (x == e.getX() && y == e.getY() && (e instanceof Wall || e instanceof Boulder)) {
				return false;
			} else if (x == e.getX() && y == e.getY() && e instanceof Door) {
				if (((Door) e).canGetThough() == false) {
					if (((Door) e).canOpen(this) == false) {
						return false;
					} else {
						try {
							dungeon.changeImage(getNum(), new Image("player.png"));
						} catch (Exception ex) {
							System.out.println("Front End: New image not initialized");
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Check if square (x,y) is blocked for boulder to be in
	 * 
	 * @param x for x axis
	 * @param y for y axis
	 * @return true if (x,y) can be access by boulder, false if (x,y) cannot be access by boulder
	 */
	public boolean boulderCanMove(int x, int y) {
		List<Entity> el = dungeon.getEntities();
		for (Entity e : el) {
			if ((e instanceof Wall || e instanceof Boulder || e instanceof Door || e instanceof Enemy
					|| e instanceof Exit) && x == e.getX() && y == e.getY()) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * player entity try to move up
	 */
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
	
	/**
	 * player entity try to move down
	 */
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

	/**
	 * player entity try to move left
	 */
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
	
	/**
	 * player entity try to move right
	 */
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
	
	/**
	 * each time player move a step, check if killed by enemy/if kills enemy
	 * check bomb explose, invincibility and if finished game
	 */
	public void action() {
		kill();
		countdown();
		if (hasInvincibilityMoves > 0)
			this.setHasInvincibilityMoves(hasInvincibilityMoves - 1);
		if (dungeon.checkCompletion()) {
			System.out.print("--- you win ---");
			System.exit(0);
		}
	}

	/**
	 * player drop bomb on the groud, where player is at
	 * 
	 */
	public void drop() {
		if (hasUnlitBombs > 0) {
			Drop drop = new Drop(getX(), getY(), -1);
			new Image("player_dead.png");
			dungeon.addEntity(drop);
			setHasUnlitBombs(hasUnlitBombs - 1);
		}
	}

	/**
	 * when a bomb is lighted, countdown for 3 moves while changing photo
	 * after 3 move, bomb destory everything in 4 nearby squares 
	 */
	public void countdown() {
		List<Entity> el = dungeon.getEntities();
		if (el == null)
			return;
		el.forEach(e -> {
			if (e instanceof Drop) {
				if (((Drop) e).getCountdown() > 0) {
					((Drop) e).setCountdown(((Drop) e).getCountdown() - 1);
					if(((Drop) e).getCountdown() == 2) {
						this.dungeon.addImage(new Image("bomb_lit_1.png"), e.getX(), e.getY());
					}else if(((Drop) e).getCountdown() == 1) {
						this.dungeon.addImage(new Image("ground.png"), e.getX(), e.getY());
						this.dungeon.addImage(new Image("bomb_lit_2.png"), e.getX(), e.getY());
					}else if(((Drop) e).getCountdown() == 0) {
						this.dungeon.addImage(new Image("ground.png"), e.getX(), e.getY());
						this.dungeon.addImage(new Image("bomb_lit_3.png"), e.getX(), e.getY());
					}
					
				}
				else {
					this.dungeon.addImage(new Image("ground.png"), e.getX(), e.getY());
					this.dungeon.addImage(new Image("bomb_lit_4.png"), e.getX(), e.getY());
					explode(e.getX(), e.getY());
					explode(e.getX() - 1, e.getY());
					explode(e.getX() + 1, e.getY());
					explode(e.getX(), e.getY() - 1);
					explode(e.getX(), e.getY() + 1);
					e.x().set(dungeon.getWidth());
				}
			}
		});
	}

	/**
	 * when bomb explode, distroy player, enemy, boulder in nearby 4 squares
	 * 
	 */
	public void explode(int x, int y) {
		List<Entity> el = dungeon.getEntities();
		if (el == null)
			return;
		el.forEach(e -> {
			if (e.getX() == x && e.getY() == y && e instanceof Player) {
				System.out.println("--- you lose ---");
				x().set(dungeon.getWidth());
				System.exit(0);
			} else if (e.getX() == x && e.getY() == y && (e instanceof Boulder)) {
				e.x().set(dungeon.getWidth());
			} else if (e.getX() == x && e.getY() == y && (e instanceof Enemy)) {
				dungeon.addImage(new Image("enemy_dead.png"), e.getX(), e.getY());
				e.x().set(dungeon.getWidth());
				dungeon.getGoal().setEnemyNumber(dungeon.getGoal().getEnemyNumber() + 1);
			}
		});
	}

	/**
	 * player collect collectable entities in the same square
	 * 
	 */
	public void collect() {
		Entity e = dungeon.findEntity(getX(), getY());
		if (!(e instanceof Player) && e.isCollectable() == true) {
			if (e instanceof Sword) {
				e.x().set(dungeon.getWidth());
				dungeon.removeEntity(e);
				setHasSwordHits(5);
			} else if (e instanceof Invincibility) {
				e.x().set(dungeon.getWidth());
				dungeon.removeEntity(e);
				setHasInvincibilityMoves(15);
			} else if (e instanceof Key && hasKeyID == 0) {
				e.x().set(dungeon.getWidth());
				dungeon.removeEntity(e);
				setHasKeyID(((Key) e).id);
				try {
					dungeon.changeImage(getNum(), new Image("-k-.png"));
				} catch (Exception ex) {
					System.out.println("Front End: New image not initialized");
				}
			} else if (e instanceof Treasure) {
				e.x().set(dungeon.getWidth());
				dungeon.removeEntity(e);
				dungeon.getGoal().setTreasureNumber(dungeon.getGoal().getTreasureNumber() + 1);
				System.out.println(dungeon.getGoal().getTreasureNumber());
			} else if (e instanceof Bomb) {
				e.x().set(dungeon.getWidth());
				dungeon.removeEntity(e);
				setHasUnlitBombs(hasUnlitBombs + 1);
			}
		}
		if (dungeon.checkCompletion()) {
			System.out.print("--- you win ---");
			System.exit(0);
		}
	}
	
	/**
	 * when reach exit entity and pressed "RETURN", print player win or loose
	 * 
	 */
	public void reach() {
		Entity e = dungeon.findEntity(getX(), getY());
		if (e instanceof Exit) {
			dungeon.getGoal().setReachExit(true);
			if (dungeon.checkCompletion()) {
				System.out.print("--- you win ---");
			} else {
				System.out.print("--- you lose ---");
			}
			System.exit(0);
		}
	}

	/**
	 * When player is in the same square as enemy
	 * if player is armed, enemy get killed 
	 * if player is unarmed, player get killed
	 */
	public void kill() {
		List<Entity> el = dungeon.getEntities();
		if (el == null)
			return;
		el.forEach(e -> {
			if (e instanceof Enemy && getX() == e.getX() && getY() == e.getY()) {
				if (this.isUnarmedPlayer()) {
					System.out.println("--- you lose ---");
					dungeon.addImage(new Image("player_dead.png"), getX(), getY());
					x().set(dungeon.getWidth());
				} else {
					System.out.println("--- you killed the enemy ---");
					try {
						dungeon.addImage(new Image("enemy_dead.png"), getX(), getY());
					} catch (Exception ex) {
						System.out.println("Front End: New image not initialized");
					}
					e.x().set(dungeon.getWidth());
					dungeon.getGoal().setEnemyNumber(dungeon.getGoal().getEnemyNumber() + 1);
				}
			}
		});
	}

	/**
	 * For player, check if player has weapon
	 * 
	 * @return true if player has weapon, false if player don't have weapon
	 */
	public boolean isUnarmedPlayer() {
		if (hasSwordHits <= 0 && hasInvincibilityMoves <= 0)
			return true;
		else if (hasInvincibilityMoves <= 0)
			this.setHasSwordHits(hasSwordHits - 1);
		return false;
	}

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
