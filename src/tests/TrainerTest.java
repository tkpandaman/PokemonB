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

}
