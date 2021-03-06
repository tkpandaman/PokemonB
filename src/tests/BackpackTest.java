package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Backpack;
import model.PokemonItem;
import model.Potion;
import model.RunningShoes;
import model.TrainerItem;
import model.pokemon.Charizard;

import org.junit.Test;

public class BackpackTest {
	
	Random r = new Random(1337L);

	@Test
	public void backPackAddPokeballTest() {
		Backpack bp = new Backpack(r);
		assertEquals(bp.getPokeballsLeft(), 30);
		bp.addPokeball();
		assertEquals(bp.getPokeballsLeft(), 31);
		bp.usePokeball();
		assertEquals(bp.getPokeballsLeft(), 30);
		
		for(int i = 0; i < 31; i++){
			bp.usePokeball();
		}
		
		assertEquals(bp.usePokeball(), null);
	}
	
	@Test
	public void backPackAddTrainerItemTest(){
		Backpack bp = new Backpack(r);
		TrainerItem shoes = new RunningShoes();
		bp.addTrainerItem(shoes);
		assertEquals(bp.getTrainerItemNamed("Running Shoes").getName(), shoes.getName());
		assertEquals(bp.getTrainerItemNamed("Something"), null);
	}
	
	@Test
	public void backpackPokemonTest(){
		Backpack bp = new Backpack(r);
		Charizard charizard = new Charizard();
		bp.addPokemon(charizard);
		assertEquals(bp.getPokemonCaptured(), 1);
	}
	
	@Test
	public void backpackAddPokemonItemTest(){
		Backpack bp = new Backpack(r);
		PokemonItem pi = new Potion();
		bp.addPokemonItem(pi);
		assertEquals(pi, bp.getPokemonItems().get(0));
	}
	
	@Test
	public void backpackGetPokemonItemNamedTest(){
		Backpack bp = new Backpack(r);
		PokemonItem pi = new Potion();
		bp.addPokemonItem(pi);
		assertEquals(pi, bp.getPokemonItemNamed("Potion"));
		assertEquals(null, bp.getPokemonItemNamed("something"));
	}
	
	@Test
	public void backpackGetTrainerItemsTest(){
		Backpack bp = new Backpack(r);
		TrainerItem ti = new RunningShoes();
		bp.addTrainerItem(ti);
		assertEquals(ti, bp.getTrainerItems().get(0));
	}
	
	
    @Test
    public void testBackpackPokemonAt()
    {
    	Backpack bp = new Backpack(r);
        Charizard charizard = new Charizard();
        bp.addPokemon(charizard);
        assertEquals( "Charizard", bp.getPokemonAt( 0 ).getClass().getSimpleName() );
    }
    @Test
    public void testBackpackGetTrainerItems()
    {
        Backpack b = new Backpack( new Random() );
        List<TrainerItem> items = new ArrayList<TrainerItem>();
        assertEquals( items, b.getTrainerItems() );
    }
    @Test
    public void testBackpackGetPokemonItems()
    {
        Backpack b = new Backpack(new Random());
        List<PokemonItem> items = new ArrayList<PokemonItem>();
        assertEquals( items, b.getPokemonItems() );
    }
    @Test
    public void testBackpackRemovePokemonItem()
    {
        Backpack b = new Backpack(new Random());
        Potion p = new Potion();
        b.addPokemonItem( p );
        assertTrue( b.removePokemonItem( p ) );
    }
}
