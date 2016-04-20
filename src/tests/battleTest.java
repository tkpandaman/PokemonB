package tests;

import static org.junit.Assert.*;

import java.util.Random;

import model.Backpack;
import model.Battle;
import model.Trainer;
import model.pokemon.Arbok;
import model.pokemon.Butterfree;
import model.pokemon.Charizard;
import model.pokemon.Pikachu;
import model.pokemon.Pokemon;

import org.junit.Test;

public class battleTest {

	private Random rand = new Random(1337L);
	private Trainer ash = new Trainer("Ash");

	@Test
	public void setupBattleTest() {
		//Pokemon arbok = new Arbok();
		Battle b = new Battle(ash, rand, 0.1);
		assertFalse(b.pokemonRanAway());
	}

	@Test
	public void throwRockTest() {
		Pokemon c = new Charizard();
		Battle b = new Battle(ash, rand, 0.8);

		b.throwRock();
		assertEquals(c.getMaxHP() - Battle.ROCK_DAMAGE, c.getCurHP());
		assertFalse(b.pokemonRanAway());

		for(int i = 0; i < 4; i++){ //throw 4 rocks = 4 turns passing 
			b.throwRock();
		}
		assertTrue(b.pokemonRanAway());
	}

	@Test
	public void throwBaitTest() {
		Pokemon c = new Charizard();
		Battle b = new Battle(ash, rand, 0.8);
		
		double runChance = ((double)c.getLikelyRun());

		assertEquals(runChance, b.getFleeChance(), 0.00001);
		b.throwBait();
		assertEquals(runChance - Battle.BAIT_CHANCE , b.getFleeChance(), 0.00001);
		assertFalse(b.pokemonRanAway());

		for(int i = 0; i < 10; i++){ //throw bait 10 times to test min run chance
			b.throwBait();
		}
		assertEquals(Battle.MIN_RUN_CHANCE , b.getFleeChance(), 0.00001);
		assertTrue(b.pokemonRanAway());
	}
	
	@Test
	public void throwSafariBallTest(){
		Pokemon c = new Pikachu();
		Trainer m = new Trainer("Misty");
		Battle b = new Battle(m, rand, 0.7);
		
		assertEquals(Battle.MIN_CAPTURE_CHANCE , b.getCaptureChance(), 0.00001);
		assertFalse(b.throwSafariBall());
		assertEquals(Backpack.INI_POKEBALLS - 1, m.openPack().getPokeballsLeft());
		
		for(int i = 0; i < 4; i++){ 
			b.throwBait();
		}
		assertEquals(6, b.getTurn());
		assertFalse(b.pokemonRanAway());
		assertTrue(b.throwSafariBall());
		assertEquals(1, m.openPack().getPokemonCaptured());
	}
	@Test
	public void testRandomPokemon()
	{
	    
	}
	
	


}
