package tests;

import static org.junit.Assert.*;
import model.Item;
import model.MapItem;
import model.Potion;

import org.junit.Test;

public class MapItemTest {
	
	@Test
	public void mapItemTest() {
		Item i = new Potion();
		MapItem m = new MapItem(0,0,i);
		assertEquals(i,m.getItem());
	}
	
	@Test
	public void mapItemXandYTest() {
		Item i = new Potion();
		MapItem m = new MapItem(32,64,i);
		assertEquals(32,m.getX());
		assertEquals(64,m.getY());
	}

}
