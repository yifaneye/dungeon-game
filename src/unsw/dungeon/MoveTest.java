package unsw.dungeon;

import static org.junit.Assert.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class MoveTest {

	@Test// Test enemy move without invincibility
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
		
		Timer timer = new Timer();
    	timer.schedule(enemy.getEnemyMove(), 0, 1000);
        dungeon.setTotalEnemies(dungeon.getTotalEnemies() + 1);
        assertEquals((int)enemy.getX(), 6);
        try{Thread.sleep(1000);}catch(InterruptedException e){System.out.println(e);}  
        System.out.print(enemy.getX());
        assertEquals((int)enemy.getX(), 5);
        assertEquals(enemy.getY(), 1);
        System.out.print(enemy.getX());
		
	}

}
