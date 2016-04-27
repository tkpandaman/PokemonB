package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Arrays;

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
		HashMap<String, Map> maps = new HashMap<String, Map>();
		maps.put("start", map);
		Game game = new Game(maps, map);
		
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
		
		HashMap<String, Map> maps = new HashMap<String, Map>();
		maps.put("start", map);
		Game game = new Game(maps, map);
		
		game.checkForPokemon(new TestRandom(Arrays.asList(2)));
		assertEquals(game.getState(), State.NORMAL);
		
		game.checkForPokemon(new TestRandom(Arrays.asList(0)));
		assertEquals(game.getState(), State.BATTLE);
	}

	@Test
	public void testGetters()
	{
	    Map map = new Map(512, 512, "tileset-x2", 32);
	    HashMap<String, Map> maps = new HashMap<String, Map>();
	    maps.put("start", map);
        Game game = new Game(maps, map);
        assertEquals( "Ash Ketchup", game.getTrainer().getName() );
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
		HashMap<String, Map> maps = new HashMap<String, Map>();
		maps.put("start", map);
		maps.put("map2", map2);
		map.setUpMap("map2");
		map2.setDownMap("start");
		Game game = new Game(maps, map);
		
		game.setPlayerPos(2,1);
		game.moveUp();
		game.moveUp();
		
		assertEquals(map2, game.getMap());
		
		game.moveDown();
		
		assertEquals(map, game.getMap());
		
	}
	@Test
	public void testChangingStateToMenuAndBack()
	{
	    Map map = new Map(512, 512, "tileset-x2", 32);
        Map map2 = new Map(512, 512, "tileset-x2", 32);
        HashMap<String, Map> maps = new HashMap<String, Map>();
        maps.put("start", map);
        maps.put("map2", map2);
        map.setUpMap("map2");
        map2.setDownMap("start");
        Game game = new Game(maps, map);
        assertEquals( State.NORMAL, game.getState() );
        game.chooseMenu();
        assertEquals( State.MENU, game.getState() );
        game.chooseMenu();
        assertEquals( State.NORMAL, game.getState() );
	}
	
}