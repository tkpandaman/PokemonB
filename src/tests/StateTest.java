package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import model.State;

public class StateTest extends SerializableTestCase{

	@Test
	public void test() {
		assertEquals(model.State.BATTLE, model.State.BATTLE);
		assertEquals(model.State.LOSE, model.State.LOSE);
		assertEquals(model.State.WIN, model.State.WIN);
		assertEquals(model.State.MENU, model.State.MENU);
		assertEquals(model.State.NORMAL, model.State.NORMAL);
		assertEquals( State.valueOf( "NORMAL" ), model.State.NORMAL );
	}
	
	@Test
	public void serializeableTest() throws ClassNotFoundException, IOException {
		model.State s = model.State.MENU;
		this.assertObjectSerializable(s);
	}
	

}
