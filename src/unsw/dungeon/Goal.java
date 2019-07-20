package unsw.dungeon;

public class Goal {
	
	private boolean exitGoal;
    private boolean enemiesGoal;
    private boolean bouldersGoal;
    private boolean treasureGoal;
    
    public boolean reachExit;
    public int totalSwitch;
    public int totalEnemies;
    public int totalTreasure;
    
	public Goal() {
		this.exitGoal = false;
		this.enemiesGoal = false;
		this.bouldersGoal = false;
		this.treasureGoal = false;		
		this.reachExit = false;
		this.totalSwitch = 0;
		this.totalEnemies = 0;
		this.totalTreasure = 0;
	}
	
	public int getTotalSwitch() {
		return totalSwitch;
	}

	public void setTotalSwitch(int totalSwitch) {
		this.totalSwitch = totalSwitch;
	}

	public int getTotalEnemies() {
		return totalEnemies;
	}

	public void setTotalEnemies(int totalEnemies) {
		this.totalEnemies = totalEnemies;
	}

	public int getTotalTreasure() {
		return totalTreasure;
	}

	public void setTotalTreasure(int totalTreasure) {
		this.totalTreasure = totalTreasure;
	}

	public boolean checkGoal() {
		
		if (exitGoal && enemiesGoal && bouldersGoal && treasureGoal) return true;
		else return false;
		
	}
	
}
