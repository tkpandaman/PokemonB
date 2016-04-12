package tests;

import static org.junit.Assert.*;

import java.util.Random;

import model.Backpack;
import model.Charizard;
import model.RunningShoes;
import model.TrainerItem;

import org.junit.Test;

public class BackpackTest {
	
	Random r = new Random();
	Backpack bp = new Backpack();
	Backpack bpR = new Backpack(r);

	@Test
	public void backPackAddPokeballTest() {
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
		TrainerItem shoes = new RunningShoes();
		bp.addTrainerItem(shoes);
		assertEquals(bp.getTrainerItemNamed("Running Shoes").getName(), shoes.getName());
		assertEquals(bp.getTrainerItemNamed("Something"), null);
	}
	
	@Test
	public void backpackPokemonTest(){
		Charizard charizard = new Charizard();
		bp.addPokemon(charizard);
		assertEquals(bp.getPokemonCaptured(), 1);
	}

}
