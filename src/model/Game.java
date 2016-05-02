package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Observable;
import java.util.Random;

import javax.swing.Timer;

import model.pokemon.KillMe;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;

public class Game extends Observable implements Serializable {

	private static final long serialVersionUID = -1241442352734346332L;
	private HashMap<String, Map> maps;
	private Map map;
	private Trainer trainer;
	private int playerX;
	private int playerY;
	private Random rand;

	private State state = State.NORMAL;

	private Battle battle;
	private BattleMenu battleMenu;
	public boolean isTransition;
	
	private float transitionAlpha;

	public Game(HashMap<String, Map> maps, Map startMap, Random r){

		battleMenu = new BattleMenu();

		//Load map
		this.maps = maps;
		this.map = startMap;
		Backpack bp = new Backpack(new Random());
		trainer = new Trainer("Ash Ketchup", bp);
		playerX = 2;
		playerY = 2;
		rand = r;
		transitionAlpha = 0;
		update();

	}

	//call to update observers
	public void update(){
		setChanged();
		notifyObservers();
	}

	//call to update observers
	public void update(int x){
		setChanged();
		notifyObservers(x);
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
			checkForPokemon(rand);
			checkForItem();
			update();
		}
	}

	private void transitionToMap(String name){
		Map nextMap = maps.get(name);
		this.map = nextMap;
		update(1);
	}

	private void checkForItem(){
		for(MapItem m : map.getMapItems()){
			if(playerX == m.getX() && playerY == m.getY()){
				if(m.getItem() instanceof Pokeball){
					map.popMapItemAt(playerX, playerY);
					trainer.openPack().addPokeball();
					return;
				}
				else if(m.getItem() instanceof TrainerItem){
					trainer.openPack().addTrainerItem((TrainerItem) map.popMapItemAt(playerX, playerY).getItem());
					return;
				}
				else{
					trainer.openPack().addPokemonItem((PokemonItem) map.popMapItemAt(playerX, playerY).getItem());
					return;
				}
			}
		}
	}
	
	private void checkGameState(){
		if( trainer.openPack().getPokeballsLeft() == 0 )
		{
			state = State.WIN;
		}
	}

	public void checkForPokemon(Random r){

		MapTile t = map.tileAt(playerX, playerY);
		int chance = t.getRandomEncounterChance();
		boolean isPokemon = r.nextInt(chance) == 0;

		if(isPokemon){
			state = State.FROZEN;
			Timer fadeOutTimer = new Timer(50, null);
			fadeOutTimer.addActionListener(new FadeOutListener());
			fadeOutTimer.start();
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
		if (state == State.BATTLE){
			battleMenu.left();
		}
		checkGameState();
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
		if (state == State.BATTLE){
			battleMenu.right();
		}
		checkGameState();
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
		if (state == State.BATTLE){
			battleMenu.up();
		}
		checkGameState();

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
		if (state == State.BATTLE){
			battleMenu.down();
		}
		checkGameState();
	}

	public void select(){
		if (battleMenu.battleOver()){
			state = State.NORMAL;
		}
		if (state == State.BATTLE){
			battleMenu.select();
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
		}
		else if( state ==State.MENU ) {
			state = State.NORMAL;
		}
	}
	
	public float getTransitionAlpha(){
		return transitionAlpha;
	}
	
	private class FadeOutListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			transitionAlpha += 5;
			if (transitionAlpha >= 255){
				transitionAlpha = 0;
				((Timer) e.getSource()).stop();
				state = State.BATTLE;
				Random rand = new Random();
				Pokemon p = new Pokedex(rand).getPokemon();
				p = new KillMe();
				battle = new Battle(trainer, p, rand);
				battleMenu.startBattle(battle);
				update();
			}
			update();
		}
	}

}
