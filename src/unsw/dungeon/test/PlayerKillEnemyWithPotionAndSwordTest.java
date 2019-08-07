package unsw.dungeon.test;

import unsw.dungeon.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerKillEnemyWithPotionAndSwordTest {

	@Test
	// kill enemy when player has both invincibility potion and sword
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
		
		Invincibility invincibility = new Invincibility(3, 1, 0);
		dungeon.addEntity(invincibility);
		player.moveRight();
		player.collect();
		assertEquals(player.hasInvincibilityMoves, 15);
		
		PlayerSubject playerSubject = new PlayerSubject();
		Enemy enemy = new Enemy(dungeon, 3, 2, playerSubject, 0);
		dungeon.addEntity(enemy);
		assertEquals(enemy.getX(), 3);
		assertEquals(enemy.getY(), 2);
		
		player.moveDown();
		assertEquals(player.hasSwordHits, 5);
		assertEquals(player.hasInvincibilityMoves, 14);
		
		Enemy enemy2 = new Enemy(dungeon, 6, 2, playerSubject, 0);
		dungeon.addEntity(enemy2);
		assertEquals(enemy2.getX(), 6);
		assertEquals(enemy2.getY(), 2);
		
		player.moveDown();
		assertEquals(player.hasSwordHits, 5);
		assertEquals(player.hasInvincibilityMoves, 13);
		
		player.moveDown();
		assertEquals(player.hasSwordHits, 5);
		assertEquals(player.hasInvincibilityMoves, 12);
		
		player.moveDown();
		assertEquals(player.hasSwordHits, 5);
		assertEquals(player.hasInvincibilityMoves, 11);
	}
}
