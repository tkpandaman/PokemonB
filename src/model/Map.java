package model;

import java.io.Serializable;

// A Pokemon map comprised of a 2D array of MapTiles, plus some additional data like width,
// height, and the names of the adjacent maps.
public class Map implements Serializable {
	private static final long serialVersionUID = -3257022862288829091L;
	
	private MapTile[][] tile;  //array of Tile objects which comprise the map
	private int width, height; //width and height of the map (in tiles)
	
	private String leftMap, rightMap, upMap, downMap; //what maps are adjacent to this one?

	public Map(int width, int height){
		this.width = width; this.height = height;
		tile = new MapTile[width][height];
		
		//populate the map with default grass tiles
		for(int i=0; i<width; i++){
			for(int j=0; j<height; j++){
				if (Tileset.getInstance().getName().equals("tileset-x2")) tile[i][j] = new MapTile(3, 0, TileType.Grass);
				else if (Tileset.getInstance().getName().equals("emerald-x2")) tile[i][j] = new MapTile(1, 0, TileType.Grass);
				else tile[i][j] = new MapTile(0, 0, TileType.Grass);
			}
		}
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
	public String getLeftMap(){
		return leftMap;
	}
	
	// Returns the name of the map to the right of this one.
	public String getRightMap(){
		return rightMap;
	}
	
	// Returns the name of the map above this one.
	public String getUpMap(){
		return upMap;
	}
	
	// Returns the name of the map below this one.
	public String getDownMap(){
		return downMap;
	}
	
}
