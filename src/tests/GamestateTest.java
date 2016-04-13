package tests;

import static org.junit.Assert.*;
import model.Gamestate;
import model.Map;
import model.State;
import model.Trainer;

import org.junit.Test;

public class GamestateTest {
	
	@Test
	public void getInstanceTest() {
		assertTrue(Gamestate.getInstance() != null);
	}
	
	@Test
	public void getTrainerTest() {
		assertTrue(Gamestate.getInstance().getTrainer() != null);
		Gamestate.getInstance().setTrainer(new Trainer("Ash"));
		assertTrue(Gamestate.getInstance().getTrainer().getName().equals("Ash"));
	}
	
	@Test
	public void getTrainerXTest() {
		Gamestate.getInstance().setTrainerX(5);
		assertEquals(Gamestate.getInstance().getTrainerX(),5);
	}
	
	@Test
	public void getTrainerYTest() {
		Gamestate.getInstance().setTrainerY(10);
		assertEquals(Gamestate.getInstance().getTrainerY(),10);
	}
	
	@Test
	public void getMapTest() {
		Gamestate.getInstance().setCurrentMap(new Map(256, 512, "tileset-x2", 32));
		assertEquals(Gamestate.getInstance().getCurrentMap().getWidth(),256);
		assertEquals(Gamestate.getInstance().getCurrentMap().getHeight(),512);
	}
	
	@Test
	public void getStateTest() {
		Gamestate.getInstance().setState(State.BATTLE);
		assertEquals(Gamestate.getInstance().getState(),State.BATTLE);
	}
	
	@Test
	public void setInstanceTest(){
		Gamestate.setInstance(Gamestate.getInstance());
	}
	

}
