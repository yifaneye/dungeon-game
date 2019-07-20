package unsw.dungeon;

import java.util.ArrayList;

public class PlayerSubject implements Subject{
	
	ArrayList<Observer> listObservers = new ArrayList<Observer>();
	int x = 0;
	int y = 0;
	boolean hasInvincibility = false;
	
	@Override
	public void attach(Observer o) {
		// TODO Auto-generated method stub
		listObservers.add(o);
	}

	@Override
	public void detach(Observer o) {
		// TODO Auto-generated method stub
		listObservers.remove(o);
	}

	@Override
	public void notifyobservers() {
		// TODO Auto-generated method stub
		for (Observer ob : listObservers) {
			ob.update(this);
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
		notifyobservers();
	}
	
	public boolean isHasInvincibility() {
		return hasInvincibility;
	}

	public void setHasInvincibility(boolean hasInvincibility) {
		this.hasInvincibility = hasInvincibility;
		notifyobservers();
	}
	
}
