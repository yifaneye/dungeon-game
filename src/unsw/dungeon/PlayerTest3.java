/**
 * 
 */
package unsw.dungeon;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author z5210859
 *
 */
public class PlayerTest3 {

	@Test
	public void test() {
		Dungeon dungeon = new Dungeon(6, 6);
		Player player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		
		int hasSwordHits = 0;
		int hasInvincibilityMoves = 0;
		int hasKeyID = -1;
		int hasUnlitBombs = 0;
		
		assertNotEquals(hasKeyID, hasSwordHits);
		assertNotEquals(hasKeyID, hasInvincibilityMoves);
		assertNotEquals(hasKeyID, hasUnlitBombs);
		
		boolean ret = player.isUnarmedPlayer();
		assertEquals(ret, true);
		
		player.moveRight();
		Key key = new Key(2, 1, 2511);
		dungeon.addEntity(key);
		player.collect();
		
		Door door = new Door(3, 1, 2511);
		dungeon.addEntity(door);
		Door door2 = new Door(3, 2, 2567);
		dungeon.addEntity(door2);		
		
		player.moveRight();
		assertEquals(player.getX(), 3);
		assertEquals(player.getY(), 1);
		assertEquals(player.hasKeyID, -1);

		player.moveDown();
		assertEquals(player.getX(), 3);
		assertEquals(player.getY(), 1);
		assertEquals(player.hasKeyID, -1);
	}

}

