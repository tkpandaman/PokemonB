package tests;

import static org.junit.Assert.*;
import model.Trainer;

import org.junit.Test;

public class TrainerTest {
	
	private Trainer ash = new Trainer("Ash");

	@Test
	public void nameTest() {
		assertEquals(ash.getName(), "Ash");
	}
	
	@Test
	public void stepsTest() {
		assertEquals(ash.getStepsLeft(), 500);
		ash.takeStep();
		assertEquals(ash.getStepsLeft(), 499);
	}
	
	@Test
	public void speedTest() {
		assertEquals(ash.getSpeed(), 1.0, 0.001);
		ash.setSpeed(1.5);
		assertEquals(ash.getSpeed(), 1.5, 0.001);
	}
	
	@Test
	public void backpackTest(){
		assertEquals(ash.openPack().getPokeballsLeft(), 30);
		ash.openPack().addPokeball();
		assertEquals(ash.openPack().getPokeballsLeft(), 31);
		ash.openPack().usePokeball();
		assertEquals(ash.openPack().getPokeballsLeft(), 30);
	}
	
}
