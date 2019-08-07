package unsw.dungeon.test;

import unsw.dungeon.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerKillEnemyWithSwordTest {

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
		
		PlayerSubject playerSubject = new PlayerSubject();
		Enemy enemy = new Enemy(dungeon, 2, 2, playerSubject, 0);
		dungeon.addEntity(enemy);
		assertEquals(enemy.getX(), 2);
		assertEquals(enemy.getY(), 2);
		
		player.moveDown();
		assertEquals(player.hasSwordHits, 4);
		
		Enemy enemy2 = new Enemy(dungeon, 3, 2, playerSubject, 0);
		assertEquals(enemy2.getX(), 3);
		assertEquals(enemy2.getY(), 2);
		dungeon.addEntity(enemy2);
		
		player.moveRight();
		assertEquals(player.hasSwordHits, 3);
	}
	
}
