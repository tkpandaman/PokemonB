package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Game;
import model.Map;

public class GameTest {

	@Test
	public void test() {
		
		Map map = new Map(512, 512, "", 32);
		Game game = new Game(map);
		
		assertEquals(game.getPlayerX(), 0);
		assertEquals(game.getPlayerY(), 0);
		
		game.moveUp();
		assertEquals(game.getPlayerX(), 0);
		assertEquals(game.getPlayerY(), 0);
		
		game.moveLeft();
		assertEquals(game.getPlayerX(), 0);
		assertEquals(game.getPlayerY(), 0);
		
		game.moveDown();
		assertEquals(game.getPlayerX(), 0);
		assertEquals(game.getPlayerY(), 1);
		
		game.moveRight();
		assertEquals(game.getPlayerX(), 1);
		assertEquals(game.getPlayerY(), 1);
		
		game.moveUp();
		assertEquals(game.getPlayerX(), 1);
		assertEquals(game.getPlayerY(), 0);
		
		game.moveLeft();
		assertEquals(game.getPlayerX(), 0);
		assertEquals(game.getPlayerY(), 0);
		
		game.moveRight();
		game.moveRight();
		game.moveRight();
		game.moveRight();
		
		// test that gamestate is keeping up when game updates
		assertEquals(game.getPlayerX(), 4);
		assertEquals(game.getPlayerY(), 0);
	}

}
