package unsw.dungeon;

public class Door extends Entity {
	
	public int id;
	State closedDoor = new ClosedDoor(this);
	State openedDoor = new OpenedDoor(this);
	
	State state = closedDoor;
	
    public Door(int x, int y, int id) {
        super(x, y);
        this.id = id;
        this.closedDoor = new ClosedDoor(this);
    	this.openedDoor = new OpenedDoor(this);
    }
    
    public boolean canOpen(Player player) {
		return (boolean) state.open(player, this);
	}
    
    public boolean canGetThough() {
		return state.getThough();
	}
    
    public boolean isReachable() {
    	return true;
    }

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public State getClosedDoor() {
		return closedDoor;
	}

	public State getOpenedDoor() {
		return openedDoor;
	}
    
}
