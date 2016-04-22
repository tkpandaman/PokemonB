package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Random;

public class Game extends Observable implements Serializable {

    private static final long serialVersionUID = -1241442352734346332L;
    private HashMap<String, Map> maps;
    private Map map;
	private Trainer trainer;
	private int playerX;
	private int playerY;

	private State state = State.NORMAL;
	
	private Battle battle;
	private BattleMenu battleMenu;
	public boolean isTransition;

	public Game(HashMap<String, Map> maps, Map startMap){
		
		battleMenu = new BattleMenu();
		
		//Load map
		this.maps = maps;
		this.map = startMap; //This should probably change later (1 = emerald, 0 = viridian)
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
	private void transitionToMap(int x, int y){
		Map nextMap = null;
		if (x > 0) nextMap = maps.get(map.getRightMap());
		if (x < 0) nextMap = maps.get(map.getLeftMap());
		if (y > 0) nextMap = maps.get(map.getDownMap());
		if (y < 0) nextMap = maps.get(map.getUpMap());
		if (nextMap != null) {
			map = nextMap;
			if (x<0) playerX = map.getWidth();
			if (x>0) playerX = 0;
			if (y<0) playerY = map.getHeight();
			if (y>0) playerY = 0;
			isTransition = true;
			update();
		}
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
				transitionToMap(-1, 0);
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
			} else {
				transitionToMap(1, 0);
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
				transitionToMap(0, -1);
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
			} else {
				transitionToMap(0, 1);
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
