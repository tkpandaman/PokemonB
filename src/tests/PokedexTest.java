package tests;

import static org.junit.Assert.*;

import java.util.Arrays;

import model.pokemon.Arbok;
import model.pokemon.Beedrill;
import model.pokemon.Butterfree;
import model.pokemon.Charizard;
import model.pokemon.Pidgeot;
import model.pokemon.Pikachu;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;
import model.pokemon.PokemonType;
import model.pokemon.Snorlax;
import model.pokemon.Spearow;
import model.pokemon.Squirtle;
import model.pokemon.Voltorb;

import org.junit.Test;

public class PokedexTest {
	
	@Test
	public void getArboc(){
		Pokedex p = new Pokedex(new TestRandom(Arrays.asList(0, 1, 2)));
		
		assertEquals(p.getPokemon(), new Arbok());
		assertEquals(p.getPokemon(), new Arbok());
		assertEquals(p.getPokemon(), new Arbok());
	}
	
	@Test
	public void getBeedrill(){
		Pokedex p = new Pokedex(new TestRandom(Arrays.asList(3, 4, 5)));
		
		assertEquals(p.getPokemon(), new Beedrill());
		assertEquals(p.getPokemon(), new Beedrill());
		assertEquals(p.getPokemon(), new Beedrill());
	}
	
	@Test
	public void getButterfree(){
		Pokedex p = new Pokedex(new TestRandom(Arrays.asList(6, 7, 8)));
		
		assertEquals(p.getPokemon(), new Butterfree());
		assertEquals(p.getPokemon(), new Butterfree());
		assertEquals(p.getPokemon(), new Butterfree());
	}
	
	@Test
	public void getCharizard(){
		Pokedex p = new Pokedex(new TestRandom(Arrays.asList(9, 10, 11)));
		
		assertEquals(p.getPokemon(), new Charizard());
		assertEquals(p.getPokemon(), new Charizard());
		assertEquals(p.getPokemon(), new Charizard());
	}
	
	@Test
	public void getPidgeot(){
		Pokedex p = new Pokedex(new TestRandom(Arrays.asList(12, 13, 14)));
		
		assertEquals(p.getPokemon(), new Pidgeot());
		assertEquals(p.getPokemon(), new Pidgeot());
		assertEquals(p.getPokemon(), new Pidgeot());
	}
	
	@Test
	public void getPikachu(){
		Pokedex p = new Pokedex(new TestRandom(Arrays.asList(15, 16)));
		
		assertEquals(p.getPokemon(), new Pikachu());
		assertEquals(p.getPokemon(), new Pikachu());
	}
	
	@Test
	public void getSnorlax(){
		Pokedex p = new Pokedex(new TestRandom(Arrays.asList(17, 18)));
		
		assertEquals(p.getPokemon(), new Snorlax());
		assertEquals(p.getPokemon(), new Snorlax());
	}
	
	@Test
	public void getSpearow(){
		Pokedex p = new Pokedex(new TestRandom(Arrays.asList(19, 20)));
		
		assertEquals(p.getPokemon(), new Spearow());
		assertEquals(p.getPokemon(), new Spearow());
	}
	
	@Test
	public void getSquirtle(){
		Pokedex p = new Pokedex(new TestRandom(Arrays.asList(21)));
		
		assertEquals(p.getPokemon(), new Squirtle());
	}
	
	@Test
	public void getVoltorb(){
		Pokedex p = new Pokedex(new TestRandom(Arrays.asList(22)));
		
		assertEquals(p.getPokemon(), new Voltorb());
	}
	
	
	
	
	
	

}
