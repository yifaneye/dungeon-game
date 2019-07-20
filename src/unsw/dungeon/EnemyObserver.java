package unsw.dungeon;

public class EnemyObserver implements Observer{
	
	int x = 0;
	int y = 0;
	boolean hasInvincibility = false;
	
	@Override
	public void update(Subject obj) {
		if(obj instanceof PlayerSubject) {
			x = ((PlayerSubject) obj).getX();
			y = ((PlayerSubject) obj).getY();
			hasInvincibility = ((PlayerSubject) obj).isHasInvincibility();
			display();
		}
	}
	
	public void display() {
		System.out.printf("player move to x = %d y = %d\n", x, y);
		System.out.printf("has potion = " + this.hasInvincibility+"\n\n");
	}
	
	
}
