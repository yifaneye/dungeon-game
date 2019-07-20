package unsw.dungeon;

public class Enemy extends Entity {
	
	private EnemyObserver observer= new EnemyObserver();
    private EnemyMove enemyMove = new EnemyMove(this);

	public Enemy(int x, int y, Subject sub) {
        super(x, y);
        sub.attach(observer);
    }
    
	public EnemyObserver getObserver() {
		return observer;
	}

	public EnemyMove getEnemyMove() {
		return enemyMove;
	}
	
    /*public void enemyMove() {
    	int diff_x = getX() - observer.x;
    	int diff_y = getY() - observer.y;
    	
    	if (Math.abs(diff_x) > Math.abs(diff_y)) {//move x
    		if(diff_x > 0) {
    			x().set(getX()-1);
    		}else {
    			x().set(getX()+1);
    		}
    	} else {//move y
    		if(diff_y > 0) {
    			y().set(getY()-1);
    		}else {
    			y().set(getY()+1);
    		}
    	}
    }*/
}
