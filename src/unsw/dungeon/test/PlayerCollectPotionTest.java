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
public class PlayerCollectPotionTest {

	@Test
	public void test() {
		Dungeon dungeon = new Dungeon(6, 6);
		Player player = new Player(dungeon, 1, 1, 0);
		dungeon.addEntity(player);
		
		assertEquals(player.hasSwordHits, 0);
		assertEquals(player.hasInvincibilityMoves, 0);
		assertEquals(player.hasKeyID, 0);
		assertEquals(player.hasUnlitBombs, 0);
		
		boolean ret = player.isUnarmedPlayer();
		assertEquals(ret, true);

		Invincibility invincibility = new Invincibility(2, 1, 0);
		dungeon.addEntity(invincibility);
		player.moveRight();
		player.collect();
		ret = player.isUnarmedPlayer();
		assertEquals(ret, false);
		assertEquals(player.hasInvincibilityMoves, 15);

		Invincibility invincibility2 = new Invincibility(3, 1, 0);
		dungeon.addEntity(invincibility2);
		player.moveRight();
		assertEquals(player.hasInvincibilityMoves, 14);
		player.collect();
		ret = player.isUnarmedPlayer();
		assertEquals(ret, false);
		assertEquals(player.hasInvincibilityMoves, 15);

		player.moveUp();
		ret = player.isUnarmedPlayer();
		assertEquals(ret, false);
		assertEquals(player.hasInvincibilityMoves, 14);
	}

}
