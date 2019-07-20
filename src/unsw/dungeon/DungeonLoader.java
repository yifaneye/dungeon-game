package unsw.dungeon;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 * Loads a dungeon from a .json file.
 *
 * By extending this class, a subclass can hook into entity creation. This is
 * useful for creating UI elements with corresponding entities.
 *
 * @author Robert Clifton-Everest
 *
 */
public abstract class DungeonLoader {

    private JSONObject json;
    
    Goal goal = new Goal();
    
    public DungeonLoader(String filename) throws FileNotFoundException {
        json = new JSONObject(new JSONTokener(new FileReader("dungeons/" + filename)));
    }

    /**
     * Parses the JSON to create a dungeon.
     * @return
     */
    public Dungeon load() {
        int width = json.getInt("width");
        int height = json.getInt("height");

        Dungeon dungeon = new Dungeon(width, height);

        JSONArray jsonEntities = json.getJSONArray("entities");
        for (int i = 0; i < jsonEntities.length(); i++) {
            loadEntity(dungeon, jsonEntities.getJSONObject(i));
        }
        JSONObject jsonGoalCondition = json.getJSONObject("goal-condition");
        String jsonGoal = jsonGoalCondition.getString("goal");
        switch (jsonGoal) {
        case "exit": goal.setExitGoal(false);
        case "enemies": goal.setEnemiesGoal(false);
        case "boulders": goal.setBouldersGoal(false);
        case "treasure": goal.setTreasureGoal(false);
        }
        return dungeon;
    }

    private void loadEntity(Dungeon dungeon, JSONObject json) {
        String type = json.getString("type");
        int x = json.getInt("x");
        int y = json.getInt("y");

        Entity entity = null;
        switch (type) {
        case "player":
            Player player = new Player(dungeon, x, y);
            dungeon.setPlayer(player);
            onLoad(player);
            entity = player;
            break;
        case "wall":
            Wall wall = new Wall(x, y);
            onLoad(wall);
            entity = wall;
            break;
        // TODO Handle other possible entities
        case "boulder":
            Boulder boulder = new Boulder(x, y);
            onLoad(boulder);
            entity = boulder;
            break;
        case "switch":
            Switch switch1 = new Switch(dungeon, x, y);
            onLoad(switch1);
            entity = switch1;
            goal.totalSwitch++;
            break;
        case "treasure":
            Treasure treasure = new Treasure(x, y);
            onLoad(treasure);
            entity = treasure;
            goal.totalTreasure++;
            break;
        case "sword":
            Sword sword = new Sword(x, y);
            onLoad(sword);
            entity = sword;
            break;
        case "exit":
            Exit exit = new Exit(x, y);
            onLoad(exit);
            entity = exit;
            break;
        case "enemy":
            Enemy enemy = new Enemy(x, y);
            onLoad(enemy);
            entity = enemy;
            goal.totalTreasure++;
            break;
        case "bomb":
            Bomb bomb = new Bomb(x, y);
            onLoad(bomb);
            entity = bomb;
            break;
        case "invincibility":
        	Invincibility invincibility = new Invincibility(x, y);
            onLoad(invincibility);
            entity = invincibility;
            break;
        }
        dungeon.addEntity(entity);
    }

    public abstract void onLoad(Entity player);

    public abstract void onLoad(Wall wall);
    
    // TODO Create additional abstract methods for the other entities  
    public abstract void onLoad(Boulder boulder);
    
    public abstract void onLoad(Switch switch1);
    
    public abstract void onLoad(Treasure treasure);
    
    public abstract void onLoad(Sword sword);
    
    public abstract void onLoad(Exit exit);
    
    public abstract void onLoad(Enemy enemy);
    
    public abstract void onLoad(Invincibility invincibility);
    
    public abstract void onLoad(Bomb bomb);

}
