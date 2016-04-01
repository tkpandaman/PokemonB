package model;

import java.awt.image.BufferedImage;
import java.io.Serializable;

// A tile in a Pokemon map.
public class MapTile implements Serializable {
	private static final long serialVersionUID = -361000834109237389L;

	private int tilesetX, tilesetY;
	
	private TileType tileType;

	public MapTile(int x, int y) {
		this(x, y, TileType.Wall);
	}
	
	public MapTile(int x, int y, TileType type) {
		tilesetX = x; tilesetY = y;
		tileType = type;
	}
	
	// Copy constructor: returns a copy of the provided MapTile.
	public MapTile(MapTile other){
		this(other.tilesetX, other.tilesetY, other.getTileType());
	}
	
	// Returns the TileType of this Tile.
	public TileType getTileType(){
		return tileType;
	}
	
	// Sets the TileType of this Tile.
	public void setTileType(TileType type){
		tileType = type;
	}
	
	// Sets the tile's image.
	public void setImage(int x, int y){
		tilesetX = x; tilesetY = y;
	}
	
	// Get this tile's image from the Tileset.
	public BufferedImage getImage(){
		return Tileset.getInstance().tileAt(tilesetX, tilesetY);
	}
	
	// Gets the x-position of this tile in the Tileset.
	public int getTilesetX(){
		return tilesetX;
	}
	
	// Gets the y-position of this tile in the Tileset.
	public int getTilesetY(){
		return tilesetY;
	}
	
	// Returns whether this tile is solid.
	public boolean isSolid(){
		return tileType.isSolid();
	}
	
	// Returns the chance (out of 1) of encountering a Pokemon on this tile.
	public float getRandomEncounterChance(){
		return tileType.getRandEncounterChance();
	}
	
}
