package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.pokemon.Pokemon;

/**
 * Backpack is in charge of encapsulating various List objects and provides convienient functionality
 * for the Trainer class to use.
 * 
 * @author AlexKatzfey
 *
 */
public class Backpack implements Serializable{

    private static final long serialVersionUID = -3247840845559132102L;
    //Create pockets in the backpack for different objects
	private List<Pokemon> pokemonList;
	private List<TrainerItem> trainerItemList;
	private List<PokemonItem> pokemonItemList;
	private int pokeBalls;
	private Random rand;
	
	public static final int INI_POKEBALLS = 30;  //initial amount of pokeballs
	
	/**
	 * Create a new Backpack for testing with intial amount of pokeballs
	 * @param rand as a Random seed
	 */
	public Backpack(Random rand){
		
		pokemonList = new ArrayList<Pokemon>();
		trainerItemList = new ArrayList<TrainerItem>();
		pokemonItemList = new ArrayList<PokemonItem>();
		pokeBalls = INI_POKEBALLS;
		this.rand = rand;
	}
	
	/**
	 * Add a new pokeball to the count
	 */
	public void addPokeball(){
		pokeBalls++;
	}

	/**
	 * Get the count of pokeballs as an int
	 * @return int pokeball count
	 */
	public int getPokeballsLeft(){
		return pokeBalls;
	}

	/**
	 * Removes a pokeball from the count and returns a new Pokeball object with rand seed.
	 * @return Pokeball
	 */
	public Pokeball usePokeball(){
		if(pokeBalls > 0) {
			pokeBalls--;
			return new Pokeball(rand);
		}
		return null;
	}
	
	/**
	 * Finds and returns a TrainerItem with the given name
	 * @param name as the Item name
	 * @return TrainerItem
	 */
	public TrainerItem getTrainerItemNamed(String name){
		for(TrainerItem i : trainerItemList){
			if (i.getName().equals(name)){
				return i;
			}
		}
		return null;
	}
	
	/**
	 * Finds and returns a PokemonItem with the given name
	 * @param name as the Item name
	 * @return PokemonItem
	 */
	public PokemonItem getPokemonItemNamed(String name){
		for(PokemonItem i : pokemonItemList){
			if (i.getName().equals(name)){
				return i;
			}
		}
		return null;
	}
	
	public List<TrainerItem> getTrainerItems(){
		return this.trainerItemList;
	}
	
	public List<PokemonItem> getPokemonItems(){
		return this.pokemonItemList;
	}
	
	/**
	 * Add a TrainerItem to the list
	 * @param ti as a TrainerItem
	 */
	public void addTrainerItem(TrainerItem ti){
		trainerItemList.add(ti);
	}
	
	/**
	 * Add a TrainerItem to the list
	 * @param ti as a TrainerItem
	 */
	public void addPokemonItem(PokemonItem pi){
		pokemonItemList.add(pi);
	}
	
	/**
	 * Return the number of captured pokemon
	 * @return int as number of pokemon
	 */
	public int getPokemonCaptured(){
		return pokemonList.size();
	}
	
	public Pokemon getPokemonAt(int index){
		return pokemonList.get(index);
	}
	
	/**
	 * Add a pokemon to the list
	 * @param p as a Pokemon
	 */
	public void addPokemon(Pokemon p){
		pokemonList.add(p);
	}
	
	
	
}