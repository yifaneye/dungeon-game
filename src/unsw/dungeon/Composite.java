package unsw.dungeon;

import java.util.ArrayList;

public class Composite implements Component {

	private String name; 
	 
	ArrayList<Component> children = new ArrayList<Component>();
	/**
	 * 
	 */
	public Composite(String name) {
		super();
		this.name = name;
	}
	/**
	 * 
	 */
	@Override
	public String nameString() {
		String answer = "[" + this.getName() + " <"; 
		for(Component c : children) {
			answer = answer + " " + c.nameString();
		}	
		answer = answer + " >]";
		return answer;
	}
	/**
	 * 
	 */
	public boolean add(Component child) {
		children.add(child);
		return true;
	}
	/**
	 * 
	 */
	public boolean remove(Component child) {
		children.remove(child);
		return true;
	}

	// Getters and Setters below .... 
	/**
	 * 
	 */
	public String getName() {
		return name;
	}
	/**
	 * 
	 */
	public void setName(String name) {
		this.name = name;
	}
	
}
