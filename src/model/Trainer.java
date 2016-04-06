package model;

import java.io.Serializable;
import java.util.List;

/**
 * Trainer is in charge of attributes associated with the playable trainer character, 
 * including number of steps remaining, name, and a backpack containing Pokemon and Items.
 * 
 * @author AlexKatzfey
 *
 */
public class Trainer implements Serializable {
	
	private Backpack bp;
	private String name;
	private int steps;
	private double speed;
	
	/**
	 * Construct a new Trainer with 500 steps and given name.
	 * @param name as the Trainer's name
	 */
	public Trainer(String name){
		this.bp = new Backpack();
		this.name = name;
		this.steps = 500;
		this.speed = 1.0;
	}
	
	/**
	 * Returns steps remaining
	 * @return int as step count
	 */
	public int getStepsLeft(){
		return this.steps;
	}
	
	/**
	 * Decrements the number of available steps
	 */
	public void takeStep(){
		this.steps--;
	}
	
	/**
	 * Returns the name of the Trainer
	 * @return name String
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Get the current speed of the Trainer
	 * @return double as a scale of speed
	 */
	public double getSpeed(){
		return this.speed;
	}
	
	/**
	 * Set the current speed of the trainer
	 * @param speed as a double
	 */
	public void setSpeed(double speed){
		this.speed = speed;
	}
	
	/**
	 * Use a pokeball
	 * @return Pokeball removed from backpack
	 */
	public Pokeball usePokeball(){
		return bp.usePokeball();
	}
	
	/**
	 * Add a new Pokeball to the backpack
	 */
	public void addPokeball(){
		bp.addPokeball();
	}
	
	/**
	 * Get the amount of pokeballs remaining
	 * @return pokeball count
	 */
	public int getPokeballsLeft(){
		return bp.getPokeballsLeft();
	}
	

}
