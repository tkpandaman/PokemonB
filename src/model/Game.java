package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

public class Game extends Observable implements Serializable {

    private static final long serialVersionUID = -1241442352734346332L;
    private ArrayList<Map> maps;
    private Map map;
	private Trainer trainer;
	private int playerX;
	private int playerY;

	private State state = State.NORMAL;
	
	private Battle battle;
	private BattleMenu battleMenu;
	public boolean isTransition;

	public Game(ArrayList<Map> maps){
		
		battleMenu = new BattleMenu();
		
		//Load map
		this.maps = maps;
		this.map = maps.get(0); //This should probably change later (1 = emerald, 0 = viridian)
		trainer = new Trainer("Sir Dumplestein");
		playerX = 2;
		playerY = 2;
		isTransition = false;
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
	
	//Need to improve this later
	private void transitionToMap(){
		if (map.equals(maps.get(0))){
			map = maps.get(1);
		}
		else map = maps.get(0);
		isTransition = true;
		update();
	}
	
	public void checkForPokemon(Random r){
		MapTile t = map.tileAt(playerX, playerY);
		int chance = t.getRandomEncounterChance();
		
		boolean isPokemon = r.nextInt(chance) == 0;
		
		if(isPokemon){
			state = State.BATTLE;
			battle = new Battle(trainer);
			battleMenu.startBattle(battle);
		}
	}
	
	public Battle getBattle(){
		return battle;
	}

	public void moveLeft(){
		if (state == State.NORMAL){
			if (playerX > 0){
				if (!map.tileAt(playerX-1, playerY).isSolid()){
					takeStep(-1, 0);
				}
			}
			else{
				transitionToMap();
			}
		}
		if( trainer.openPack().getPokeballsLeft() == 0 )
        {
            state = State.WIN;
        }
		if (state == State.BATTLE){
			battleMenu.left();
		}
	}

	public void moveRight(){
		if (state == State.NORMAL){
			if (playerX < map.getWidth()-1){
				if (!map.tileAt(playerX+1, playerY).isSolid()){
					takeStep(1, 0);
				}
			}
		}
		if( trainer.openPack().getPokeballsLeft() == 0 )
        {
            state = State.WIN;
        }
		if (state == State.BATTLE){
			battleMenu.right();
		}
	}

	public void moveUp(){
		if (state == State.NORMAL){
			if (playerY > 0){
				if (!map.tileAt(playerX, playerY-1).isSolid()){
					takeStep(0, -1);
				}
			}
			else{
				transitionToMap();
			}
		}
		if( trainer.openPack().getPokeballsLeft() == 0 )
        {
            state = State.WIN;
        }
		if (state == State.BATTLE){
			battleMenu.up();
		}
		
	}

	public void moveDown(){
		if (state == State.NORMAL){
			if (playerY < map.getHeight()-1){
				if (!map.tileAt(playerX, playerY+1).isSolid()){
					takeStep(0, 1);
				}
			}
		}
		if( trainer.openPack().getPokeballsLeft() == 0 )
		{
		    state = State.WIN;
		}
		if (state == State.BATTLE){
			battleMenu.down();
		}
	}
	
	public void select(){
		if (state == State.BATTLE){
			battleMenu.select();
		}
		
		if (battleMenu.battleOver()){
			state = State.NORMAL;
		}
	}

	public int getPlayerX(){
		return playerX;
	}

	public int getPlayerY(){
		return playerY;
	}
	
	public void setPlayerPos(int x, int y){
		this.playerX = x;
		this.playerY = y;
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
