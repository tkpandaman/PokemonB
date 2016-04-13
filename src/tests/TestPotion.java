package tests;

import static org.junit.Assert.*;
import java.io.IOException;
import org.junit.Test;

import model.Charizard;
import model.Potion;

public class TestPotion extends SerializableTestCase
{   
    @Test
    public void testPotionGetName()
    {
        Potion potion = new Potion();
        assertEquals( "Potion", potion.getName() );
    }
    @Test
    public void testPotionGetDescription()
    {
        Potion potion = new Potion();
        assertEquals( "Heals a pokemon's health by 20 HP", potion.getDescription() );
    }
    @Test
    public void testPotionHealFull20Points()
    {
        Charizard charizard = new Charizard();
        Potion potion = new Potion();
        assertEquals( 120, charizard.getCurHP() );
        charizard.takeDamage( 30 );
        assertEquals( 90, charizard.getCurHP() );
        assertTrue( potion.use( charizard ) );
        assertEquals( 110, charizard.getCurHP() );
    }
    @Test
    public void testPotionNotHealFull20Points()
    {
        Charizard charizard = new Charizard();
        Potion potion = new Potion();
        assertEquals( 120, charizard.getCurHP() );
        charizard.takeDamage( 10 );
        assertEquals( 110, charizard.getCurHP() );
        assertTrue( potion.use( charizard ) );
        assertEquals( 120, charizard.getCurHP());
        assertFalse( potion.use( charizard ) );
    }
    @Test
    public void testPotionNotHealPokemonIfNoHPLost()
    {
        Charizard charizard = new Charizard();
        Potion potion = new Potion();
        assertEquals( 120, charizard.getCurHP() );
        assertFalse( potion.use( charizard ) );
    }
    @Test
    public void testPotionSerializable() throws ClassNotFoundException, IOException
    {
        this.assertObjectSerializable( new Potion() );
    }
    @Test
    public void testPotionNotEqualToObject()
    {
        Charizard charizard = new Charizard();
        Potion potion = new Potion();
        assertFalse( potion.equals( charizard ) );
        assertTrue( potion.equals( potion ) );
    }
}