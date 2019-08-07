/**
 * 
 */
package unsw.dungeon.test;

import unsw.dungeon.*;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author z5210859
 *
 */
public class PlayerCollectKeyTest {

	@Test
	public void test() {
		Dungeon dungeon = new Dungeon(6, 6);
		Player player = new Player(dungeon, 1, 1, 0);
		dungeon.addEntity(player);
			
		Key key = new Key(2, 1, 2521, 0);
		dungeon.addEntity(key);
		Key key2 = new Key(3, 1, 2531, 0);
		dungeon.addEntity(key2);
		Key key3 = new Key(4, 1, 2541, 0);
		dungeon.addEntity(key3);
		
		// player without key intends to pick up a key - success
		player.moveRight();
		player.collect();
		assertEquals(player.hasKeyID, 2521);
		
		// player with key intends to pick up another key - failed
		player.moveRight();
		player.collect();
		assertEquals(player.hasKeyID, 2521);
		
		// player with key intends to pick up another key - failed
		player.moveRight();
		player.collect();
		assertEquals(player.hasKeyID, 2521);
	}

}
