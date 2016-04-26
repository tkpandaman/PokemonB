package model;

import java.io.Serializable;
import java.util.ArrayList;

// A Pokemon map comprised of a 2D array of MapTiles, plus some additional data like width,
// height, and the names of the adjacent maps.
public class Map implements Serializable {
	private static final long serialVersionUID = -3257022862288829091L;

	private MapTile[][] tile;  //array of Tile objects which comprise the map
	private int width, height; //width and height of the map (in tiles)

	//public static enum names{none,default_map,emerald_test,viridian_forest}; //Add new maps here

	private String tileset;
	private int tileSize;
	private String curMap, leftMap, rightMap, upMap, downMap; //what maps are adjacent to this one?
	private ArrayList<MapItem> mapItems;



	public Map(int width, int height, String tileset, int tileSize){
		this.width = width; this.height = height;
		this.tileset = tileset;
		this.tileSize = tileSize;
		tile = new MapTile[width][height];
		mapItems = new ArrayList<MapItem>();

		//populate the map with default grass tiles
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				if (tileset.equals("tileset-x2")) tile[i][j] = new MapTile(3, 0, TileType.Grass);
				else if (tileset.equals("emerald-x2")) tile[i][j] = new MapTile(1, 0, TileType.Grass);
				else tile[i][j] = new MapTile(0, 0, TileType.Grass);
			}
		}
	}
	
	// Returns the MapItems associated with this Map
	public ArrayList<MapItem> getMapItems(){
		return mapItems;
	}
	
	public MapItem popMapItemAt(int x, int y){
		for (MapItem m : mapItems){
			if (m.getX() == x && m.getY() == y){
				mapItems.remove(m);
				return m;
			}
		}
		return null;
	}
	
	// Add a MapItem to the list
	public void addMapItem(MapItem m){
		for (MapItem mItem : mapItems){
			if(mItem.getX() == m.getX() && mItem.getY() == m.getY()) return; //probably should have used a Set
		}
		mapItems.add(m);
	}

	// Sets the tile at x, y to the tile provided.
	public void setTile(MapTile t, int x, int y){
		tile[x][y] = t;
	}

	// Get the array of Tile objects stored by the map.
	public MapTile[][] getTiles(){
		return tile;
	}

	// Gets a tile from the array of Tile objects stored by the map.
	public MapTile tileAt(int x, int y){
		return tile[x][y];
	}

	// Gets the width of the map (in tiles).
	public int getWidth(){
		return width;
	}

	// Gets the height of the map (in tiles).
	public int getHeight(){
		return height;
	}

	// Returns the name of the map to the left of this one.
	public String getCurMap(){
		return curMap;
	}

	// Sets the name of the map to the left of this one.
	public void setCurMap(String name){
		curMap = name;
	}

	// Returns the name of the map to the left of this one.
	public String getLeftMap(){
		return leftMap;
	}

	// Sets the name of the map to the left of this one.
	public void setLeftMap(String name){
		leftMap = name;
	}

	// Returns the name of the map to the right of this one.
	public String getRightMap(){
		return rightMap;
	}

	// Sets the name of the map to the right of this one.
	public void setRightMap(String name){
		rightMap = name;
	}

	// Returns the name of the map above this one.
	public String getUpMap(){
		return upMap;
	}

	// Sets the name of the map above this one.
	public void setUpMap(String name){
		upMap = name;
	}

	// Returns the name of the map below this one.
	public String getDownMap(){
		return downMap;
	}

	// Sets the name of the map below this one.
	public void setDownMap(String name){
		downMap = name;
	}

	// Returns the name of this map's tileset.
	public String getTileset() {
		return tileset;
	}

	// Returns the size (in pixels) of each tile in this map.
	public int getTileSize(){
		return tileSize;
	}

}
