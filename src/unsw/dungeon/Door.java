package unsw.dungeon;

import javafx.scene.image.Image;

public class Door extends Entity {
	
	public int id;
	private Dungeon dungeon;
	State closedDoor = new ClosedDoor(this);
	State openedDoor = new OpenedDoor(this);
	
	State state = closedDoor;
	
    public Door(int x, int y, int id, Dungeon dungeon, int num) {
        super(x, y, num);
        this.id = id;
        this.closedDoor = new ClosedDoor(this);
    	this.openedDoor = new OpenedDoor(this);
    	this.dungeon = dungeon;
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
		if(state == openedDoor) {
			try {
				dungeon.changeImage(getNum(), new Image("open_door.png"));
			} catch (Exception ex) {
				System.out.println("Front End: New image not initialized");
			}
		}
		this.state = state;
	}

	public State getClosedDoor() {
		return closedDoor;
	}

	public State getOpenedDoor() {
		return openedDoor;
	}
    
}
