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
					System.out.println("--- you lose ---");
					e.x().set(getX() + dungeon.getWidth());
					System.exit(0);
				} else {
					System.out.println("--- you killed the enemy ---");
					x().set(getX() + dungeon.getWidth());
					dungeon.getGoal().setEnemyNumber(dungeon.getGoal().getEnemyNumber() + 1);
				}
			}
		});
	}

}
