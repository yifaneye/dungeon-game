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
public class PlayerCollectObjectTest {

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
		
		Boulder boulder12 = new Boulder(1, 2);
		Boulder boulder21 = new Boulder(2, 1);
		dungeon.addEntity(boulder12);
		Boulder ret1 = player.hasBoulder(1, 2);
		
		assertEquals(ret1, boulder12);
		assertNotEquals(ret1, boulder21);
		
		// intended to move down - not blocked by boulder
		boolean ret2 = player.playerCanMove(1, 3);
		assertEquals(ret2, true);
		
		// intended to move down - not blocked by boulder
		player.moveDown();
		assertEquals(player.getY(), 2);
		
		// intended to move down - blocked by boulder
		Boulder boulder14 = new Boulder(1, 4);
		dungeon.addEntity(boulder14);
		ret2 = player.playerCanMove(1, 4);
		assertEquals(ret2, false);
		
		// intended to move down - not blocked by boulder
		player.moveDown();
		assertEquals(player.getY(), 2);
		
		// intended to move left - not blocked by boundary
		player.moveLeft();
		assertEquals(player.getX(), 0);
		
		// intended to move left - blocked by boundary
		player.moveLeft();
		assertEquals(player.getX(), 0);
		
		boolean ret3 = player.isUnarmedPlayer();
		assertEquals(ret3, true);
		
		Sword sword = new Sword(0, 2);
		dungeon.addEntity(sword);
		player.collect();
		ret3 = player.isUnarmedPlayer();
		assertEquals(ret3, false);
	}

}
