package unsw.dungeon;

import java.util.TimerTask;

public class EnemyMove extends TimerTask{

	private Enemy enemy;
	
	public EnemyMove(Enemy enemy) {
		this.enemy = enemy;
	}
	
	// 0,0 stop moving
	// blocked by wall, and move other way
	
	@Override
	public void run() {
		int diff_x = enemy.getX() - enemy.getObserver().x;
		int diff_y = enemy.getY() - enemy.getObserver().y;
		
		if (Math.abs(diff_x) > Math.abs(diff_y)) {//move x
			if(diff_x > 0) {
				enemy.x().set(enemy.getX()-1);
			}else {
				enemy.x().set(enemy.getX()+1);
			}
		} else {//move y
			if(diff_y > 0) {
				enemy.y().set(enemy.getY()-1);
			}else {
				enemy.y().set(enemy.getY()+1);
			}
		}
		
	}
	
}
