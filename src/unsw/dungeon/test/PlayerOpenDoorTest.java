/**
 * 
 */
package unsw.dungeon.test;

import unsw.dungeon.*;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

/**
 * @author z5210859
 *
 */
public class PlayerOpenDoorTest {

	@Test
	public void test() throws FileNotFoundException {
		Dungeon dungeon = new Dungeon(6, 6);
		Player player = new Player(dungeon, 1, 1, 0);
		dungeon.addEntity(player);
			
		Key key = new Key(2, 1, 2511, 0);
		dungeon.addEntity(key);
		
		player.moveRight();
		player.collect();
		assertEquals(player.hasKeyID, 2511);
		
		Door door = new Door(3, 1, 2567, dungeon, 0);
		dungeon.addEntity(door);
		Door door2 = new Door(2, 2, 2511, dungeon, 0);
		dungeon.addEntity(door2);	
		Door door3 = new Door(2, 3, 2511, dungeon, 0);
		dungeon.addEntity(door3);	
		
		// player intends to go through door no.2567 with key no.2511 - failed
		player.moveRight();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 1);
		assertEquals(player.hasKeyID, 2511);

		// player intends to go through door no.2511 with key no.2511 - success
		player.moveDown();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 2);
		assertEquals(player.hasKeyID, 0);
		
		// player intends to go through door no.2511 without a key - failed
		player.moveDown();
		assertEquals(player.getX(), 2);
		assertEquals(player.getY(), 2);
		assertEquals(player.hasKeyID, 0);
	}

}
