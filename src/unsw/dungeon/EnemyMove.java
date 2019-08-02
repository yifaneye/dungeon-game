package unsw.dungeon;

import java.util.List;
import java.util.TimerTask;

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

	public boolean enemyCanMove(int x, int y) {
		List<Entity> el = enemy.dungeon.getEntities();
		for (Entity e : el) {
			if ((e instanceof Wall || e instanceof Boulder) && x == e.getX() && y == e.getY()) {
				return false;
			}
		}
		return true;
	}

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
			}
		}
	}

	public void runTowards() {
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
			}
		}
	}

}
