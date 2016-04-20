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
		Battle b = new Battle(ash, rand, 0.0);
		assertFalse(b.pokemonRanAway());
	}

	@Test
	public void throwRockTest() {
		Pokemon c = new Charizard();
		Battle b = new Battle(ash, rand, 0.8);

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

		Battle b = new Battle(ash, rand, 0.8);
		Pokemon c = b.getPokemon();
		System.out.println(b.getFleeChance());
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
	public void testArbok()
	{
	    Trainer m = new Trainer("Misty");
	    Battle b = new Battle(m, rand, 0.1);
	    assertEquals( "Arbok", b.getPokemon().getClass().getSimpleName() );
	}
	@Test
    public void testVoltorb()
    {
        Trainer m = new Trainer("Misty");
        Battle b = new Battle(m, rand, 0.2);
        assertEquals( "Voltorb", b.getPokemon().getClass().getSimpleName() );
    }
	@Test
    public void testButterfree()
    {
        Trainer m = new Trainer("Misty");
        Battle b = new Battle(m, rand, 0.4);
        assertEquals( "Butterfree", b.getPokemon().getClass().getSimpleName() );
    }
	@Test
    public void testSpearow()
    {
        Trainer m = new Trainer("Misty");
        Battle b = new Battle(m, rand, 0.5);
        assertEquals( "Spearow", b.getPokemon().getClass().getSimpleName() );
    }
	@Test
    public void testBeedrill()
    {
        Trainer m = new Trainer("Misty");
        Battle b = new Battle(m, rand, 0.6);
        assertEquals( "Beedrill", b.getPokemon().getClass().getSimpleName() );
    }
	@Test
	public void testSquirtle()
	{
	    Trainer m = new Trainer("Misty");
        Battle b = new Battle(m, rand, 0.85);
        assertEquals( "Squirtle", b.getPokemon().getClass().getSimpleName() );
	}
	@Test
    public void testSnorlax()
    {
        Trainer m = new Trainer("Misty");
        Battle b = new Battle(m, rand, 0.95);
        assertEquals( "Snorlax", b.getPokemon().getClass().getSimpleName() );
    }
	@Test
	public void testRandomBattle()
	{
	    Trainer m = new Trainer("Misty");
        Battle b = new Battle(m);
	}
	@Test
	public void testPokeballsRunOut()
	{
	    Trainer m = new Trainer("Misty");
	    assertEquals( 30, m.openPack().getPokeballsLeft() );
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    m.openPack().usePokeball();
	    assertEquals( 0, m.openPack().getPokeballsLeft() );
        Battle b = new Battle(m);
        assertFalse( b.throwSafariBall() );
	}
}
