package unsw.dungeon;

public class Leaf implements Component {

	private String name; 
	
	
	public Leaf(String name) {
		super();
		this.name = name;
	}

	@Override
	public String nameString() {
		return this.name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
