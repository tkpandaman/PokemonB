package tests;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import editor_controller.LevelEditor;
import editor_controller.LevelEditor.EditorView;
import model.Map;
import model.TileType;
import model.Tileset;

public class LevelEditorTest {

	@Test
	public void test() {
		
		File levelFile = new File("levels/test");
		levelFile.delete();
		
		LevelEditor editor = new LevelEditor("test", 32);
		assertEquals(editor.getTileSize(), 32);
		
		Tileset tileset = editor.getTileset();
		assertTrue(tileset.getName().equals("test"));
		assertEquals(tileset.getTileSize(), 32);
		
		Map map = editor.getMap();
		assertEquals(map.getWidth(), editor.getMapWidth());
		assertEquals(map.getHeight(), editor.getMapHeight());
		assertEquals(editor.getMapPixelWidth(), map.getWidth()*map.getTileSize());
		assertEquals(editor.getMapPixelHeight(), map.getHeight()*map.getTileSize());
		
		editor.setEditorView(EditorView.Collisions);
		assertEquals(editor.getEditorView(), EditorView.Collisions);
		editor.setEditorView(EditorView.TileType);
		assertEquals(editor.getEditorView(), EditorView.TileType);
		editor.setEditorView(EditorView.Normal);
		assertEquals(editor.getEditorView(), EditorView.Normal);
		
		
		editor.setTileType(TileType.Grass);
		assertEquals(editor.getCurrentTile().getTileType(), TileType.Grass);
		editor.setTileType(TileType.Wall);
		assertEquals(editor.getCurrentTile().getTileType(), TileType.Wall);
		editor.setTileImage(1, 2);
		
		editor.drawTile(10, 10);
		assertEquals(map.tileAt(10, 10).getTilesetX(), 1);
		assertEquals(map.tileAt(10, 10).getTilesetY(), 2);
		assertEquals(map.tileAt(10, 10).getTileType(), TileType.Wall);
		
		editor.newLevel();
		map = editor.getMap();
		assertEquals(map.tileAt(10, 10).getTilesetX(), 0);
		assertEquals(map.tileAt(10, 10).getTilesetY(), 0);
		
		editor.setTileImage(1, 2);
		editor.setTileType(TileType.Wall);
		editor.drawTile(10, 10);
		
		editor.saveLevel(levelFile);
		
		editor.newLevel();
		
		editor.loadLevel(levelFile);
		map = editor.getMap();
		assertEquals(map.tileAt(10, 10).getTilesetX(), 1);
		assertEquals(map.tileAt(10, 10).getTilesetY(), 2);
		assertEquals(map.tileAt(10, 10).getTileType(), TileType.Wall);
		
		levelFile.delete();
		
		File nonexistentFile = new File("/levels/tes");
		editor.saveLevel(nonexistentFile);
		editor.loadLevel(nonexistentFile);
		
	}

}
