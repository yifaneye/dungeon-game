package unsw.dungeon;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 * An entity in the dungeon.
 * 
 * @author Robert Clifton-Everest
 *
 */
public class Entity {

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	// IntegerProperty is used so that changes to the entities position can be
	// externally observed.
	private IntegerProperty x, y;
	private int num;

	/**
	 * Create an entity positioned in square (x,y)
	 * 
	 * @param x
	 * @param y
	 */
	public Entity(int x, int y, int num) {
		this.x = new SimpleIntegerProperty(x);
		this.y = new SimpleIntegerProperty(y);
		this.num = num;
	}

	public IntegerProperty x() {
		return x;
	}

	public IntegerProperty y() {
		return y;
	}

	public int getY() {
		return y().get();
	}

	public int getX() {
		return x().get();
	}
	/**
	 * For player, check if player has weapon
	 * 
	 * @return true if player has weapon, false if player don't have weapon
	 */
	public boolean isUnarmedPlayer() {
		return false;
	}
	/**
	 * Check if entity can be collected by player
	 * 
	 * @return true if entity can be collected, false if entity cannot be collected
	 */
	public boolean isCollectable() {
		return false;
	}
	/**
	 * Check if exit is able to reach
	 * 
	 * @return true if exit can be reach, false if exit cannot be reached
	 */
	public boolean isReachable() {
		return false;
	}
	/**
	 * Check if this switch is opened by bounder
	 * 
	 * @return true if switch is opened, false if switch is closed
	 */
	public boolean isSwitchOpen(Dungeon dungeon) {
		return false;
	}
	
}
