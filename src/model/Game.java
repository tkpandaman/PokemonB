package model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Random;

public class Game extends Observable implements Serializable {

    private static final long serialVersionUID = -1241442352734346332L;
    private Map map;
	private Trainer trainer;
	private int playerX;
	private int playerY;

	private State state = State.NORMAL;
	
	private BattleMenu battleMenu;

	public Game(Map map){
		
		battleMenu = new BattleMenu();

		//Load map
		this.map = map;
		trainer = new Trainer("Sir Dumplestein");
		playerX = 11;
		playerY = 8;
		update();
		

	}
	
	//call to update observers
	public void update(){
		setChanged();
		notifyObservers();
	}


	// Returns the current map.
	public Map getMap(){
		return map;
	}

	public void takeStep(int x, int y){
		if (state == State.NORMAL){
			playerX += x;
			playerY += y;
			trainer.takeStep();
			if (trainer.getStepsLeft() <= 0) state = State.WIN;
            checkForPokemon(new Random());
			update();
		}
	}
	
	public void checkForPokemon(Random r){
		MapTile t = map.tileAt(playerX, playerY);
		int chance = t.getRandomEncounterChance();
		
		boolean isPokemon = r.nextInt(chance) == 0;
		
		if(isPokemon){
			state = State.BATTLE;
		}
	}
	

	public void moveLeft(){
		if (playerX > 0){
			if (!map.tileAt(playerX-1, playerY).isSolid()){
				takeStep(-1, 0);
			}
		}
	}

	public void moveRight(){
		if (playerX < map.getWidth()-1){
			if (!map.tileAt(playerX+1, playerY).isSolid()){
				takeStep(1, 0);
			}
		}
	}

	public void moveUp(){
		if (playerY > 0){
			if (!map.tileAt(playerX, playerY-1).isSolid()){
				takeStep(0, -1);
			}
		}
	}

	public void moveDown(){
		if (playerY < map.getHeight()-1){
			if (!map.tileAt(playerX, playerY+1).isSolid()){
				takeStep(0, 1);
			}
		}
	}

	public int getPlayerX(){
		return playerX;
	}

	public int getPlayerY(){
		return playerY;
	}

	public Trainer getTrainer(){
		return trainer;
	}

	public State getState(){
		return state;
	}
	
	public BattleMenu getBattleMenu(){
		return battleMenu;
	}

}
