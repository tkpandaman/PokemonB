
package editor_controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;
import java.util.Random;

import model.Item;
import model.Map;
import model.MapTile;
import model.Pokeball;
import model.TileType;
import model.Tileset;

// The model class for the level editor.
// Holds a Map and is responsible for changing the tiles on it.
public class LevelEditor extends Observable{
	private int width = 50;
	private int height = 50;
	
	private Map map;
	
	private Tileset currentTileset;
	private int tileSize;
	
	private Item currentItem;
	
	public String cursor;
	public boolean copy;
	
	MapTile currentTile = new MapTile(0,0);
	
	public enum EditorView{Normal, Collisions, TileType};
	private EditorView view = EditorView.Normal;
	
	public enum UpdateType{TilesetChanged};
	
	public LevelEditor(String tileset, int tileSize){
		setTileset(tileset, tileSize);
		
		cursor = "Tiles"; //Default cursor
		currentItem = new Pokeball(new Random()); //Default Item
		copy = false; //Default copy state
		
		map = new Map(width, height, tileset, tileSize);
		setTileImage(0, 0);
	}
	
	public void update(){
		setChanged();
		notifyObservers();
	}
	
	// Gets the current Tileset.
	public Tileset getTileset(){
		return currentTileset;
	}
	
	// Returns the item currently selected by radio buttons
	public Item getCurrentItem(){
		return currentItem;
	}
	
	// Sets the currentItem to the one selected by buttons
	public void setCurrentItem(Item m){
		currentItem = m;
	}
	
	// Changes the current Tileset.
	public void setTileset(String tileset, int size){
		tileSize = size;
		currentTileset = new Tileset(tileset, size);
		setChanged();
		notifyObservers(UpdateType.TilesetChanged);
	}
	
	// Changes the tile at x, y on the map to the current tile.
	public void drawTile(int x, int y){
		map.setTile(new MapTile(currentTile), x, y); //copy currentTile to map
		setChanged();
		notifyObservers();
	}
	
	// Gets the tile-to-be-drawn.
	public MapTile getCurrentTile(){
		return currentTile;
	}
	
	// Sets the current tile image to the tile at coordinates x, y in the tileset.
	public void setTileImage(int x, int y){
		currentTile.setImage(x, y);
		//get default TileType for x, y
		currentTile.setTileType(currentTileset.getDefaultType(x, y));
		setChanged();
		notifyObservers();
	}
	
	// Sets the current tile to m
		public void setTile(MapTile m){
			currentTile = m;
			setChanged();
			notifyObservers();
		}
	
	// Sets the current tile type.
	public void setTileType(TileType type){
		currentTile.setTileType(type);
		//make this the new default TileType for this tile
		currentTileset.setDefaultType(currentTile.getTilesetX(), currentTile.getTilesetY(), type);
	}
	
	// Gets the Map object being edited.
	public Map getMap(){
		return map;
	}
	
	// Gets the width of the map (in tiles).
	public int getMapWidth(){
		return width;
	}
	
	// Gets the height of the map (in tiles).
	public int getMapHeight(){
		return height;
	}
	
	// Gets the width of the map (in pixels).
	public int getMapPixelWidth(){
		return width*tileSize;
	}
	
	// Gets the height of the map (in pixels).
	public int getMapPixelHeight(){
		return height*tileSize;
	}
	
	// Returns the tile size.
	public int getTileSize(){
		return tileSize;
	}
	
	// Returns the current EditorView (normal, show collisions, or show tile type)
	public EditorView getEditorView(){
		return view;
	}
	
	// Sets the current EditorView.
	public void setEditorView(EditorView view){
		this.view = view;
		setChanged();
		notifyObservers();
	}
	
	// Creates a blank map.
	public void newLevel(){
		map = new Map(width, height, currentTileset.getName(), tileSize);
		setChanged();
		notifyObservers();
	}
	
	// Saves the map to a file.
	public void saveLevel(File file){
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			//write objects to saved data
			out.writeObject(map);
			fileOut.close();
			fileOut.flush();
		} catch (IOException e1) {
		}
	}
	
	// Loads a map from a file.
	public void loadLevel(File file){
		try{
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			//read objects from saved data
			map = (Map) in.readObject();
			this.setTileset(map.getTileset(), map.getTileSize());
			
			in.close();
			fileIn.close();
			setChanged();
			notifyObservers();
		} catch(Exception ee){
		}
	}
	
}
