package unsw.dungeon.test;

import unsw.dungeon.*;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class EnemyNormalMoveTest {

	@Test // Test enemy run towards the player
	public void test() {
Dungeon dungeon = new Dungeon(6, 6);
		
		Player player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		PlayerSubject playerSubject = new PlayerSubject();
		Enemy enemy = new Enemy(dungeon, 5, 2, playerSubject);
		dungeon.addEntity(enemy);
		
		assertEquals(enemy.getX(), 5);
		assertEquals(enemy.getY(), 2);

		Timer timer = new Timer();
		timer.schedule(enemy.getEnemyMove(), 0, 750);
		dungeon.setTotalEnemies(dungeon.getTotalEnemies() + 1);
		
		try {
			Thread.sleep(700);
		} catch (InterruptedException e) {
			System.out.println(e);
		}
		
		assertEquals(enemy.getX(), 4);
		assertEquals(enemy.getY(), 2);
	}
	
}
