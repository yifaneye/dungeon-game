package unsw.dungeon;

public class EnemyObserver implements Observer {

	public int x = 0;
	public int y = 0;
	public int hasInvincibilityMoves = 0;

	@Override
	public void update(Subject obj) {
		if (obj instanceof PlayerSubject) {
			x = ((PlayerSubject) obj).getX();
			y = ((PlayerSubject) obj).getY();
			hasInvincibilityMoves = ((PlayerSubject) obj).gethasInvincibilityMoves();
		}
	}

}
