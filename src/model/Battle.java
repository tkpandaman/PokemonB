package model;

import java.util.Random;

import model.pokemon.Pokemon;

/**
 * The Class Battle.
 * @author AlexKatzfey
 */
public class Battle {
	
	private double chanceOfRun;
	private double chanceOfCapture;
	private int turnsTillFlee;
	private int turn; 
	private boolean willFlee;
	private Pokemon poke;
	private Trainer t;
	private Random rand;
	
	//Change these to affect the balance
	public static final int ROCK_DAMAGE = 20;
	public static final double BAIT_CHANCE = 0.05;
	public static final double MIN_RUN_CHANCE = 0.10;
	public static final double MIN_CAPTURE_CHANCE = 0.35;
	
	/**
	 * Instantiates a new battle.
	 *
	 * @param t the trainer
	 * @param p the pokemon
	 */
	public Battle(Trainer t, Pokemon p){
		this.poke = p;
		this.t = t;
		this.turnsTillFlee = p.getTurnsTillFlee();
		this.turn = 1;
		this.chanceOfRun = poke.getLikelyRun() / 100;
		this.willFlee = false;
		this.rand = new Random();
		this.chanceOfCapture = MIN_CAPTURE_CHANCE;
	}
	
	/**
	 * Instantiates a new battle with random seed for testability.
	 *
	 * @param t the trainer
	 * @param p the pokemon
	 * @param r the random
	 */
	public Battle(Trainer t, Pokemon p, Random r){
		this.poke = p;
		this.t = t;
		this.turnsTillFlee = p.getTurnsTillFlee();
		this.turn = 1;
		this.chanceOfRun = (double)poke.getLikelyRun() / 100;
		this.willFlee = false;
		this.rand = r;
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
	
	private void endTurn(){
		//End of turn actions here
		this.turn++;
		
		if (turn >= turnsTillFlee || rand.nextDouble() < chanceOfRun){
			this.willFlee = true;
		}
		
	}
	
	/**
	 * Check to see if Pokemon ran away each turn.
	 *
	 * @return true, if successful
	 */
	public boolean pokemonRanAway(){
		return this.willFlee;
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
