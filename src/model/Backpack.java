package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Backpack is in charge of encapsulating various List objects and provides convienient functionality
 * for the Trainer class to use.
 * 
 * @author AlexKatzfey
 *
 */
public class Backpack {

	//Create pockets in the backpack for different objects
	private List<Pokemon> pokemonList;
	private List<Pokeball> pokeBallList;
	private List<TrainerItem> trainerItemList;
	private List<PokemonItem> pokemonItemList;

	private static final int iniPokeBalls = 30;

	/**
	 * Create a new Backpack with intial amount of pokeballs
	 */
	public Backpack(){
		
		pokemonList = new ArrayList<Pokemon>();
		trainerItemList = new ArrayList<TrainerItem>();
		pokemonItemList = new ArrayList<PokemonItem>();
		pokeBallList = new ArrayList<Pokeball>();
		
		for (int i = 0; i < iniPokeBalls; i++){
			pokeBallList.add(new Pokeball(new Random()));
		}
	}
	
	/**
	 * Create a new Backpack for testing with intial amount of pokeballs
	 * @param rand as a Random seed
	 */
	public Backpack(Random rand){
		
		pokemonList = new ArrayList<Pokemon>();
		trainerItemList = new ArrayList<TrainerItem>();
		pokemonItemList = new ArrayList<PokemonItem>();
		pokeBallList = new ArrayList<Pokeball>();
		
		for (int i = 0; i < iniPokeBalls; i++){
			pokeBallList.add(new Pokeball(rand));
		}
	}
	
	/**
	 * Add a new pokeball to the list
	 */
	public void addPokeball(){
		pokeBallList.add(new Pokeball(new Random()));
	}
	
	/**
	 * Add a new pokeball to the list with testable random
	 * @param rand as a Random seed
	 */
	public void addPokeball(Random rand){
		pokeBallList.add(new Pokeball(rand));
	}

	/**
	 * Get the count of pokeballs as an int
	 * @return int pokeball count
	 */
	public int getPokeballsLeft(){
		return pokeBallList.size();
	}

	/**
	 * Removes a pokeball from the end of the list and returns it if the List is not empty.
	 * @return Pokeball
	 */
	public Pokeball usePokeball(){
		if(!pokeBallList.isEmpty()) return pokeBallList.remove(pokeBallList.size()-1);
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
	 * Add a TrainerItem to the list
	 * @param ti as a TrainerItem
	 */
	public void addTrainerItem(TrainerItem ti){
		trainerItemList.add(ti);
	}
	
	/**
	 * Return the number of captured pokemon
	 * @return int as number of pokemon
	 */
	public int getPokemonCaptured(){
		return pokemonList.size();
	}
	
	/**
	 * Add a pokemon to the list
	 * @param p as a Pokemon
	 */
	public void addPokemon(Pokemon p){
		pokemonList.add(p);
	}
	
	
	
}