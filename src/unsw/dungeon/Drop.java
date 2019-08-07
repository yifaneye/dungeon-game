package unsw.dungeon;

public class Drop extends Entity {
	
	private int countdown;
	/**
	 * 
	 */
	public Drop(int x, int y, int num) {
        super(x, y, num);
        this.countdown = 3;
    }
	/**
	 * 
	 */
    public int getCountdown() {
		return countdown;
	}
    /**
	 * 
	 */
	public void setCountdown(int countdown) {
		this.countdown = countdown;
	}
}
