package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Game;
import model.Map;
import model.State;

public class GameTest {

	@Test
	public void testMove() {
		
		Map map = new Map(512, 512, "tileset-x2", 32);
		ArrayList<Map> maps = new ArrayList<Map>();
		maps.add(map);
		Game game = new Game(maps);
		
		assertEquals(game.getPlayerX(), 2);
		assertEquals(game.getPlayerY(), 2);
		
		game.moveUp();
		assertEquals(game.getPlayerX(), 2);
		assertEquals(game.getPlayerY(), 1);
		
		game.moveLeft();
		assertEquals(game.getPlayerX(), 1);
		assertEquals(game.getPlayerY(), 1);
		
		game.moveDown();
		assertEquals(game.getPlayerX(), 1);
		assertEquals(game.getPlayerY(), 2);
		
		game.moveRight();
		assertEquals(game.getPlayerX(), 2);
		assertEquals(game.getPlayerY(), 2);
		
		game.moveUp();
		assertEquals(game.getPlayerX(), 2);
		assertEquals(game.getPlayerY(), 1);
		
		game.moveLeft();
		assertEquals(game.getPlayerX(), 1);
		assertEquals(game.getPlayerY(), 1);
		
		game.moveRight();
		game.moveRight();
		game.moveRight();
		game.moveRight();
		game.moveUp();
		
		// test that gamestate is keeping up when game updates
		assertEquals(game.getPlayerX(), 5);
		assertEquals(game.getPlayerY(), 0);
	}
	@Test
	public void testGetters()
	{
	    Map map = new Map(512, 512, "tileset-x2", 32);
        ArrayList<Map> maps = new ArrayList<Map>();
        maps.add(map);
        Game game = new Game(maps);
        assertEquals( "Sir Dumplestein", game.getTrainer().getName() );
        assertEquals( "Map", game.getMap().getClass().getSimpleName() );
        assertEquals( 2, game.getPlayerX() );
        assertEquals( 2, game.getPlayerY() );
        assertEquals( State.NORMAL, game.getState() );
	}
}
