package tests;

import static org.junit.Assert.*;

import java.util.Random;

import model.Backpack;
import model.Battle;
import model.BattleAction;
import model.BattleMenu;
import model.Trainer;
import model.pokemon.Pokedex;
import model.pokemon.Pokemon;

import org.junit.Test;

public class BattleMenuTest {
	
	private Random rand = new Random(1337L);

	@Test
	public void startBattleTest() {
		BattleMenu menu = new BattleMenu();
		Backpack bp = new Backpack(rand);
		Trainer t = new Trainer("Ash", bp);
		Pokemon p = new Pokedex(0.8).getPokemon();
		Battle b = new Battle(t, p, rand);
		menu.startBattle(b);
		assertEquals(b, menu.getBattle());
	}
	
	@Test
	public void directionTest() {
		BattleMenu menu = new BattleMenu();
		Backpack bp = new Backpack(rand);
		Trainer t = new Trainer("Ash", bp);
		Pokemon p = new Pokedex(0.8).getPokemon();
		Battle b = new Battle(t, p, rand);
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
		Pokemon p = new Pokedex(0.8).getPokemon();
		Battle b = new Battle(t, p, rand);
		menu.startBattle(b);
		assertEquals("",menu.getText());
		
	}
	
	@Test
	public void getMenuItemTest() {
		BattleMenu menu = new BattleMenu();
		Backpack bp = new Backpack(rand);
		Trainer t = new Trainer("Ash", bp);
		Pokemon p = new Pokedex(0.8).getPokemon();
		Battle b = new Battle(t, p, rand);
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
	
	@Test
	public void testMoveGetterandSetter() {
		BattleMenu m = new BattleMenu();
		assertEquals(BattleAction.Start, m.getMove());
		m.setMove(BattleAction.Bait);
		assertEquals(BattleAction.Bait, m.getMove());
	}
	
	@Test
	public void testResultAction(){
		BattleMenu menu = new BattleMenu();
		Backpack bp = new Backpack(rand);
		Trainer t = new Trainer("Ash", bp);
		Pokemon p = new Pokedex(rand).getPokemon();
		Battle b = new Battle(t,p,rand);
		menu.startBattle(b);
		
		menu.resultAction();
		assertEquals(menu.getMove(), BattleAction.End);
		
		menu.setBattleOver(false);
		b.setPokemonRanAway(true);
		
		menu.resultAction();
		assertEquals(menu.getMove(), BattleAction.PokeRun);
		
		b.setPokemonRanAway(true);
		menu.setBattleOver(false);
		menu.setMove(BattleAction.PokeRun);
		System.out.println("Again");
		menu.resultAction();
		assertEquals(menu.getMove(), BattleAction.End);
		assertEquals(menu.battleOver(), true);
	}

}
