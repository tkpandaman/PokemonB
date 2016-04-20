package model.pokemon;

import java.io.Serializable;
import model.Item;

/*
 * Inheritance hierarchy exists for Pokémon with at least 3 concrete classes.
	Should be Serializable.
 * 
 * */
public abstract class Pokemon implements Serializable {
	
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
	
	 @Override
	    public boolean equals(Object other) {
	        // In an equals method you should always confirm that the instance of the Object
	        // is the type you are looking for.
	        if (!(other instanceof Pokemon)) {
	            return false;
	        } else {
	            Pokemon otherPokemon = (Pokemon)other;
	            return this.getClass().getName().equals(otherPokemon.getClass().getName());
	        }
	    }
}
