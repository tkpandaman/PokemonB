package tests;

import static org.junit.Assert.*;

import java.util.Random;

import model.Backpack;
import model.Battle;
import model.BattleMenu;
import model.Trainer;

import org.junit.Test;

public class BattleMenuTest {
	
	private Random rand = new Random(1337L);

	@Test
	public void startBattleTest() {
		BattleMenu menu = new BattleMenu();
		Backpack bp = new Backpack(rand);
		Trainer t = new Trainer("Ash", bp);
		Battle b = new Battle(t,rand,0.8);
		menu.startBattle(b);
		assertEquals(b, menu.getBattle());
	}
	
	@Test
	public void directionTest() {
		BattleMenu menu = new BattleMenu();
		Backpack bp = new Backpack(rand);
		Trainer t = new Trainer("Ash", bp);
		Battle b = new Battle(t,rand,0.8);
		menu.startBattle(b);
		menu.select();
		assertEquals(0,menu.getIndex());
		menu.right();
		menu.select();
		assertEquals(1,menu.getIndex());
		menu.left();
		menu.select();
		assertEquals(0,menu.getIndex());
		menu.down();
		menu.select();
		assertEquals(2,menu.getIndex());
		menu.right();
		menu.select();
		assertEquals(3,menu.getIndex());
		menu.down();
		menu.up();
	}
	
	@Test
	public void getTextTest() {
		BattleMenu menu = new BattleMenu();
		Backpack bp = new Backpack(rand);
		Trainer t = new Trainer("Ash", bp);
		Battle b = new Battle(t,rand,0.8);
		menu.startBattle(b);
		assertEquals("",menu.getText());
		
	}
	
	@Test
	public void getMenuItemTest() {
		BattleMenu menu = new BattleMenu();
		Backpack bp = new Backpack(rand);
		Trainer t = new Trainer("Ash", bp);
		Battle b = new Battle(t,rand,0.8);
		menu.startBattle(b);
		assertEquals("BALL",menu.getItems()[0].getText());
		assertEquals(0,menu.getItems()[0].getX());
		assertEquals(0,menu.getItems()[0].getY());
		assertEquals("BAIT",menu.getItems()[1].getText());
		assertEquals(0,menu.getItems()[0].getX());
		assertEquals(0,menu.getItems()[0].getY());
		assertEquals("ROCK",menu.getItems()[2].getText());
		assertEquals(0,menu.getItems()[0].getX());
		assertEquals(0,menu.getItems()[0].getY());
		assertEquals("RUN",menu.getItems()[3].getText());
		assertEquals(0,menu.getItems()[0].getX());
		assertEquals(0,menu.getItems()[0].getY());
	}
	

}
