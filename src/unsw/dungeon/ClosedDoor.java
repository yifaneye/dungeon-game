package unsw.dungeon;

public class ClosedDoor implements State{
	
	Door door;
	/**
	 * 
	 */
	public ClosedDoor(Door door) {
		this.door = door;
	}
	/**
	 * 
	 */
	@Override
	public boolean getThough() {
		return false;
	}
	/**
	 * 
	 */
	@Override
	public boolean open(Player player, Door door) {
		if (player.hasKeyID == door.id) {
			//door.x().set(player.getDungeon().getWidth());
			System.out.println("--- you opened the door ---");
			player.setHasKeyID(0);
			door.setState(door.getOpenedDoor());
			return true;
		} else {
			return false;
		}
	}
	
}
