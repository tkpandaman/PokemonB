package model;

import java.io.Serializable;

/**
 * Gamestate is in charge of storing information that needs to be saved to and loaded from a file.
 * @author AlexKatzfey
 *
 */
public class Gamestate implements Serializable{

	private static Gamestate instance;
	private int trainerX, trainerY;
	private Trainer trainer;
	private Map currentMap;

	private Gamestate(){
		setTrainerX(0);
		setTrainerY(0);
		setTrainer(new Trainer("Ash"));
		setCurrentMap(null);
	}

	public static Gamestate getInstance(){
		if(instance == null) instance = new Gamestate();	
		return instance;
	}

	public static void setInstance(Gamestate game){
		instance = game;
	}

	public int getTrainerX() {
		return trainerX;
	}

	public void setTrainerX(int trainerX) {
		this.trainerX = trainerX;
	}

	public int getTrainerY() {
		return trainerY;
	}

	public void setTrainerY(int trainerY) {
		this.trainerY = trainerY;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public Map getCurrentMap() {
		return currentMap;
	}

	public void setCurrentMap(Map currentMap) {
		this.currentMap = currentMap;
	}

}
