package unsw.dungeon;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnemyTest2 {

	@Test
	// kill enemy when player has both potion and sword
	public void test() {
		Dungeon dungeon = new Dungeon(6, 6);
		Player player = new Player(dungeon, 1, 1);
		dungeon.addEntity(player);
		dungeon.setPlayer(player);
		player.hasInvincibilityMoves = 1;
		player.hasSwordHits = 3;
		PlayerSubject playerSubject = new PlayerSubject();
		Enemy enemy = new Enemy(dungeon, 1, 1, playerSubject);
		dungeon.addEntity(enemy);
		enemy.kill();

		assertEquals(dungeon.getEntities().size(), 1);
		assertEquals(player.hasSwordHits, 3);
	}

}
