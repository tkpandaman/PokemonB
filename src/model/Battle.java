package model;

import java.io.Serializable;
import java.util.Observable;
import java.util.Random;

import model.pokemon.Arbok;
import model.pokemon.Beedrill;
import model.pokemon.Butterfree;
import model.pokemon.Charizard;
import model.pokemon.Pikachu;
import model.pokemon.Pokemon;
import model.pokemon.Snorlax;
import model.pokemon.Spearow;
import model.pokemon.Squirtle;
import model.pokemon.Voltorb;

/**
 * The Class Battle.
 * @author AlexKatzfey
 */
public class Battle extends Observable implements Serializable {
	
    private static final long serialVersionUID = -8112809181561931812L;
    private double chanceOfRun;
	private double chanceOfCapture;
	private int turnsTillFlee;
	private int turn; 
	private boolean willFlee;
	private Pokemon poke;
	private Trainer t;
	private Random rand;
	
	/** The Constant ROCK_DAMAGE. */
	//Change these to affect the balance
	public static final int ROCK_DAMAGE = 20;
	
	/** The Constant BAIT_CHANCE. */
	public static final double BAIT_CHANCE = 0.05;
	
	/** The Constant MIN_RUN_CHANCE. */
	public static final double MIN_RUN_CHANCE = 0.10;
	
	/** The Constant MIN_CAPTURE_CHANCE. */
	public static final double MIN_CAPTURE_CHANCE = 0.35;
	
	/**
	 * Instantiates a new battle.
	 *
	 * @param t the trainer
	 */
	public Battle(Trainer t){
		this.t = t;
		this.rand = new Random();
		this.poke = randomPokemon(rand.nextDouble());
		this.turnsTillFlee = poke.getTurnsTillFlee();
		this.chanceOfRun = poke.getLikelyRun() / 100;
		this.turn = 1;
		this.willFlee = false;
		this.chanceOfCapture = MIN_CAPTURE_CHANCE;
	}
	
	/**
	 * Instantiates a new battle with random seed for testability.
	 *
	 * @param t the trainer
	 * @param p the pokemon
	 * @param r the random
	 */
	public Battle(Trainer t, Random rand, double pokemonChoice){
		this.t = t;
		this.rand = rand;
		this.poke = randomPokemon(pokemonChoice);
		this.turnsTillFlee = poke.getTurnsTillFlee();
		this.chanceOfRun = (double)poke.getLikelyRun() / 100;
		this.turn = 1;
		this.willFlee = false;
		this.chanceOfCapture = MIN_CAPTURE_CHANCE;
	}
	
	private void setCaptureChance(){
		this.chanceOfCapture = 1.0 - ((double)poke.getCurHP() / poke.getMaxHP()) + MIN_CAPTURE_CHANCE;
	}
	
	/**
	 * Gets the capture chance.
	 *
	 * @return the capture chance
	 */
	public double getCaptureChance(){
		return this.chanceOfCapture;
	}
	public Trainer getTrainer()
	{
	    return t;
	}
	/**
	 * Gets the flee chance.
	 *
	 * @return the flee chance
	 */
	public double getFleeChance(){
		return this.chanceOfRun;
	}
	
	/**
	 * Gets the turn.
	 *
	 * @return the turn
	 */
	public int getTurn(){
		return this.turn;
	}
	
	
	/**
	 * Gets the pokemon.
	 *
	 * @return the pokemon
	 */
	public Pokemon getPokemon(){
		return this.poke;
	}
	
	private void endTurn(){
		//End of turn actions here
		this.turn++;
		
		if (turn >= turnsTillFlee || rand.nextDouble() < chanceOfRun){
			this.willFlee = true;
		}
		
	}
	
	private Pokemon randomPokemon(double choice){
		
		//Commons 60%
		if(choice >= 0.0 && choice < 0.15){
			return new Arbok();
		}
		else if (choice >= 0.15 && choice < 0.30){
			return new Voltorb();
		}
		else if (choice >= 0.30 && choice < 0.45){
			return new Butterfree();
		}
		else if (choice >= 0.45 && choice < 0.60){
			return new Spearow();
		}
		
		//Rares 30%
		else if (choice >= 0.60 && choice < 0.675){
			return new Beedrill();
		}
		else if (choice >= 0.675 && choice < 0.75){
			return new Pikachu();
		}
		else if (choice >= 0.75 && choice < 0.825){
			return new Charizard();
		}
		else if (choice >= 0.825 && choice < 0.9){
			return new Squirtle();
		}
		
		//Ultra-rare 10%
		return new Snorlax();
		
	}
	
	/**
	 * Check to see if Pokemon ran away each turn.
	 *
	 * @return true, if successful
	 */
	public boolean pokemonRanAway(){
		return this.willFlee;
	}
	
	public void setPokemonRanAway(boolean val){
		this.willFlee = val;
	}
	
	
	/**
	 * Throw rock. Increases chance of capturing pokemon.
	 */
	public void throwRock(){
		this.poke.takeDamage(ROCK_DAMAGE);
		setCaptureChance();
		endTurn();
	}
	
	/**
	 * Throw bait. Lower's chance of pokemon running.
	 */
	public void throwBait(){
		if(this.chanceOfRun >= MIN_RUN_CHANCE + BAIT_CHANCE) this.chanceOfRun -= BAIT_CHANCE;
		endTurn();
	}
	
	/**
	 * Throw safari ball. If successful, pokemon is added to Trainer's backpack.
	 *
	 * @return true, if the pokemon is captured
	 */
	public boolean throwSafariBall(){
		Pokeball ball = this.t.openPack().usePokeball();
		if(ball != null){
			ball.random = this.rand;
			endTurn();
			boolean captured = ball.use(this.chanceOfCapture);
			if (captured) t.openPack().addPokemon(this.poke); //pokemon is added to backpack if captured
			return captured;
		}
		return false; //This should never be called as the player 
					  //should not be able to throw a pokeball when they have 0 left.
		
	}

}
