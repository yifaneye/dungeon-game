package unsw.dungeon;

public class OpenedDoor implements State{
	Door door;
	
	public OpenedDoor(Door door) {
		this.door = door;
	}
	
	@Override
	public boolean getThough() {
		return true;
	}

	@Override
	public boolean open(Player player, Door door) {
		return false;
	}
	
	
}
