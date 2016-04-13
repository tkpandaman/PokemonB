package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Observable;

public class Game extends Observable {
	
	private Map map;
	private int playerX = 0;
	private int playerY = 0;
	
	public Game(){
		
		//Load map
		File mapFile = new File("levels/emerald-test");
		try{
			FileInputStream fileIn = new FileInputStream(mapFile);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			//read objects from saved data
			map = (Map) in.readObject();
			
			in.close();
			fileIn.close();
			setChanged();
			notifyObservers();
		} catch(Exception ee){
		}
		
	}
	
	// Returns the current map.
	public Map getMap(){
		return map;
	}
	
}
