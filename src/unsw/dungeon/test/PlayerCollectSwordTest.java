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
public class PlayerCollectSwordTest {

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

		Sword sword = new Sword(2, 1, 0);
		dungeon.addEntity(sword);
		player.moveRight();
		player.collect();
		assertEquals(player.hasSwordHits, 5);
		
		Sword sword2 = new Sword(3, 1, 0);
		dungeon.addEntity(sword2);
		player.moveRight();
		player.collect();
		assertEquals(player.hasSwordHits, 5);
	}

}