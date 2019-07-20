package unsw.dungeon;

public class Enemy extends Entity {
	
	public Dungeon dungeon;
	private EnemyObserver observer= new EnemyObserver();
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
	
    
}
