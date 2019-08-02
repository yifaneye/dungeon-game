package unsw.dungeon;

import java.util.List;

public class Enemy extends Entity {

	public Dungeon dungeon;
	
	private EnemyObserver observer = new EnemyObserver();
	private EnemyMove enemyMove = new EnemyMove(this);

	public Enemy(Dungeon dungeon, int x, int y, Subject s) {
		super(x, y);
		s.attach(observer);
		this.dungeon = dungeon;
	}
	
	public EnemyMove getEnemyMove() {
		return enemyMove;
	}
	
	public EnemyObserver getObserver() {
		return observer;
	}

	public void kill() {
		List<Entity> el = dungeon.getEntities();
		if (el == null) return;
		el.forEach(e->{
			if (getX() == e.getX() && getY() == e.getY() && e instanceof Player) {
				if (((Player) e).isUnarmedPlayer()) {
					e.x().set(getX() + dungeon.getWidth());
					dungeon.removeEntity(e);
					System.out.println("--- you lose ---");
					System.exit(0);
				} else {
					x().set(getX() + dungeon.getWidth());
					dungeon.removeEntity(this);
					System.out.println("--- you killed the enemy ---");
				}
			}
		});
	}

}
