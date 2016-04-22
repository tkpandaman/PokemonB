package model.pokemon;

import java.util.ArrayList;
import java.util.Random;

public class Pokedex {
	private Random theRandom;
	private ArrayList<Pokemon> pokemon;
	
    public Pokedex(Random r){
    	this.theRandom = r;
    	pokemon = new ArrayList<Pokemon>();
    	
    	// Add in our Pokemon
    	pokemon.add(new Arbok());
    	pokemon.add(new Beedrill());
    	pokemon.add(new Butterfree());
    	pokemon.add(new Charizard());
    	pokemon.add(new Pidgeot());
    	pokemon.add(new Pikachu());
    	pokemon.add(new Snorlax());
    	pokemon.add(new Spearow());
    	pokemon.add(new Squirtle());
    	pokemon.add(new Voltorb());
      	
    }
    
    public Pokemon getPokemon(){
    	switch(theRandom.nextInt(23)){
    	case 0: case 1: case 2:
    		return pokemon.get(0);
    	case 3: case 4: case 5: 
    		return pokemon.get(1);
    	case 6: case 7: case 8: 
    		return pokemon.get(2);
    	case 9: case 10: case 11: 
    		return pokemon.get(3);
    	case 12: case 13: case 14:
    		return pokemon.get(4);
    	case 15: case 16: 
    		return pokemon.get(5);
    	case 17: case 18: 
    		return pokemon.get(6);
    	case 19: case 20: 
    		return pokemon.get(7);
    	case 21: 
    		return pokemon.get(8);
    	case 22: 
    		return pokemon.get(9);
    	}
    	
    	return null;
    }
}
