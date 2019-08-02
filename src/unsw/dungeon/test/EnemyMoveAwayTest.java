package unsw.dungeon.test;

import unsw.dungeon.*;

import static org.junit.Assert.*;
import java.util.Timer;
import org.junit.Test;

public class EnemyMoveAwayTest {

	@Test // Test enemy move with invincibility
	public void test() {
		Dungeon dungeon = new Dungeon(6, 6);
		Player player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);

		PlayerSubject playerSubject = new PlayerSubject();
		Enemy enemy = new Enemy(dungeon, 6, 1, playerSubject);
		dungeon.addEntity(enemy);

		assertEquals(enemy.getY(), 1);
		assertEquals(enemy.getX(), 6);

		playerSubject.setHasInvincibility(true);

		Timer timer = new Timer();
		timer.schedule(enemy.getEnemyMove(), 0, 1000);
		dungeon.setTotalEnemies(dungeon.getTotalEnemies() + 1);
		assertEquals((int) enemy.getX(), 6);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		System.out.print(enemy.getX());
		assertEquals((int) enemy.getX(), 7);
		assertEquals(enemy.getY(), 1);
		System.out.print(enemy.getX());
	}

}
