package tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.ArrayList;

import org.junit.Test;
import model.Game;
import model.Map;
import model.State;
import model.MapTile;
import model.State;
import model.TileType;

public class GameTest {

	@Test
	public void testMove() {
		
		Map map = new Map(512, 512, "tileset-x2", 32);
		MapTile[][] tiles = map.getTiles();
		for(MapTile[] tt : tiles){
			for(MapTile tile : tt){
				tile.setTileType(TileType.Floor);
			}
		}
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
		
		game.select();
		
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
		assertEquals(5,game.getPlayerX());
		assertEquals(0,game.getPlayerY());
	}

	@Test
	public void testCheckPok(){
		Map map = new Map(512, 512, "", 32);
		
		ArrayList<Map> maps = new ArrayList<Map>();
		maps.add(map);
		Game game = new Game(maps);
		
		game.checkForPokemon(new TestRandom(Arrays.asList(2)));
		assertEquals(game.getState(), State.NORMAL);
		
		game.checkForPokemon(new TestRandom(Arrays.asList(0)));
		assertEquals(game.getState(), State.BATTLE);
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
	
	@Test
	public void testTransition()
	{
		Map map = new Map(512, 512, "tileset-x2", 32);
		Map map2 = new Map(512, 512, "tileset-x2", 32);
		ArrayList<Map> maps = new ArrayList<Map>();
		maps.add(map);
		maps.add(map2);
		Game game = new Game(maps);
		
		game.setPlayerPos(2,1);
		game.moveUp();
		game.moveUp();
		
		assertEquals(map2, game.getMap());
		
		game.moveUp();
		
		assertEquals(map, game.getMap());
		
	}
	
	@Test
	public void chooseMenuTest(){
		Game game = new Game();
	}
	
	
	
	
	
	
	
}