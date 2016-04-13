package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.pokemon.Charizard;
import model.pokemon.Snorlax;
import model.pokemon.Voltorb;
import junit.framework.TestCase;

public class PokemonTests {
	
	@Test
	public void PokemonTests(){
		Snorlax s = new Snorlax();
		
		assertEquals("Snorlax", s.getType());
		assertEquals(150, s.getCurHP());
		assertEquals(150, s.getMaxHP());
		assertEquals(6, s.getTurnsTillFlee());
		assertEquals(20, s.getLikelyRun());
		s.takeDamage(2);
		assertEquals(148, s.getCurHP());
		
		Voltorb v = new Voltorb();
		
		assertEquals("Voltorb", v.getType());
		assertEquals(100, v.getCurHP());
		assertEquals(100, v.getMaxHP());
		assertEquals(10, v.getTurnsTillFlee());
		assertEquals(30, v.getLikelyRun());
		v.takeDamage(2);
		assertEquals(98, v.getCurHP());
		
		Charizard c = new Charizard();
		
		assertEquals("Charizard", c.getType());
		assertEquals(120, c.getCurHP());
		assertEquals(120, c.getMaxHP());
		assertEquals(10, c.getTurnsTillFlee());
		assertEquals(50, c.getLikelyRun());
		c.takeDamage(2);
		assertEquals(118, c.getCurHP());
	}
	
}
