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

    public Dungeon(int width, int height) {
        this.width = width;
        this.height = height;
        this.entities = new ArrayList<>();
        this.player = null;
        this.playersubject = new PlayerSubject();
        
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

	public PlayerSubject getPlayersubject() {
		return playersubject;
	}

	public List<Entity> getEntities() {
		return entities;
	}
	
    public void removeEntity(Entity entity) {
        entities.remove(entity);
    }
    
    public void updatePlayerSubject() {
    	System.out.println("Update\n");
    	playersubject.setXY(player.getX(), player.getY());
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

}
