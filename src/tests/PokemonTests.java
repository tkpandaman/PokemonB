package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.pokemon.Charizard;
import model.pokemon.PokemonType;
import model.pokemon.Snorlax;
import model.pokemon.Voltorb;

public class PokemonTests {
	
	@Test
	public void PokemonTests(){
		Snorlax s = new Snorlax();
		
		assertEquals(PokemonType.SNORLAX, s.getType());
		assertEquals(120, s.getCurHP());
		assertEquals(120, s.getMaxHP());
		assertEquals(10, s.getTurnsTillFlee());
		assertEquals(50, s.getLikelyRun());
		s.takeDamage(2);
		assertEquals(118, s.getCurHP());
		
		Voltorb v = new Voltorb();
		
		assertEquals(PokemonType.VOLTORB, v.getType());
		assertEquals(120, v.getCurHP());
		assertEquals(120, v.getMaxHP());
		assertEquals(10, v.getTurnsTillFlee());
		assertEquals(50, v.getLikelyRun());
		v.takeDamage(2);
		assertEquals(118, v.getCurHP());
		
		Charizard c = new Charizard();
		
		assertEquals(PokemonType.CHARIZARD, c.getType());
		assertEquals(120, c.getCurHP());
		assertEquals(120, c.getMaxHP());
		assertEquals(10, c.getTurnsTillFlee());
		assertEquals(50, c.getLikelyRun());
		c.takeDamage(2);
		assertEquals(118, c.getCurHP());
	}
	
}
