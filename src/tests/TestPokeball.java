package tests;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import model.Charizard;
import model.Pokeball;

public class TestPokeball
{
    @Test
    public void testGetters()
    {
        Pokeball p = new Pokeball( new Random() );
        assertEquals( "Pokeball", p.getName() );
        assertEquals( "throw at a pokemon to try and capture it", p.getDescription() );
    }
    @Test
    public void testPokeball()
    {
        Charizard charizard = new Charizard();
        Pokeball p = new Pokeball( new Random(12L) ); // controlled random pokeball that will not catch
        assertFalse( p.use( charizard ) );
        Pokeball p2 = new Pokeball( new Random( 1234L ) ); // controlled random pokeball that will catch
        assertTrue( p2.use( charizard ) );
    }
    
}