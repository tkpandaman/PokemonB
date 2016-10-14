package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Arrays;
import java.util.Random;

import org.junit.Test;

import model.Backpack;
import model.Battle;
import model.BattleMenu;
import model.Game;
import model.Map;
import model.MapItem;
import model.Pokeball;
import model.Potion;
import model.RunningShoes;
import model.State;
import model.MapTile;
import model.State;
import model.TileType;
import model.Trainer;
import model.WinCondition;
import model.pokemon.Arbok;

public class GameTest {

	private Random r = new Random(1337L);

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
		Game game = new Game(maps, map, r);

		assertEquals(game.getPlayerX(), 25);
		assertEquals(game.getPlayerY(), 40);

		game.moveUp();
		assertEquals(game.getPlayerX(), 25);
		assertEquals(game.getPlayerY(), 40);

		game.moveLeft();
		assertEquals(game.getPlayerX(), 25);
		assertEquals(game.getPlayerY(), 40);

		game.moveDown();
		assertEquals(game.getPlayerX(), 25);
		assertEquals(game.getPlayerY(), 40);

		game.moveRight();
		assertEquals(game.getPlayerX(), 25);
		assertEquals(game.getPlayerY(), 40);

		game.select();

		assertEquals(game.getPlayerX(), 25);
		assertEquals(game.getPlayerY(), 40);

		game.moveUp();
		assertEquals(game.getPlayerX(), 25);
		assertEquals(game.getPlayerY(), 40);

		game.moveLeft();
		assertEquals(game.getPlayerX(), 25);
		assertEquals(game.getPlayerY(), 40);

		game.moveRight();
		game.moveRight();
		game.moveRight();
		game.moveRight();
		game.moveUp();

		// test that gamestate is keeping up when game updates
		assertEquals(25,game.getPlayerX());
		assertEquals(40,game.getPlayerY());

	}

	@Test
	public void checkForItemsTest(){
		Map map = new Map(512, 512, "tileset-x2", 32);
		MapTile[][] tiles = map.getTiles();
		for(MapTile[] tt : tiles){
			for(MapTile tile : tt){
				tile.setTileType(TileType.Floor);
			}
		}
		Backpack bp = new Backpack(r);

		HashMap<String, Map> maps = new HashMap<String, Map>();
		maps.put("start", map);
		Game game = new Game(maps, map, r);
		game.setState(State.NORMAL);
		game.setPlayerPos(0, 0);


		MapItem pot = new MapItem(2,0, new Potion());
		MapItem shoes = new MapItem(3,0, new RunningShoes());
		MapItem ball = new MapItem(4,0, new Pokeball(r));

		game.getMap().addMapItem(pot);
		game.getMap().addMapItem(shoes);
		game.getMap().addMapItem(ball);

		game.moveRight();
		game.moveRight();
		game.moveRight();
		game.moveRight();

		assertEquals(4, game.getPlayerX());
		assertEquals(0, game.getPlayerY());

		assertEquals(31, game.getTrainer().openPack().getPokeballsLeft());
		assertEquals(1, game.getTrainer().openPack().getTrainerItems().size());
		assertEquals(1, game.getTrainer().openPack().getPokemonItems().size());

		game.moveLeft();

		assertEquals(3, game.getPlayerX());
		assertEquals(0, game.getPlayerY());

	}

	@Test
	public void checkGameStateTest(){
		Map map = new Map(512, 512, "", 32);
		Backpack bp = new Backpack(r);

		HashMap<String, Map> maps = new HashMap<String, Map>();
		maps.put("start", map);
		Game game = new Game(maps, map, r);

		for(int i = 0; i < 30; i++){
			game.getTrainer().openPack().usePokeball();
		}
		assertEquals(0, game.getTrainer().openPack().getPokeballsLeft());
		game.moveRight();
		assertEquals(State.LOSE, game.getState());

		Game gameTwo = new Game(maps, map, r);

		while(gameTwo.getState() != State.BATTLE){
			gameTwo.checkForPokemon(r);
		}
		gameTwo.moveDown();
		assertEquals(State.BATTLE, gameTwo.getState());

	}

	@Test
	public void menuTest(){
		Map map = new Map(512, 512, "", 32);
		Backpack bp = new Backpack(r);

		HashMap<String, Map> maps = new HashMap<String, Map>();
		maps.put("start", map);
		Game game = new Game(maps, map, r);
		game.setState(State.NORMAL);

		game.chooseMenu();
		assertEquals(State.MENU, game.getState());

		game.chooseMenu();
		assertEquals(State.NORMAL, game.getState());

		while(game.getState() != State.BATTLE){
			game.checkForPokemon(r);
		}
		game.chooseMenu();
		assertEquals(State.BATTLE, game.getState());
		BattleMenu menu = game.getBattleMenu();
		assertEquals(menu, game.getBattleMenu());
	}

	@Test
	public void winConditionPokemonTest(){
		Map map = new Map(512, 512, "tileset-x2", 32);
		MapTile[][] tiles = map.getTiles();
		for(MapTile[] tt : tiles){
			for(MapTile tile : tt){
				tile.setTileType(TileType.Floor);
			}
		}
		Backpack bp = new Backpack(r);

		HashMap<String, Map> maps = new HashMap<String, Map>();
		maps.put("start", map);
		Game game = new Game(maps, map, r);
		game.setState(State.NORMAL);
		game.setPlayerPos(0, 0);

		game.setWin(WinCondition.POKEMON);

		assertEquals(WinCondition.POKEMON, game.getWin());

		for(int i = 0; i < 500; i++){
			game.getTrainer().takeStep();
		}

		game.moveRight();

		assertEquals(State.LOSE, game.getState());

		game.setState(State.NORMAL);

		game.setWin(WinCondition.POKEMON);

		for(int i = 0; i < 10; i++){
			game.getTrainer().openPack().addPokemon(new Arbok());
		}

		game.moveRight();

		assertEquals(State.WIN, game.getState());

		assertEquals(game.getTransitionAlpha(), 0.1, 0.00001);


	}

	@Test
	public void winConditionSafariballsTest(){
		Map map = new Map(512, 512, "tileset-x2", 32);
		MapTile[][] tiles = map.getTiles();
		for(MapTile[] tt : tiles){
			for(MapTile tile : tt){
				tile.setTileType(TileType.Floor);
			}
		}
		Backpack bp = new Backpack(r);

		HashMap<String, Map> maps = new HashMap<String, Map>();
		maps.put("start", map);
		Game game = new Game(maps, map, r);
		game.setState(State.NORMAL);
		game.setPlayerPos(0, 0);

		game.setWin(WinCondition.SAFARIBALLS);

		for(int i = 0; i < 30; i++){
			game.getTrainer().openPack().usePokeball();
		}

		game.moveRight();

		assertEquals(State.WIN, game.getState());
		assertEquals(WinCondition.SAFARIBALLS, game.getWin());
	}




	@Test
	public void battleTest(){
		Map map = new Map(512, 512, "", 32);
		Backpack bp = new Backpack(r);

		HashMap<String, Map> maps = new HashMap<String, Map>();
		maps.put("start", map);
		Game game = new Game(maps, map, r);

		while(game.getState() != State.BATTLE){
			game.checkForPokemon(r);
		}

		Battle b = game.getBattle();
		assertEquals(b, game.getBattle());


	}


	@Test
	public void testCheckPok(){
		Map map = new Map(512, 512, "", 32);

		HashMap<String, Map> maps = new HashMap<String, Map>();
		maps.put("start", map);
		Game game = new Game(maps, map, r);

		game.checkForPokemon(new TestRandom(Arrays.asList(2)));
		assertEquals(game.getState(), State.INTRO);

		game.checkForPokemon(new TestRandom(Arrays.asList(0)));
		assertEquals(game.getState(), State.FROZEN);
	}

	@Test
	public void testGetters()
	{
		Map map = new Map(512, 512, "tileset-x2", 32);
		HashMap<String, Map> maps = new HashMap<String, Map>();
		maps.put("start", map);
		Game game = new Game(maps, map, r);
		assertEquals( "Ash Ketchup", game.getTrainer().getName() );
		assertEquals( "Map", game.getMap().getClass().getSimpleName() );
		assertEquals( 25, game.getPlayerX() );
		assertEquals( 40, game.getPlayerY() );
		assertEquals( State.INTRO, game.getState() );
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
		map.setLeftMap("map2");
		map2.setRightMap("start");
		Game game = new Game(maps, map, r);
		game.setState(State.NORMAL);

		game.setPlayerPos(0,1);
		game.moveUp();
		game.moveUp();

		assertEquals(map2, game.getMap());

		game.moveDown();
		game.moveDown();

		assertEquals(map, game.getMap());

		game.moveLeft();

		assertEquals(map2, game.getMap());

		game.moveRight();

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
		Game game = new Game(maps, map,r);
		game.setState(State.NORMAL);
		assertEquals( State.NORMAL, game.getState() );
		game.chooseMenu();
		assertEquals( State.MENU, game.getState() );
		game.chooseMenu();
		assertEquals( State.NORMAL, game.getState() );
	}

}