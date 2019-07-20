package unsw.dungeon;

public class ClosedDoor implements State{
	
	Door door;
	
	public ClosedDoor(Door door) {
		this.door = door;
	}
	
	@Override
	public boolean getThough() {
		return false;
	}

	@Override
	public boolean open(Player player, Door door) {
		if (player.getHasKeyID() == door.id) {
			door.x().set(door.getX() + player.getDungeon().getWidth());
			player.getDungeon().removeEntity(door);
			player.hasKeyID = -1;
			door.setState(door.getOpenedDoor());
			return true;
		} else {
			return false;
		}
	}
	
}
