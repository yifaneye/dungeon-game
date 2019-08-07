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
		List<Entity> el = dungeon.getEntities();
		for (Entity e : el) {
			if (e instanceof Switch) {
				if (((Switch) e).isSwitchOpen(dungeon) == false) {
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
		if (enemyNumber == totalEnemies) {
			return true;
		} else {
			return false;
		}
	}

	public boolean treasureGoal() {
		if (treasureNumber == totalTreasure) {
			return true;
		} else {
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
		boolean ret = true;
		Composite goalCond = DungeonLoader.goalCond;
		for (Component g : goalCond.children) {
			if (g instanceof Composite) {
				if (((Composite) g).getName() == "OR") {
					ret = false;
					if (g.nameString().contains("exit")) {
						if (exitGoal())
							ret = true;
					}
					if (g.nameString().contains("enemies")) {
						if (enemiesGoal())
							ret = true;
					}
					if (g.nameString().contains("boulders")) {
						if (switchGoal(dungeon))
							ret = true;
					}
					if (g.nameString().contains("treasure")) {
						if (treasureGoal())
							ret = true;
					}
				} else if (((Composite) g).getName() == "AND") {
					ret = true;
					if (g.nameString().contains("exit")) {
						if (!exitGoal())
							ret = false;
					}
					if (g.nameString().contains("enemies")) {
						if (!enemiesGoal())
							ret = false;
					}
					if (g.nameString().contains("treasure")) {
						if (!treasureGoal())
							ret = false;
					}
					if (g.nameString().contains("boulders")) {
						if (!switchGoal(dungeon))
							ret = false;
					}
				}
			}
			if (g instanceof Leaf) {
				if (((Leaf) g).getName() == "exit") {
					if (!exitGoal())
						return false;
				}
				if (((Leaf) g).getName() == "enemies") {
					if (!enemiesGoal())
						return false;
				}
				if (((Leaf) g).getName() == "boulders") {
					if (!switchGoal(dungeon))
						return false;
				}
				if (((Leaf) g).getName() == "treasure") {
					if (!treasureGoal())
						return false;
				}
			}
			if (g instanceof Composite) {
				if (((Composite) g).getName() == "OR" && ret == true) {
					return true;
				} else if (((Composite) g).getName() == "AND" && ret == false) {
					return false;
				}
			}
		}
		return ret;
	}
}
