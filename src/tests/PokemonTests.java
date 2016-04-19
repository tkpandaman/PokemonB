package tests;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import model.pokemon.Arbok;
import model.pokemon.Beedrill;
import model.pokemon.Butterfree;
import model.pokemon.Charizard;
import model.pokemon.Pidgeot;
import model.pokemon.Pikachu;
import model.pokemon.PokemonType;
import model.pokemon.Snorlax;
import model.pokemon.Spearow;
import model.pokemon.Squirtle;
import model.pokemon.Voltorb;

public class PokemonTests extends SerializableTestCase{
	
	@Test
	public void VoltorbTest() throws ClassNotFoundException, IOException{
		Voltorb v = new Voltorb();
		
		this.assertObjectSerializable(v);
		
		assertEquals(PokemonType.VOLTORB, v.getType());
		assertEquals(99, v.getCurHP());
		assertEquals(99, v.getMaxHP());
		assertEquals(3, v.getTurnsTillFlee());
		assertEquals(25, v.getLikelyRun());
		v.takeDamage(2);
		assertEquals(97, v.getCurHP());
	}
	
	@Test
	public void SnorlaxTest() throws ClassNotFoundException, IOException{
		Snorlax s = new Snorlax();

		this.assertObjectSerializable(s);
		
		assertEquals(PokemonType.SNORLAX, s.getType());
		assertEquals(150, s.getCurHP());
		assertEquals(150, s.getMaxHP());
		assertEquals(6, s.getTurnsTillFlee());
		assertEquals(20, s.getLikelyRun());
		s.takeDamage(2);
		assertEquals(148, s.getCurHP());
	}
	
	@Test
	public void CharizardTest() throws ClassNotFoundException, IOException{
		Charizard c = new Charizard();
		
		this.assertObjectSerializable(c);
		
		assertEquals(PokemonType.CHARIZARD, c.getType());
		assertEquals(120, c.getCurHP());
		assertEquals(120, c.getMaxHP());
		assertEquals(10, c.getTurnsTillFlee());
		assertEquals(50, c.getLikelyRun());
		c.takeDamage(2);
		assertEquals(118, c.getCurHP());
	}
	
	@Test
	public void ArbokTest() throws ClassNotFoundException, IOException{
		Arbok p = new Arbok();
		
		this.assertObjectSerializable(p);
		
		assertEquals(PokemonType.ARBOK, p.getType());
		assertEquals(90, p.getCurHP());
		assertEquals(90, p.getMaxHP());
		assertEquals(3, p.getTurnsTillFlee());
		assertEquals(70, p.getLikelyRun());
		p.takeDamage(2);
		assertEquals(88, p.getCurHP());
	}
	
	@Test
	public void BeedrillTest() throws ClassNotFoundException, IOException{
		Beedrill p = new Beedrill();
		
		this.assertObjectSerializable(p);
		
		assertEquals(PokemonType.BEEDRILL, p.getType());
		assertEquals(130, p.getCurHP());
		assertEquals(130, p.getMaxHP());
		assertEquals(10, p.getTurnsTillFlee());
		assertEquals(10, p.getLikelyRun());
		p.takeDamage(20);
		assertEquals(110, p.getCurHP());
	}
	
	@Test
	public void ButterfreeTest() throws ClassNotFoundException, IOException{
		Butterfree p = new Butterfree();
		
		this.assertObjectSerializable(p);
		
		assertEquals(PokemonType.BUTTERFREE, p.getType());
		assertEquals(111, p.getCurHP());
		assertEquals(111, p.getMaxHP());
		assertEquals(6, p.getTurnsTillFlee());
		assertEquals(33, p.getLikelyRun());
		p.takeDamage(20);
		assertEquals(91, p.getCurHP());
	}
	
	@Test
	public void PidgeotTest() throws ClassNotFoundException, IOException{
		Pidgeot p = new Pidgeot();
		
		this.assertObjectSerializable(p);
		
		assertEquals(PokemonType.PIDGEOT, p.getType());
		assertEquals(75, p.getCurHP());
		assertEquals(75, p.getMaxHP());
		assertEquals(3, p.getTurnsTillFlee());
		assertEquals(55, p.getLikelyRun());
		p.takeDamage(10);
		assertEquals(65, p.getCurHP());
	}
	
	@Test
	public void PikachuTest() throws ClassNotFoundException, IOException{
		Pikachu p = new Pikachu();
		
		this.assertObjectSerializable(p);
		
		assertEquals(PokemonType.PIKACHU, p.getType());
		assertEquals(200, p.getCurHP());
		assertEquals(200, p.getMaxHP());
		assertEquals(20, p.getTurnsTillFlee());
		assertEquals(5, p.getLikelyRun());
		p.takeDamage(100);
		assertEquals(100, p.getCurHP());
	}
	
	@Test
	public void SpearowTest() throws ClassNotFoundException, IOException{
		Spearow p = new Spearow();
		
		this.assertObjectSerializable(p);
		
		assertEquals(PokemonType.SPEAROW, p.getType());
		assertEquals(60, p.getCurHP());
		assertEquals(60, p.getMaxHP());
		assertEquals(2, p.getTurnsTillFlee());
		assertEquals(25, p.getLikelyRun());
		p.takeDamage(7);
		assertEquals(53, p.getCurHP());
	}
	
	@Test
	public void SquirtleTest() throws ClassNotFoundException, IOException{
		Squirtle p = new Squirtle();
		
		this.assertObjectSerializable(p);
		
		assertEquals(PokemonType.SQUIRTLE, p.getType());
		assertEquals(105, p.getCurHP());
		assertEquals(105, p.getMaxHP());
		assertEquals(4, p.getTurnsTillFlee());
		assertEquals(35, p.getLikelyRun());
		p.takeDamage(5);
		assertEquals(100, p.getCurHP());
	}
	
}
