package editor_controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import controller.GameGUI;
import model.Map;

public class ConvertMap {
	
	private File oldMapFile;
	private Map theNewMap;
	
	public static void main(String[] args){
		File file = null;
		for(File f : GameGUI.mapFiles()){
			if (f.getName().equals("emerald-test-2")){
				file = f;
			}
		}
		ConvertMap conv = new ConvertMap(file);
		conv.saveUpdatedMap("emerald-test");
	}
	
	public ConvertMap(File oldMap){
		this.oldMapFile = oldMap;
		this.theNewMap = convertTheMap();
	}
	
	public void saveUpdatedMap(String theNewName){
		this.theNewMap.setCurMap(theNewName);
		File file = new File("levels/" + theNewMap.getCurMap());
		try {
			FileOutputStream fileOut = new FileOutputStream(file);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			//write objects to saved data
			out.writeObject(theNewMap);
			fileOut.close();
			fileOut.flush();
		} catch (IOException e1) {
		}
	}
	
	private Map convertTheMap(){
		
		Map updatedMap = null;
		Object o = null;
		
		try{
			FileInputStream fileIn = new FileInputStream(this.oldMapFile);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			o = (Object) in.readObject();

			//read objects from saved data
			updatedMap = new Map( ((Map)o).getTiles().length, ((Map)o).getTiles()[0].length, ((Map)o).getTileset(), ((Map)o).getTileSize());

			in.close();
			fileIn.close();
		} catch(Exception ee){
			ee.printStackTrace();
			return null;
		}
		
		for (int i = 0; i < ((Map)o).getTiles().length; i++){
			for (int j = 0; j < ((Map)o).getTiles()[0].length; j++){
				updatedMap.setTile(((Map)o).getTiles()[i][j], i, j);
			}
		}
		
		updatedMap.setCurMap(((Map)o).getCurMap());
		updatedMap.setUpMap(((Map)o).getUpMap());
		updatedMap.setDownMap(((Map)o).getDownMap());
		updatedMap.setRightMap(((Map)o).getRightMap());
		updatedMap.setLeftMap(((Map)o).getLeftMap());
		
		return updatedMap;
		
	}

}
