package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Map;
import model.MapTile;
import model.TileType;

public class MapTest {

	@Test
	public void test() {
		Map map = new Map(10,50,"tileset-x2",32);
		assertTrue(map.getTileset().equals("tileset-x2"));
		assertEquals(map.getTileSize(), 32);
		
		assertEquals(map.getWidth(), 10);
		assertEquals(map.getHeight(), 50);
		
		MapTile[][] tiles = map.getTiles();
		assertEquals(tiles.length, map.getWidth());
		assertEquals(tiles[0].length, map.getHeight());
		for(int i=0; i<tiles.length; i++){
			for(int j=0; j<tiles.length; j++){
				//all tiles in map should be initialized
				assertNotEquals(tiles[i][j], null);
				assertEquals(tiles[i][j], map.tileAt(i, j));
				MapTile t = new MapTile(1,1,TileType.Wall);
				map.setTile(t, i, j);
				assertEquals(map.tileAt(i, j), t);
			}
		}
		
		map.setLeftMap("lolpwn");
		assertTrue(map.getLeftMap().compareTo("lolpwn") == 0);
		map.setRightMap("");
		assertTrue(map.getRightMap().compareTo("") == 0);
		map.setUpMap(".............................................");
		assertTrue(map.getUpMap().compareTo(".............................................") == 0);
		map.setDownMap("map");
		assertTrue(map.getDownMap().compareTo("map") == 0);
		
		map = new Map(1000,5,"emerald-x2",32);
		assertTrue(map.getTileset().equals("emerald-x2"));
		assertEquals(map.getTileSize(), 32);
		
		map = new Map(1,5000,"tileset",16);
		assertTrue(map.getTileset().equals("tileset"));
		assertEquals(map.getTileSize(), 16);
		
	}

}
