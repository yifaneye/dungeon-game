package unsw.dungeon.test;

import unsw.dungeon.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnemyObserverTest {

	@Test
	public void test() {
		Dungeon dungeon = new Dungeon(6, 6);
		PlayerSubject playerSubject = new PlayerSubject();
		Enemy enemy = new Enemy(dungeon, 1, 1, playerSubject, 0);
		dungeon.addEntity(enemy);
		
		// test initial observer
		assertEquals(enemy.getObserver().x, 0);
		assertEquals(enemy.getObserver().y, 0);
		assertEquals(enemy.getObserver().hasInvincibilityMoves, 0);

		// test observer after subject has changed
		playerSubject.setXY(3, 4);
		playerSubject.setHasInvincibilityMoves(15);
		assertEquals(enemy.getObserver().x, 3);
		assertEquals(enemy.getObserver().y, 4);
		assertEquals(enemy.getObserver().hasInvincibilityMoves, 15);
		
		// test observer after subject has changed
		playerSubject.setXY(4, 4);
		playerSubject.setHasInvincibilityMoves(14);
		assertEquals(enemy.getObserver().x, 4);
		assertEquals(enemy.getObserver().y, 4);
		assertEquals(enemy.getObserver().hasInvincibilityMoves, 14);
	}

}
