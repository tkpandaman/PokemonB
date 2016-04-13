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
	private State state;

	//Constructor with default states of variables
	private Gamestate(){
		setTrainerX(0);
		setTrainerY(0);
		setTrainer(new Trainer("Ash"));
		setCurrentMap(null);
		setState(State.NORMAL);
	}

	/**
	 * Gets the single instance of Gamestate.
	 *
	 * @return single instance of Gamestate
	 */
	public synchronized static Gamestate getInstance(){
		if(instance == null) instance = new Gamestate();	
		return instance;
	}

	/**
	 * Sets the instance.
	 *
	 * @param game the new instance
	 */
	public static void setInstance(Gamestate game){
		instance = game;
	}

	/**
	 * Gets the trainer x.
	 *
	 * @return the trainer x
	 */
	public int getTrainerX() {
		return trainerX;
	}

	/**
	 * Sets the trainer x.
	 *
	 * @param trainerX the new trainer x
	 */
	public void setTrainerX(int trainerX) {
		this.trainerX = trainerX;
	}

	/**
	 * Gets the trainer y.
	 *
	 * @return the trainer y
	 */
	public int getTrainerY() {
		return trainerY;
	}

	/**
	 * Sets the trainer y.
	 *
	 * @param trainerY the new trainer y
	 */
	public void setTrainerY(int trainerY) {
		this.trainerY = trainerY;
	}

	/**
	 * Gets the trainer.
	 *
	 * @return the trainer
	 */
	public Trainer getTrainer() {
		return trainer;
	}

	/**
	 * Sets the trainer.
	 *
	 * @param trainer the new trainer
	 */
	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	/**
	 * Gets the current map.
	 *
	 * @return the current map
	 */
	public Map getCurrentMap() {
		return currentMap;
	}

	/**
	 * Sets the current map.
	 *
	 * @param currentMap the new current map
	 */
	public void setCurrentMap(Map currentMap) {
		this.currentMap = currentMap;
	}

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
	public State getState() {
		return state;
	}

	/**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
	public void setState(State state) {
		this.state = state;
	}

}
