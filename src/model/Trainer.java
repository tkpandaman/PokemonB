package model;

import java.io.Serializable;

public class Trainer implements Serializable {
	
	private Backpack bp;
	private String name;
	private int steps;
	
	
	public Trainer(String name){
		this.bp = new Backpack();
		this.name = name;
		this.steps = 500;
	}
	
	public int getStepsLeft(){
		return this.steps;
	}
	
	public void takeStep(){
		this.steps--;
	}
	
	public String getName(){
		return this.name;
	}

}
