package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;

// The model class for the level editor.
// Holds a Map and is responsible for changing the tiles on it.
public class LevelEditor extends Observable{

	private int width = 50;
	private int height = 50;
	
	private Map map;
	
	private int[] tileImage = new int[2]; //coordinates of the current tile image in the tileset.
	MapTile currentTile = new MapTile(0,0);
	
	public enum EditorView{Normal, Collisions, TileType};
	private EditorView view = EditorView.Normal;
	
	public LevelEditor(){
		map = new Map(width, height);
		setTileImage(0, 0);
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
		currentTile.setTileType(Tileset.getInstance().getDefaultType(x, y));
		setChanged();
		notifyObservers();
	}
	
	// Sets the current tile type.
	public void setTileType(TileType type){
		currentTile.setTileType(type);
		//make this the new default TileType for this tile
		Tileset.getInstance().setDefaultType(currentTile.getTilesetX(), currentTile.getTilesetY(), type);
	}
	
	// Returns the current tile image coordinates
	public int[] getTileImage(){
		return tileImage;
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
		return width*Tileset.tileSize;
	}
	
	// Gets the height of the map (in pixels).
	public int getMapPixelHeight(){
		return height*Tileset.tileSize;
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
		map = new Map(width, height);
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
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	// Loads a map from a file.
	public void loadLevel(File file){
		try{
			FileInputStream fileIn = new FileInputStream(file);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			//read objects from saved data
			map = (Map) in.readObject();
			
			in.close();
			fileIn.close();
			setChanged();
			notifyObservers();
		} catch(Exception ee){
			System.out.println("Couldn't load file!");
		}
	}
	
}
