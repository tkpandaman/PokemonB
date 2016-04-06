package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.imageio.ImageIO;

// A class which represents a tileset, dividing up a large image into discrete tiles
// and maintaining a list of default TileTypes for each one.
public class Tileset {
	static Tileset instance = null;
	
	private final String filename;
	private int width, height;
	
	private BufferedImage[][] tile;
	private final int tileSize;
	
	HashMap<String, TileType> defaults = new HashMap<String, TileType>();
	
	@SuppressWarnings("unchecked")
	public Tileset(String filename, int tileSize){
		this.filename = filename;
		this.tileSize = tileSize;
		
		//load tileset image
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("images/" + filename + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		width = image.getWidth();
		height = image.getHeight();
		
		tile = new BufferedImage[width/tileSize][height/tileSize];
		
		//populate tile array with 32x32 tile images
		for(int y=0; y<height/tileSize; y++){
			for(int x=0; x<width/tileSize; x++){
				tile[x][y] = image.getSubimage(x*tileSize, y*tileSize, tileSize, tileSize);
			}
		}
		
		//load tile defaults
		try {
			FileInputStream fileIn = new FileInputStream("images/" + filename + "-defaults");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			defaults = (HashMap<String, TileType>) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException e) {
			//file not found; create default defaults
			for(int y=0; y<height/tileSize; y++){
				for(int x=0; x<width/tileSize; x++){
					defaults.put(x+","+y, TileType.Wall);
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	// Returns the filename of the tileset.
	public String getName(){
		return filename;
	}
	
	public int getTileSize(){
		return tileSize;
	}
	
	// Returns the width of the tileset (in pixels).
	public int getWidth(){
		return width;
	}
	
	// Returns the height of the tileset (in pixels).
	public int getHeight(){
		return height;
	}
	
	// Returns the tile at index x, y in the tileset.
	public BufferedImage tileAt(int x, int y){
		return tile[x][y];
	}
	
	// Returns the default TileType for the tile at x, y in the tileset.
	public TileType getDefaultType(int x, int y){
		return defaults.get(x+","+y);
	}
	
	// Sets the default TileType for the tile at x, y in the tileset.
	public void setDefaultType(int x, int y, TileType type){
		defaults.put(x+","+y, type);
	}
	
	public void saveTileDefaults(){
		try {
			FileOutputStream fileOut = new FileOutputStream("images/" + filename + "-defaults");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			//write objects to saved data
			out.writeObject(defaults);
			fileOut.close();
			fileOut.flush();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
}
