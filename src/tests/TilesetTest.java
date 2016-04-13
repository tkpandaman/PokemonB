package tests;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.imageio.IIOException;

import org.junit.Test;

import model.TileType;
import model.Tileset;

public class TilesetTest {

	@Test
	public void test() {
		File defaults = new File("images/test-defaults");
		defaults.delete();
		
		Tileset tileset = new Tileset("test", 32);
		
		assertTrue(tileset.getName().equals("test"));
		assertEquals(tileset.getTileSize(), 32);
		assertEquals(tileset.getWidth(), 96);
		assertEquals(tileset.getHeight(), 128);
		
		BufferedImage image = tileset.tileAt(0, 0);
		
		for(int x=0; x<tileset.getWidth()/32; x++){
			for(int y=0; y<tileset.getHeight()/32; y++){
				assertEquals(tileset.getDefaultType(x, y), TileType.Wall);
			}
		}
		
		tileset.setDefaultType(0,0, TileType.LongGrass);
		assertEquals(tileset.getDefaultType(0, 0), TileType.LongGrass);
		tileset.setDefaultType(1,0, TileType.Floor);
		assertEquals(tileset.getDefaultType(1, 0), TileType.Floor);
		
		tileset.saveTileDefaults();
		
		//load same tileset, check that defaults were loaded
		tileset = new Tileset("test", 32);
		assertEquals(tileset.getDefaultType(0, 0), TileType.LongGrass);
		assertEquals(tileset.getDefaultType(1, 0), TileType.Floor);
		
		//TODO: check that image is the same as above
		//assertTrue(image.equals(tileset.tileAt(0, 0)));

		assertTrue(defaults.delete());
	}
	
	@Test (expected = NullPointerException.class)
	public void loadNonexistentTileset(){
		Tileset tileset = new Tileset("something completely new and unexpected", 32);
	}

}
