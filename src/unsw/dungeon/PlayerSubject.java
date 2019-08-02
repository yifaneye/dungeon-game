package unsw.dungeon;

import java.util.ArrayList;

public class PlayerSubject implements Subject {
	
	private ArrayList<Observer> listObservers = new ArrayList<Observer>();
	private int x = 0;
	private int y = 0;
	private int hasInvincibilityMoves = 0;

	@Override
	public void attach(Observer o) {
		listObservers.add(o);
	}

	@Override
	public void detach(Observer o) {
		listObservers.remove(o);
	}

	@Override
	public void notifyObservers() {
		for (Observer o : listObservers) {
			o.update(this);
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setXY(int x, int y) {
		this.x = x;
		this.y = y;
		notifyObservers();
	}

	public int gethasInvincibilityMoves() {
		return hasInvincibilityMoves;
	}

	public void setHasInvincibilityMoves(int hasInvincibilityMoves) {
		this.hasInvincibilityMoves = hasInvincibilityMoves;
		notifyObservers();
	}

}
