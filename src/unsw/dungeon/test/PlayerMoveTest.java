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
public class PlayerMoveTest {

	@Test
	public void test() {
		Dungeon dungeon = new Dungeon(6, 6);
		Player player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);

		Boulder boulder12 = new Boulder(1, 2);
		Boulder boulder21 = new Boulder(2, 1);
		dungeon.addEntity(boulder21);
		Boulder ret1 = player.hasBoulder(2, 1);

		assertEquals(ret1, boulder21);
		assertNotEquals(ret1, boulder12);

		// player intends to move right - not blocked by boulder
		boolean ret2 = player.playerCanMove(3, 1);
		assertEquals(ret2, true);

		// player intends to move right - not blocked by boulder
		player.moveRight();
		assertEquals(player.getX(), 2);

		// player intends to move down - blocked by boulder
		Boulder boulder41 = new Boulder(4, 1);
		dungeon.addEntity(boulder41);
		ret2 = player.playerCanMove(1, 4);
		assertEquals(ret2, true);
	}

}
