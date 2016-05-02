package model.pokemon;

import java.util.Random;

public class Pokedex {
	private Random theRandom;
	private double choice;

	public Pokedex(Random r){
		this.theRandom = r;
		this.choice = r.nextDouble();
	}
	
	// I know this is bad code but it's late
	public Pokedex(double choice){
		this.choice = choice;
	}

	public Pokemon getPokemon(){
		
		//Commons 75%
		if(choice >= 0.0 && choice < 0.125){
			return new Arbok();
		}
		else if (choice >= 0.125 && choice < 0.25){
			return new Voltorb();
		}
		else if (choice >= 0.25 && choice < 0.375){
			return new Butterfree();
		}
		else if (choice >= 0.375 && choice < 0.5){
			return new Spearow();
		}
		else if (choice >= 0.5 && choice < 0.625){
			return new Beedrill();
		}
		else if (choice >= 0.625 && choice < 0.75){
			return new Pidgeot();
		}

		//Rares 18%
		else if (choice >= 0.75 && choice < 0.81){
			return new Squirtle();
		}
		else if (choice >= 0.81 && choice < 0.87){
			return new Pikachu();
		}
		else if (choice >= 0.87 && choice < 0.93){
			return new Charizard();
		}
		
		//Ultra-rare 7%
		else if (choice >= 0.93 && choice < 0.995){
			return new Snorlax();
		}
		
		//Ultra-ultra-rare ~1%
		return new KillMe();
		
	}
}
