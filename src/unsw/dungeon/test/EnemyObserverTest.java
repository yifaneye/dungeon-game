package unsw.dungeon.test;

import unsw.dungeon.*;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnemyObserverTest {

	@Test
	public void test() {
		Dungeon dungeon = new Dungeon(6, 6);
		PlayerSubject playerSubject = new PlayerSubject();
		Enemy enemy = new Enemy(dungeon, 1, 1, playerSubject);
		dungeon.addEntity(enemy);
		
		// test initial observer
		assertEquals(enemy.getObserver().x, 0);
		assertEquals(enemy.getObserver().y, 0);
		assertEquals(enemy.getObserver().hasInvincibilityMoves, 0);

		// test when subject changed
		playerSubject.setXY(2, 7);
		playerSubject.setHasInvincibilityMoves(15);
		assertEquals(enemy.getObserver().x, 2);
		assertEquals(enemy.getObserver().y, 7);
		assertEquals(enemy.getObserver().hasInvincibilityMoves, 15);
	}

}