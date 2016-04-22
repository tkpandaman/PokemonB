package tests;

import static org.junit.Assert.*;
import java.io.IOException;
import java.util.Random;

import org.junit.Test;

import model.pokemon.Charizard;
import model.Pokeball;

public class TestPokeball extends SerializableTestCase
{
    @Test
    public void testPokeballGetName()
    {
        Pokeball p = new Pokeball( new Random() );
        assertEquals( "Pokeball", p.getName() );
    }
    @Test
    public void testPokeballGetDescription()
    {
        Pokeball p = new Pokeball( new Random() );
        assertEquals( "Pokeball", p.getName() );
        assertEquals( "throw at a pokemon to try and capture it", p.getDescription() );
    }
    @Test
    public void testPokeballNotCapturePokemon()
    {
        Charizard charizard = new Charizard();
        Pokeball p = new Pokeball( new Random(1234L) ); // controlled random pokeball that will not catch
        assertFalse( p.use( 0.0 ) );
    }
    @Test
    public void testPokeballCapturePokemon()
    {
        Charizard charizard = new Charizard();
        Pokeball p = new Pokeball( new Random( 12L ) ); // controlled random pokeball that will catch
        assertTrue( p.use( 1.0 ) );
    }
    @Test
    public void testPokeballSerializable() throws ClassNotFoundException, IOException
    {
        this.assertObjectSerializable( new Pokeball( new Random() ) );
    }
    @Test
    public void testPokeballNotEqualToObject()
    {
        Charizard charizard = new Charizard();
        Pokeball p = new Pokeball( new Random( 1234L ) );
        assertFalse( p.equals( charizard ) );
        assertTrue( p.equals( p ) );
    }
}