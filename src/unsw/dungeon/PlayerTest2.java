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
public class PlayerTest2 {

	@Test
	public void test() {
		Dungeon dungeon = new Dungeon(5, 5);
		Player player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		
		int hasSwordHits = 0;
		int hasInvincibilityMoves = 0;
		int hasKeyID = -1;
		int hasUnlitBombs = 0;
		
		assertEquals(hasSwordHits, hasUnlitBombs);
		assertEquals(hasSwordHits, hasInvincibilityMoves);
		assertEquals(hasInvincibilityMoves, hasUnlitBombs);
		
		Boulder boulder12 = new Boulder(1, 2);
		Boulder boulder21 = new Boulder(2, 1);
		dungeon.addEntity(boulder21);
		Boulder ret1 = player.hasBoulder(2, 1);
		
		assertEquals(ret1, boulder21);
		assertNotEquals(ret1, boulder12);
		
		// intended to move right - not blocked by boulder
		boolean ret2 = player.playerCanMove(3, 1);
		assertEquals(ret2, true);
		
		// intended to move right - not blocked by boulder
		player.moveRight();
		assertEquals(player.getX(), 2);
		
		// intended to move down - blocked by boulder
		Boulder boulder41 = new Boulder(4, 1);
		dungeon.addEntity(boulder41);
		ret2 = player.playerCanMove(1, 4);
		assertEquals(ret2, true);
		
		boolean ret3 = player.isUnarmedPlayer();
		assertEquals(ret3, true);
		
		Invincibility invincibility = new Invincibility(2, 1);
		dungeon.addEntity(invincibility);
		player.collect();
		ret3 = player.isUnarmedPlayer();
		assertEquals(ret3, false);
		assertEquals(player.hasInvincibilityMoves, 15);
		
		Invincibility invincibility2 = new Invincibility(2, 1);
		dungeon.addEntity(invincibility2);
		player.collect();
		ret3 = player.isUnarmedPlayer();
		assertEquals(ret3, false);
		assertEquals(player.hasInvincibilityMoves, 15);
		
		player.moveUp();
		assertEquals(player.hasInvincibilityMoves, 14);
	}

}

