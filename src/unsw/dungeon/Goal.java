package unsw.dungeon;

import java.util.List;

public class Goal {
	
	private boolean reachExit;
    private int enemyNumber;
    private int treasureNumber;
    private int totalSwitch;
    private int totalEnemies;
    private int totalTreasure;
    private Dungeon dungeon;
    
	public Goal(Dungeon dungeon) {
		this.reachExit = false;
		this.enemyNumber = 0;
		this.treasureNumber = 0;
		this.totalSwitch = 0;
		this.totalEnemies = 0;
		this.totalTreasure = 0;
		this.dungeon = dungeon;
	}
	
	public boolean switchGoal(Dungeon dungeon) {
		Switch switc = null;
		List<Entity> el = dungeon.getEntities();
		for (Entity e : el) {
			if (e instanceof Switch) {
				if(!e.switchOpen(dungeon)) {
					return false;
				}
			}
		}
		return true;
		
	}
	
	public boolean exitGoal() {
		return reachExit;
	}
	
	public boolean enemiesGoal() {
		if(enemyNumber == totalEnemies) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean treasureGoal() {
		if(treasureNumber == totalTreasure) {
			return true;
		}else {
			return false;
		}
	}
	
	public boolean isReachExit() {
		return reachExit;
	}

	public void setReachExit(boolean reachExit) {
		this.reachExit = reachExit;
	}

	public int getEnemyNumber() {
		return enemyNumber;
	}

	public void setEnemyNumber(int enemyNumber) {
		this.enemyNumber = enemyNumber;
	}

	public int getTreasureNumber() {
		return treasureNumber;
	}

	public void setTreasureNumber(int treasureNumber) {
		this.treasureNumber = treasureNumber;
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

	public boolean checkGoals() {
		
		if (exitGoal() && enemiesGoal()&& switchGoal(dungeon) && treasureGoal()) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
}
