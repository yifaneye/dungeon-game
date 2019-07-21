package unsw.dungeon;

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
		assertEquals(enemy.getObserver().hasInvincibility, false);

		// test when subject changed

		playerSubject.setXY(2, 7);
		playerSubject.setHasInvincibility(true);
		assertEquals(enemy.getObserver().x, 2);
		assertEquals(enemy.getObserver().y, 7);
		assertEquals(enemy.getObserver().hasInvincibility, true);
	}

}