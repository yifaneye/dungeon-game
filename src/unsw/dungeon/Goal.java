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
		this.exitGoal = true;
		this.enemiesGoal = true;
		this.bouldersGoal = true;
		this.treasureGoal = true;		
		this.reachExit = false;
		this.totalSwitch = 0;
		this.totalEnemies = 0;
		this.totalTreasure = 0;
	}
	
	public void setExitGoal(boolean exitGoal) {
		this.exitGoal = exitGoal;
	}

	public void setEnemiesGoal(boolean enemiesGoal) {
		this.enemiesGoal = enemiesGoal;
	}

	public void setBouldersGoal(boolean bouldersGoal) {
		this.bouldersGoal = bouldersGoal;
	}

	public void setTreasureGoal(boolean treasureGoal) {
		this.treasureGoal = treasureGoal;
	}

	public boolean checkGoal() {
		
		if (exitGoal && enemiesGoal && bouldersGoal && treasureGoal) return true;
		else return false;
		
	}
	
}
