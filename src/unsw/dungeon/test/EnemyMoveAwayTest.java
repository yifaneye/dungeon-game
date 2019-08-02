package unsw.dungeon.test;

import unsw.dungeon.*;

import static org.junit.Assert.*;
import java.util.Timer;
import org.junit.Test;

public class EnemyMoveAwayTest {

	@Test // Test enemy run away from the player when player has invincibility potion
	public void test() {
		Dungeon dungeon = new Dungeon(6, 6);
		
		Player player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		PlayerSubject playerSubject = new PlayerSubject();
		Enemy enemy = new Enemy(dungeon, 2, 2, playerSubject);
		dungeon.addEntity(enemy);
		
		assertEquals(enemy.getX(), 2);
		assertEquals(enemy.getY(), 2);

		playerSubject.setHasInvincibilityMoves(15);

		Timer timer = new Timer();
		timer.schedule(enemy.getEnemyMove(), 0, 750);
		dungeon.setTotalEnemies(dungeon.getTotalEnemies() + 1);
		
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		
		assertEquals(enemy.getX(), 2);
		assertEquals(enemy.getY(), 3);
		
		try {
			Thread.sleep(750);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		
		assertEquals(enemy.getX(), 2);
		assertEquals(enemy.getY(), 4);
		
		try {
			Thread.sleep(750);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		
		assertEquals(enemy.getX(), 2);
		assertEquals(enemy.getY(), 5);
	}

}
