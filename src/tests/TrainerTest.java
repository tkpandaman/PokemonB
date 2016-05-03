package tests;

import static org.junit.Assert.*;

import java.util.Random;

import model.Backpack;
import model.Trainer;

import org.junit.Test;

public class TrainerTest {
	
	private Random r = new Random(1337L);

	@Test
	public void nameTest() {
		Backpack bp = new Backpack(r);
		Trainer ash = new Trainer("Ash", bp);
		assertEquals(ash.getName(), "Ash");
	}
	
	@Test
	public void stepsTest() {
		Backpack bp = new Backpack(r);
		Trainer ash = new Trainer("Ash", bp);
		assertEquals(500, ash.getStepsLeft());
		ash.takeStep();
		assertEquals(499, ash.getStepsLeft());
	}
	
	
	@Test
	public void speedTest() {
		Backpack bp = new Backpack(r);
		Trainer ash = new Trainer("Ash", bp);
		assertEquals(ash.getSpeed(), 2.0, 0.001);
		ash.setSpeed(1.5);
		assertEquals(ash.getSpeed(), 1.5, 0.001);
	}
	
	@Test
	public void backpackTest(){
		Backpack bp = new Backpack(r);
		Trainer ash = new Trainer("Ash", bp);
		assertEquals(ash.openPack().getPokeballsLeft(), 30);
		ash.openPack().addPokeball();
		assertEquals(ash.openPack().getPokeballsLeft(), 31);
		ash.openPack().usePokeball();
		assertEquals(ash.openPack().getPokeballsLeft(), 30);
	}
	@Test
	public void testReset()
	{
	    Backpack bp = new Backpack(r);
	    Trainer ash = new Trainer("Ash", bp);
	    ash.reset( bp );
	}
}
