package unsw.dungeon;

public class Drop extends Entity {
	
	private int countdown;

	public Drop(int x, int y) {
        super(x, y);
        this.countdown = 3;
    }
    
    public int getCountdown() {
		return countdown;
	}

	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}
}
