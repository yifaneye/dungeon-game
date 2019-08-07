package unsw.dungeon.test;

import unsw.dungeon.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerKillEnemyWithPotionTest {

	@Test
	// kill enemy when player has invincibility potion
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
		assertEquals(player.hasInvincibilityMoves, 15);
		
		player.moveUp();
		assertEquals(player.hasInvincibilityMoves, 14);
		
		PlayerSubject playerSubject = new PlayerSubject();
		Enemy enemy = new Enemy(dungeon, 1, 0, playerSubject, 0);
		dungeon.addEntity(enemy);
		assertEquals(enemy.getX(), 1);
		assertEquals(enemy.getY(), 0);
		
		player.moveLeft();
		assertEquals(player.hasInvincibilityMoves, 13);
		
		Enemy enemy2 = new Enemy(dungeon, 0, 0, playerSubject, 0);
		dungeon.addEntity(enemy2);
		assertEquals(enemy2.getX(), 0);
		assertEquals(enemy2.getY(), 0);
		
		player.moveLeft();
		assertEquals(player.hasInvincibilityMoves, 12);
	}
	
}
