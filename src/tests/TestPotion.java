package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import model.Potion;
import model.pokemon.Charizard;

public class TestPotion
{   
    @Test
    public void testGetters()
    {
        Potion potion = new Potion();
        assertEquals( "Potion", potion.getName() );
        assertEquals( "Heals a pokemon's health by 20 HP", potion.getDescription() );
    }
    @Test
    public void testPotion()
    {
        Charizard charizard = new Charizard();
        Potion potion = new Potion();
        assertEquals( 120, charizard.getCurHP() );
        charizard.takeDamage( 30 );
        assertEquals( 90, charizard.getCurHP() );
        assertTrue( potion.use( charizard ) );
        assertEquals( 110, charizard.getCurHP() );
        assertTrue( potion.use( charizard ) );
        assertEquals( 120, charizard.getCurHP());
        assertFalse( potion.use( charizard ) );
        
    }
}