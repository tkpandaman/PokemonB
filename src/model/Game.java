package model;

import java.io.Serializable;
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
		this.map = startMap;
		Backpack bp = new Backpack();
		trainer = new Trainer("Ash Ketchup", bp);
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
	
	private void transitionToMap(String name){
		/*if (name.equals(Map.names.default_map)){
			map = maps.get(0); //set to default map
		}
		else if (name.equals(Map.names.emerald_test)){
			map = maps.get(1); //set to emerald test
		}
		else if (name.equals(Map.names.viridian_forest)){
			map = maps.get(2); //set to viridian forest
		}
		isTransition = true;
		update();*/

		Map nextMap = maps.get(name);
		this.map = nextMap;
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
			else if(map.getLeftMap() != null){ //change this to null?
				transitionToMap(map.getLeftMap()); //need to see which map is left of this one
				playerX = map.getWidth()-1;
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
			else if(map.getRightMap() != null){
				transitionToMap(map.getRightMap()); //need to see which map is right of this one
				playerX = 0;
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
			else if(map.getUpMap() != null){
				transitionToMap(map.getUpMap()); //need to see which map is up of this one
				playerY = map.getHeight()-1;
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
			else if(map.getDownMap() != null){
				transitionToMap(map.getDownMap()); //need to see which map is down of this one
				playerY = 0;
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
	public void chooseMenu()
	{
		if( state ==State.NORMAL ) {
			state = State.MENU;
			return;
		}
		if( state ==State.MENU ) {
			state = State.NORMAL;
			return;
		}
	}
	
}
