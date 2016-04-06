package test;

import static org.junit.Assert.*;

import org.junit.Test;

import model.MapTile;
import model.TileType;
import model.Tileset;

public class TileTest {

	@Test
	public void test() {
		MapTile floorTile = new MapTile(0,0, TileType.Floor);
		TileType floorType = floorTile.getTileType();
		assertEquals(floorType, TileType.Floor);
		assertEquals(floorTile.isSolid(), floorType.isSolid());
		assertEquals(floorTile.getRandomEncounterChance(), floorType.getRandEncounterChance(), 0.01);
		
		MapTile wallTile = new MapTile(5,0);
		TileType wallType = wallTile.getTileType();
		assertEquals(wallType, TileType.Wall);
		assertEquals(wallTile.isSolid(), wallType.isSolid());
		assertEquals(wallTile.getRandomEncounterChance(), wallType.getRandEncounterChance(), 0.01);
	}
	
	@Test
	public void testTileType(){
		for (TileType t : TileType.values()){
			//each TileType has a name
			assertFalse(t.toString() == null);
			assertFalse(t.toString().compareTo("") == 0);
			//each TileType has an abbreviation
			assertFalse(t.getAbbreviation() == null);
			assertFalse(t.getAbbreviation().compareTo("") == 0);
		}
	}
	
	@Test
	public void testCopyConstructor(){
		MapTile tile = new MapTile(1,1);
		MapTile tile2 = new MapTile(tile);
		assertNotEquals(tile, tile2);
		assertEquals(tile.getTileType(), tile2.getTileType());
		assertEquals(tile.getTilesetX(), tile2.getTilesetX());
		assertEquals(tile.getTilesetY(), tile2.getTilesetY());
		assertEquals(tile.isSolid(), tile2.isSolid());
	}
	
	@Test
	public void testSetters(){
		MapTile tile = new MapTile(0,5,TileType.LongGrass);
		assertTrue(tile.getTilesetX() == 0);
		assertTrue(tile.getTilesetY() == 5);
		assertEquals(tile.getTileType(), TileType.LongGrass);
		assertFalse(tile.isSolid());
		tile.setImage(5, 0);
		assertTrue(tile.getTilesetX() == 5);
		assertTrue(tile.getTilesetY() == 0);
		tile.setTileType(TileType.Wall);
		assertEquals(tile.getTileType(), TileType.Wall);
		assertTrue(tile.isSolid());
	}

}
