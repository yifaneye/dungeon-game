package unsw.dungeon;

import java.util.List;

public class Enemy extends Entity {

	public Dungeon dungeon;
	
	private EnemyObserver observer = new EnemyObserver();
	private EnemyMove enemyMove = new EnemyMove(this);

	public Enemy(Dungeon dungeon, int x, int y, Subject sub) {
		super(x, y);
		sub.attach(observer);
		this.dungeon = dungeon;
	}

	public EnemyObserver getObserver() {
		return observer;
	}

	public EnemyMove getEnemyMove() {
		return enemyMove;
	}

	public void kill() {
		List<Entity> el = dungeon.getEntities();
		for (Entity e : el) {
			if (e instanceof Player && getX() == e.getX() && getY() == e.getY()) {
				if (((Player) e).isUnarmedPlayer()) {
					System.out.println("--- you lose ---");
					System.exit(1);
				} else {
					x().set(0);
					dungeon.removeEntity(this);
					System.out.println("--- you killed the enemy ---");
				}
			}
		}
	}

}
