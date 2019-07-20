package unsw.dungeon;

public interface State {

	public boolean open(Player player, Door door);

	public boolean getThough();

}
