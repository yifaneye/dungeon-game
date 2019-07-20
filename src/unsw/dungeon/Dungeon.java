/**
 *
 */
package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

/**
 * A dungeon in the interactive dungeon player.
 *
 * A dungeon can contain many entities, each occupy a square. More than one
 * entity can occupy the same square.
 *
 * @author Robert Clifton-Everest
 *
 */
public class Dungeon {

    private int width, height;
    private List<Entity> entities;
    private Player player;
    private PlayerSubject playersubject;
    private Goal goal;

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.playersubject = new PlayerSubject();
        this.goal = new Goal(this);
    }
    
    public Entity findEntity(int x, int y) {
		Entity en = null;
    	for (Entity e : entities) {
			if (x == e.getX() && y == e.getY()) {
				en = e;
			}
		}
    	return en;
    }
    
    public void updatePlayerSubject() {
    	System.out.println("Update\n");
    	playersubject.setXY(player.getX(), player.getY());
    }
    
    //getter&&setter 
    public int getTreasureNumber() {
    	return goal.getTreasureNumber();
    }

    public void setTreasureNumber (int treasureNumber) {
        this.goal.setTreasureNumber(treasureNumber);
    }
    
    public int getTotalSwitch() {
    	return goal.getTotalSwitch();
    }

    public void setTotalSwitch(int totalSwitch) {
        this.goal.setTotalSwitch(totalSwitch);
    }
    
    public int getTotalEnemies() {
    	return goal.getTotalEnemies();
    }

    public void setTotalEnemies(int totalEnemies) {
        this.goal.setTotalEnemies(totalEnemies);
    }
    
    public int getTotalTreasure() {
    	return goal.getTotalTreasure();
    }

    public void setTotalTreasure(int totalTreasure) {
        this.goal.setTotalTreasure(totalTreasure);
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

	public Goal getGoal() {
		return goal;
	}

	public List<Entity> getEntities() {
		return entities;
	}
	
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
    
    public PlayerSubject getPlayersubject() {
		return playersubject;
	}

}
