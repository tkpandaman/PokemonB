package model;

import java.util.ArrayList;
import java.util.List;

public class Backpack {
	List<Pokemon> pokemonList;
	List<Items> items;
	
	public Backpack(){
		pokemonList = new ArrayList();
		items = new ArrayList();
	}
	
	public List getPokemon(){
		return this.pokemonList;
	}
	
	public List getItems(){
		return this.items;
	}
	
	public int getPokeballsLeft(){
		int pokeballCount = 0;
		for(Item i: items){
			if(i.class.equals(Pokeball)){
				pokeballCount++;
			}
		}
		
		return pokeballCount;
	}
	
	public Pokeball usePokeball(){
		for(Item i: items){
			if(i.class.equals(Pokeball)){
				return items.pop(i);
			}
		}
		
		return null;
	}
	
	
}
