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
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;

import org.junit.Test;

public class battleTest {

	private Random rand = new Random(1337L);

	@Test
	public void setupBattleTest() {
		Backpack bp = new Backpack(rand);
		Trainer m = new Trainer("Misty", bp);
		Pokemon p = new Pokedex(0.8).getPokemon();
		Battle b = new Battle(m, p, rand);
		assertFalse(b.pokemonRanAway());
	}

	@Test
	public void throwRockTest() {
		Backpack bp = new Backpack(rand);
		Trainer m = new Trainer("Misty", bp);
		Pokemon p = new Pokedex(0.8).getPokemon();
		Battle b = new Battle(m, p, rand);

		b.throwRock();
		assertEquals(b.getPokemon().getMaxHP() - Battle.ROCK_DAMAGE, b.getPokemon().getCurHP());
		assertFalse(b.pokemonRanAway());

		for(int i = 0; i < 4; i++){ //throw 4 rocks = 4 turns passing 
			b.throwRock();
		}
		assertTrue(b.pokemonRanAway());
	}

	@Test
	public void throwBaitTest() {
		Backpack bp = new Backpack(rand);
		Trainer m = new Trainer("Misty", bp);
		Pokemon p = new Pokedex(0.8).getPokemon();
		Battle b = new Battle(m, p, rand);
		Pokemon c = b.getPokemon();
		
		double runChance = (double)c.getLikelyRun()/100;

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
		Backpack bp = new Backpack(rand);
		Trainer m = new Trainer("Misty", bp);
		Pokemon p = new Pokedex(0.6).getPokemon();
		Battle b = new Battle(m, p, rand);
		
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
	public void testArbok()
	{
		Backpack bp = new Backpack(rand);
		Trainer m = new Trainer("Misty", bp);
		Pokemon p = new Pokedex(0.1).getPokemon();
		Battle b = new Battle(m, p, rand);
	    assertEquals( "Arbok", b.getPokemon().getClass().getSimpleName() );
	}
	@Test
    public void testVoltorb()
    {
		Backpack bp = new Backpack(rand);
		Trainer m = new Trainer("Misty", bp);
		Pokemon p = new Pokedex(0.2).getPokemon();
		Battle b = new Battle(m, p, rand);
        assertEquals( "Voltorb", b.getPokemon().getClass().getSimpleName() );
    }
	@Test
    public void testButterfree()
    {
		Backpack bp = new Backpack(rand);
		Trainer m = new Trainer("Misty", bp);
		Pokemon p = new Pokedex(0.3).getPokemon();
		Battle b = new Battle(m, p, rand);
        assertEquals( "Butterfree", b.getPokemon().getClass().getSimpleName() );
    }
	@Test
    public void testSpearow()
    {
		Backpack bp = new Backpack(rand);
		Trainer m = new Trainer("Misty", bp);
		Pokemon p = new Pokedex(0.4).getPokemon();
		Battle b = new Battle(m, p, rand);
        assertEquals( "Spearow", b.getPokemon().getClass().getSimpleName() );
    }
	@Test
    public void testBeedrill()
    {
		Backpack bp = new Backpack(rand);
		Trainer m = new Trainer("Misty", bp);
		Pokemon p = new Pokedex(0.6).getPokemon();
		Battle b = new Battle(m, p, rand);
        assertEquals( "Beedrill", b.getPokemon().getClass().getSimpleName() );
    }
	@Test
	public void testSquirtle()
	{
		Backpack bp = new Backpack(rand);
		Trainer m = new Trainer("Misty", bp);
		Pokemon p = new Pokedex(0.8).getPokemon();
		Battle b = new Battle(m, p, rand);
        assertEquals( "Squirtle", b.getPokemon().getClass().getSimpleName() );
	}
	@Test
    public void testSnorlax()
    {
		Backpack bp = new Backpack(rand);
		Trainer m = new Trainer("Misty", bp);
		Pokemon p = new Pokedex(0.97).getPokemon();
		Battle b = new Battle(m, p, rand);
        assertEquals( "Snorlax", b.getPokemon().getClass().getSimpleName() );
    }
	@Test
	public void testRandomBattle()
	{
		Backpack bp = new Backpack(rand);
		Trainer m = new Trainer("Misty", bp);
		Pokemon p = new Pokedex(0.8).getPokemon();
		Battle b = new Battle(m, p, rand);
	}
	@Test
	public void testPokeballsRunOut()
	{
		Backpack bp = new Backpack(rand);
		Trainer m = new Trainer("Misty", bp);
	    assertEquals( 30, m.openPack().getPokeballsLeft() );
	
	    for(int i = 0; i < 30; i++){
	    	m.openPack().usePokeball();
	    }
	    assertEquals( 0, m.openPack().getPokeballsLeft() );
	    Pokemon p = new Pokedex(rand).getPokemon();
		Battle b = new Battle(m, p, rand);
        //assertEquals( b.ge )
        assertFalse( b.throwSafariBall() );
	}
}
