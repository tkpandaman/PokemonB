package model;

import model.pokemon.Pokemon;

public class Battle {
	
	private double chanceOfRun;
	private double chanceOfCapture;
	private int turnsTillFlee;
	private int turn;
	private Pokemon poke;
	private Trainer t;
	
	private static final int ROCK_DAMAGE = 10;
	private static final double BAIT_CHANCE = 0.05;
	
	public Battle(Trainer t, Pokemon p){
		this.poke = p;
		this.t = t;
		this.turnsTillFlee = p.getTurnsTillFlee();
		this.turn = 1;
		this.chanceOfRun = poke.getLikelyRun() / 100;
		setCaptureChance();
	}
	
	private void setCaptureChance(){
		this.chanceOfCapture = 1 - (poke.getCurHP() / poke.getMaxHP());
	}
	
	private void endTurn(){
		//End of turn actions here
	}
	
	public void throwRock(){
		this.poke.takeDamage(ROCK_DAMAGE);
		setCaptureChance();
		endTurn();
	}
	
	public void throwBait(){
		this.chanceOfRun -= BAIT_CHANCE;
		endTurn();
	}
	
	public void throwSafariBall(){
		Pokeball ball = this.t.openPack().usePokeball();
		if(ball != null){
			
		}
	}
	
	

}
