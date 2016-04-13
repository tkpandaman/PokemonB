package model.pokemon;

import java.io.Serializable;

/*
 * Inheritance hierarchy exists for Pokémon with at least 3 concrete classes.
	Should be Serializable.
 * 
 * */
abstract class Pokemon implements Serializable {
	
	private int HP;
	private int runLikely;
	private int maxHP;
	private int maxDur;
	
	public Pokemon(int hp, int run, int maxHP, int maxDur){
		this.HP = hp;
		this.runLikely = run;
		this.maxHP = maxHP;
		this.maxDur = maxDur;
	}
	
	/**
	 * @return maxHP - the max HP this Pokemon can have
	 */
	public int getMaxHP(){
		return maxHP;
	}
	
	/**
	 * @return The ammount of HP the pokemon currently has
	 */
	public int getCurHP(){
		return HP;
	}
	
	/**
	 * @return How many turns till the Pokemon Runs
	 */
	public int getTurnsTillFlee(){
		return maxDur;
	}
	
	/**
	 * @return How likely is the Pokemon to run on initial encounter
	 */
	public int getLikelyRun(){
		return runLikely;
	}
	
	/**
	 * Subtracts damage took by total HP
	 * @param d (damage took)
	 */
	public void takeDamage(int d){
		HP -= d;
	}
	
	//public void flee(){
		// Not sure what to do here?
		/*
		 * Boolean inBattle = false?
		 * 
		 */
	//}
	
	abstract PokemonType getType();
}
