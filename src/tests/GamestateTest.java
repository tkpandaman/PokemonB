package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import model.Gamestate;
import model.Map;
import model.State;
import model.Trainer;

import org.junit.Test;

public class GamestateTest extends SerializableTestCase{
	
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
		Gamestate.getInstance().setState(State.NORMAL);
		assertEquals(Gamestate.getInstance().getState(),State.NORMAL);
		Gamestate.getInstance().setState(State.LOSE);
		assertEquals(Gamestate.getInstance().getState(),State.LOSE);
		Gamestate.getInstance().setState(State.WIN);
		assertEquals(Gamestate.getInstance().getState(),State.WIN);
		Gamestate.getInstance().setState(State.MENU);
		assertEquals(Gamestate.getInstance().getState(),State.MENU);
	}
	
	@Test
	public void setInstanceTest(){
		Gamestate.setInstance(Gamestate.getInstance());
	}
	
	@Test
	public void serializableTest() throws ClassNotFoundException, IOException{
		Gamestate g = Gamestate.getInstance();
		this.assertObjectSerializable(g);
	}
	

}
