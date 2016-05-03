package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import org.junit.Test;
import model.Game;
import model.IntroScreenModel;
import model.Map;
import model.MapTile;
import model.Pokeball;
import model.TileType;

public class TestIntroScreenModel extends SerializableTestCase
{
    @Test
    public void testIntroScreenState()
    {
        
    }
    @Test
    public void testIntroScreenModelState()
    {
        Map map = new Map(512, 512, "tileset-x2", 32);
        MapTile[][] tiles = map.getTiles();
        for(MapTile[] tt : tiles){
            for(MapTile tile : tt){
                tile.setTileType(TileType.Floor);
            }
        }
        HashMap<String, Map> maps = new HashMap<String, Map>();
        maps.put("start", map);
        Game game = new Game(maps, map, new Random());
        IntroScreenModel i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.Start);
        assertEquals( IntroScreenModel.IntroScreenState.Start, i.getState() );
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.Main);
        assertEquals( IntroScreenModel.IntroScreenState.Main, i.getState() );
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.NewGame);
        assertEquals( IntroScreenModel.IntroScreenState.NewGame, i.getState() );
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.StartNewGame);
        assertEquals( IntroScreenModel.IntroScreenState.StartNewGame, i.getState() );
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.LoadGame);
        assertEquals( IntroScreenModel.IntroScreenState.LoadGame, i.getState() );
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.LoadFail);
        assertEquals( IntroScreenModel.IntroScreenState.LoadFail, i.getState() );
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.Credits);
        assertEquals( IntroScreenModel.IntroScreenState.Credits, i.getState() );
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.Quit);
        assertEquals( IntroScreenModel.IntroScreenState.Quit, i.getState() );
    }
    @Test
    public void testIntroScreenModelGetMenuOptions()
    {
        Map map = new Map(512, 512, "tileset-x2", 32);
        MapTile[][] tiles = map.getTiles();
        for(MapTile[] tt : tiles){
            for(MapTile tile : tt){
                tile.setTileType(TileType.Floor);
            }
        }
        HashMap<String, Map> maps = new HashMap<String, Map>();
        maps.put("start", map);
        Game game = new Game(maps, map, new Random());
        String[] startChoice = new String[]{"Press Enter"};
        String[] fail = new String[0];
        String[] mainChoice = new String[]{"New Game", "Continue", "Credits", "Quit"};
        String[] loadChoice = new String[]{"Back"};
        String[] newGameChoice = new String[]{"Walk 500 Steps", "Capture 10 Pokemon", "Use 30 Safariballs", "Back"};
        IntroScreenModel i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.Start);
        for( int j = 0; j < i.getMenuOptions().length; j++ )
        {
            assertEquals( startChoice[j], i.getMenuOptions()[j] );
        }
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.Main);
        for( int j = 0; j < i.getMenuOptions().length; j++ )
        {
            assertEquals( mainChoice[j], i.getMenuOptions()[j] );
        }
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.NewGame);
        for( int j = 0; j < i.getMenuOptions().length; j++ )
        {
            assertEquals( newGameChoice[j], i.getMenuOptions()[j] );
        }
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.Credits);
        for( int j = 0; j < i.getMenuOptions().length; j++ )
        {
            assertEquals( loadChoice[j], i.getMenuOptions()[j] );
        }
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.LoadFail);
        for( int j = 0; j < i.getMenuOptions().length; j++ )
        {
            assertEquals( loadChoice[j], i.getMenuOptions()[j] );
        }
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.LoadGame);
        for( int j = 0; j < i.getMenuOptions().length; j++ )
        {
            assertEquals( fail[j], i.getMenuOptions()[j] );
        }
    }
    @Test
    public void testIntroScreenReset()
    {
        Map map = new Map(512, 512, "tileset-x2", 32);
        MapTile[][] tiles = map.getTiles();
        for(MapTile[] tt : tiles){
            for(MapTile tile : tt){
                tile.setTileType(TileType.Floor);
            }
        }
        HashMap<String, Map> maps = new HashMap<String, Map>();
        maps.put("start", map);
        Game game = new Game(maps, map, new Random());
        IntroScreenModel i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.LoadGame);
        assertEquals( IntroScreenModel.IntroScreenState.LoadGame, i.getState() );
        i.reset();
        assertEquals(  IntroScreenModel.IntroScreenState.Start, i.getState() );
    }
    @Test
    public void testIntroScreenModelSelectAndMove()
    {
        Map map = new Map(512, 512, "tileset-x2", 32);
        MapTile[][] tiles = map.getTiles();
        for(MapTile[] tt : tiles){
            for(MapTile tile : tt){
                tile.setTileType(TileType.Floor);
            }
        }
        HashMap<String, Map> maps = new HashMap<String, Map>();
        maps.put("start", map);
        Game game = new Game(maps, map, new Random());
        IntroScreenModel i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.Main);
        i.moveUp();
        assertEquals( 0, i.getSelected() );
        i.moveDown();
        assertEquals( 1, i.getSelected() );
        i.moveDown();
        assertEquals( 2, i.getSelected() );
        i.moveDown();
        assertEquals( 3, i.getSelected() );
        i.moveDown();
        assertEquals( 3, i.getSelected() );
        i.moveUp();
        assertEquals( 2, i.getSelected() );
    }
    @Test
    public void testIntroScreenModelLoadFail()
    {
        Map map = new Map(512, 512, "tileset-x2", 32);
        MapTile[][] tiles = map.getTiles();
        for(MapTile[] tt : tiles){
            for(MapTile tile : tt){
                tile.setTileType(TileType.Floor);
            }
        }
        HashMap<String, Map> maps = new HashMap<String, Map>();
        maps.put("start", map);
        Game game = new Game(maps, map, new Random());
        IntroScreenModel i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.Start);
        assertEquals( IntroScreenModel.IntroScreenState.Start, i.getState() );
        i.loadFailed();
        assertEquals( IntroScreenModel.IntroScreenState.LoadFail, i.getState() );
    }
    @Test
    public void testIntroScreenModelSelectStart()
    {
        Map map = new Map(512, 512, "tileset-x2", 32);
        MapTile[][] tiles = map.getTiles();
        for(MapTile[] tt : tiles){
            for(MapTile tile : tt){
                tile.setTileType(TileType.Floor);
            }
        }
        HashMap<String, Map> maps = new HashMap<String, Map>();
        maps.put("start", map);
        Game game = new Game(maps, map, new Random());
        IntroScreenModel i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.Start);
        assertEquals( IntroScreenModel.IntroScreenState.Start, i.getState() );
        i.select();
        assertEquals( IntroScreenModel.IntroScreenState.Main, i.getState() );
    }
    @Test
    public void testIntroScreenModelSelectMain()
    {
        Map map = new Map(512, 512, "tileset-x2", 32);
        MapTile[][] tiles = map.getTiles();
        for(MapTile[] tt : tiles){
            for(MapTile tile : tt){
                tile.setTileType(TileType.Floor);
            }
        }
        HashMap<String, Map> maps = new HashMap<String, Map>();
        maps.put("start", map);
        Game game = new Game(maps, map, new Random());
        IntroScreenModel i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.Main);
        i.select();
        assertEquals( IntroScreenModel.IntroScreenState.NewGame, i.getState() );
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.Main);
        i.moveDown();
        i.select();
        assertEquals( IntroScreenModel.IntroScreenState.LoadGame, i.getState() );
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.Main);
        i.moveDown();
        i.moveDown();
        i.select();
        assertEquals( IntroScreenModel.IntroScreenState.Credits, i.getState() );
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.Main);
        i.moveDown();
        i.moveDown();
        i.moveDown();
        i.select();
        assertEquals( IntroScreenModel.IntroScreenState.Quit, i.getState() );
    }
    @Test
    public void testIntroScreenModelSelectNewGame()
    {
        Map map = new Map(512, 512, "tileset-x2", 32);
        MapTile[][] tiles = map.getTiles();
        for(MapTile[] tt : tiles){
            for(MapTile tile : tt){
                tile.setTileType(TileType.Floor);
            }
        }
        HashMap<String, Map> maps = new HashMap<String, Map>();
        maps.put("start", map);
        Game game = new Game(maps, map, new Random());
        IntroScreenModel i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.NewGame);
        assertEquals( IntroScreenModel.IntroScreenState.NewGame, i.getState() );
        i.select();
        assertEquals( IntroScreenModel.IntroScreenState.StartNewGame, i.getState() );
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.NewGame);
        assertEquals( IntroScreenModel.IntroScreenState.NewGame, i.getState() );
        i.moveDown();
        i.select();
        assertEquals( IntroScreenModel.IntroScreenState.StartNewGame, i.getState() );
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.NewGame);
        assertEquals( IntroScreenModel.IntroScreenState.NewGame, i.getState() );
        i.moveDown();
        i.moveDown();
        i.select();
        assertEquals( IntroScreenModel.IntroScreenState.StartNewGame, i.getState() );
        i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.NewGame);
        assertEquals( IntroScreenModel.IntroScreenState.NewGame, i.getState() );
        i.moveDown();
        i.moveDown();
        i.moveDown();
        i.select();
        assertEquals( IntroScreenModel.IntroScreenState.Main, i.getState() );
    }
    @Test
    public void testIntroScreenModelSelectLoadFail()
    {
        Map map = new Map(512, 512, "tileset-x2", 32);
        MapTile[][] tiles = map.getTiles();
        for(MapTile[] tt : tiles){
            for(MapTile tile : tt){
                tile.setTileType(TileType.Floor);
            }
        }
        HashMap<String, Map> maps = new HashMap<String, Map>();
        maps.put("start", map);
        Game game = new Game(maps, map, new Random());
        IntroScreenModel i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.LoadFail);
        assertEquals( IntroScreenModel.IntroScreenState.LoadFail, i.getState() );
        i.select();
        assertEquals( IntroScreenModel.IntroScreenState.Main, i.getState() );
    }
    @Test
    public void testIntroScreenModelSelectCredits()
    {
        Map map = new Map(512, 512, "tileset-x2", 32);
        MapTile[][] tiles = map.getTiles();
        for(MapTile[] tt : tiles){
            for(MapTile tile : tt){
                tile.setTileType(TileType.Floor);
            }
        }
        HashMap<String, Map> maps = new HashMap<String, Map>();
        maps.put("start", map);
        Game game = new Game(maps, map, new Random());
        IntroScreenModel i = new IntroScreenModel(game, IntroScreenModel.IntroScreenState.Credits);
        assertEquals( IntroScreenModel.IntroScreenState.Credits, i.getState() );
        i.select();
        assertEquals( IntroScreenModel.IntroScreenState.Main, i.getState() );
    }
}