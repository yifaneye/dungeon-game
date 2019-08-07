package unsw.dungeon;

import java.util.List;

import javafx.scene.image.Image;

public class Enemy extends Entity {

	public Dungeon dungeon;

	private EnemyObserver observer = new EnemyObserver();
	private EnemyMove enemyMove = new EnemyMove(this);
	
	/**
	 * Create a enemy positioned in square (x,y)
	 * 
	 * @param x for x axis
	 * @param y for y axis
	 * @param dungeon add dungeon so that player can access dungeon data
	 * @param num is the index of entity
	 */
	public Enemy(Dungeon dungeon, int x, int y, Subject s, int num) {
		super(x, y, num);
		s.attach(observer);
		this.dungeon = dungeon;
	}
	
    //getter & setter
	public EnemyMove getEnemyMove() {
		return enemyMove;
	}

	public EnemyObserver getObserver() {
		return observer;
	}

	/**
	 * When ememy is in the same square as player
	 * if player is armed, enemy get killed 
	 * if player is unarmed, player get killed
	 */
	public void kill() {
		List<Entity> el = dungeon.getEntities();
		if (el == null)
			return;
		el.forEach(e->{
			if (getX() == e.getX() && getY() == e.getY() && e instanceof Player) {
				if (((Player) e).isUnarmedPlayer()) {
					System.out.println("--- you lose ---");
					try {
						dungeon.addImage((new Image("player_dead.png")), getX(), getY());
					} catch (Exception ex) {
						System.out.println("Front End: New image not initialized");
					}
					e.x().set(dungeon.getWidth());
				} else {
					System.out.println("--- you killed the enemy ---");
					dungeon.addImage(new Image("enemy_dead.png"), getX(), getY());
					x().set(dungeon.getWidth());
					dungeon.getGoal().setEnemyNumber(dungeon.getGoal().getEnemyNumber() + 1);
				}
			}
		});
	}

}
