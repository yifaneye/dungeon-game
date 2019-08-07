package unsw.dungeon;

import java.util.*;

public class EnemyMove extends TimerTask {

	private Enemy enemy;
	
	
	public EnemyMove(Enemy enemy) {
		this.enemy = enemy;
	}

	public void run() {
		enemy.kill();
		if (enemy.getObserver().hasInvincibilityMoves > 0)
			runAway();
		else
			runTowards();
	}
	
	/**
	 * enemy will be blocked by wall and boulder
	 * 
	 * @param x for x axis of aquare want to go in
	 * @param y for y axis
	 * @return true if enemy can move to (x,y), false if enemy will be blocked 
	 */
	public boolean enemyCanMove(int x, int y) {
		List<Entity> el = enemy.dungeon.getEntities();
		for (Entity e : el) {
			if ((e instanceof Wall || e instanceof Boulder) && x == e.getX() && y == e.getY()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * enemy constantly move away from player
	 * 
	 */
	public void runAway() {
		int diff_x = enemy.getX() - enemy.getObserver().x;
		int diff_y = enemy.getY() - enemy.getObserver().y;
		if (Math.abs(diff_x) > Math.abs(diff_y)) {
			if (diff_x > 0 && enemyCanMove(enemy.getX() + 1, enemy.getY())) {
				enemy.x().set(enemy.getX() + 1);
			} else if (diff_x < 0 && enemyCanMove(enemy.getX() - 1, enemy.getY())) {
				enemy.x().set(enemy.getX() - 1);
			} else if (diff_y > 0 && enemyCanMove(enemy.getX(), enemy.getY() + 1)) {
				enemy.y().set(enemy.getY() + 1);
			} else if (diff_y < 0 && enemyCanMove(enemy.getX(), enemy.getY() - 1)) {
				enemy.y().set(enemy.getY() - 1);
			} else if (diff_y == 0 && enemyCanMove(enemy.getX(), enemy.getY() - 1)
					&& enemyCanMove(enemy.getX(), enemy.getY() + 1)) {
				Random r = new Random();
				int move = r.nextBoolean() ? 1 : -1;
				enemy.y().set(enemy.getY() + move);
			} else if (diff_y == 0 && enemyCanMove(enemy.getX(), enemy.getY() + 1)) {
				enemy.y().set(enemy.getY() + 1);
			} else if (diff_y == 0 && enemyCanMove(enemy.getX(), enemy.getY() - 1)) {
				enemy.y().set(enemy.getY() - 1);
			}
		} else {
			if (diff_y > 0 && enemyCanMove(enemy.getX(), enemy.getY() + 1)) {
				enemy.y().set(enemy.getY() + 1);
			} else if (diff_y < 0 && enemyCanMove(enemy.getX(), enemy.getY() - 1)) {
				enemy.y().set(enemy.getY() - 1);
			} else if (diff_x > 0 && enemyCanMove(enemy.getX() + 1, enemy.getY())) {
				enemy.x().set(enemy.getX() + 1);
			} else if (diff_x < 0 && enemyCanMove(enemy.getX() - 1, enemy.getY())) {
				enemy.x().set(enemy.getX() - 1);
			} else if (diff_x == 0 && enemyCanMove(enemy.getX() - 1, enemy.getY())
					&& enemyCanMove(enemy.getX() + 1, enemy.getY())) {
				Random r = new Random();
				int move = r.nextBoolean() ? 1 : -1;
				enemy.x().set(enemy.getX() + move);
			} else if (diff_x == 0 && enemyCanMove(enemy.getX() + 1, enemy.getY())) {
				enemy.x().set(enemy.getX() + 1);
			} else if (diff_x == 0 && enemyCanMove(enemy.getX() - 1, enemy.getY())) {
				enemy.x().set(enemy.getX() - 1);
			}
		}
	}
	
	
	
	/**
	 * enemy constantly move towards from player
	 * 
	 */
	
	public void runTowards() {
		if (enemy.getObserver().x == enemy.dungeon.getWidth()) {
			cancel();
			return;
		}
		int diff_x = enemy.getX() - enemy.getObserver().x;
		int diff_y = enemy.getY() - enemy.getObserver().y;
		if (Math.abs(diff_x) > Math.abs(diff_y)) {
			if (diff_x > 0 && enemyCanMove(enemy.getX() - 1, enemy.getY())) {
				enemy.x().set(enemy.getX() - 1);
			} else if (diff_x < 0 && enemyCanMove(enemy.getX() + 1, enemy.getY())) {
				enemy.x().set(enemy.getX() + 1);
			} else if (diff_y > 0 && enemyCanMove(enemy.getX(), enemy.getY() - 1)) {
				enemy.y().set(enemy.getY() - 1);
			} else if (diff_y < 0 && enemyCanMove(enemy.getX(), enemy.getY() + 1)) {
				enemy.y().set(enemy.getY() + 1);
			} else if (diff_y == 0 && enemyCanMove(enemy.getX(), enemy.getY() - 1)
					&& enemyCanMove(enemy.getX(), enemy.getY() + 1)) {
				Random r = new Random();
				int move = r.nextBoolean() ? 1 : -1;
				enemy.y().set(enemy.getY() + move);
			} else if (diff_y == 0 && enemyCanMove(enemy.getX(), enemy.getY() - 1)) {
				enemy.y().set(enemy.getY() - 1);
			} else if (diff_y == 0 && enemyCanMove(enemy.getX(), enemy.getY() + 1)) {
				enemy.y().set(enemy.getY() + 1);
			}
		} else {
			if (diff_y > 0 && enemyCanMove(enemy.getX(), enemy.getY() - 1)) {
				enemy.y().set(enemy.getY() - 1);
			} else if (diff_y < 0 && enemyCanMove(enemy.getX(), enemy.getY() + 1)) {
				enemy.y().set(enemy.getY() + 1);
			} else if (diff_x > 0 && enemyCanMove(enemy.getX() - 1, enemy.getY())) {
				enemy.x().set(enemy.getX() - 1);
			} else if (diff_x < 0 && enemyCanMove(enemy.getX() + 1, enemy.getY())) {
				enemy.x().set(enemy.getX() + 1);
			} else if (diff_x == 0 && enemyCanMove(enemy.getX() - 1, enemy.getY())
					&& enemyCanMove(enemy.getX() + 1, enemy.getY())) {
				Random r = new Random();
				int move = r.nextBoolean() ? 1 : -1;
				enemy.x().set(enemy.getX() + move);
			} else if (diff_x == 0 && enemyCanMove(enemy.getX() - 1, enemy.getY())) {
				enemy.x().set(enemy.getX() - 1);
			} else if (diff_x == 0 && enemyCanMove(enemy.getX() + 1, enemy.getY())) {
				enemy.x().set(enemy.getX() + 1);
			}
		}
	}

}
