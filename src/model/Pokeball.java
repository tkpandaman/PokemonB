package model;

import java.io.Serializable;
import java.util.Random;

import model.pokemon.Pokemon;

/**
 * Pokeball item used to capture pokemon
 * @author Joshua Raphael
 *
 */
public class Pokeball extends PokemonItem implements Serializable
{
    // pass in random value so we can test capturing
    Random random;
    /**
     * Construct the pokeball object
     * @param rand
     *     Random value passed in to randomly catch pokemon
     */
    public Pokeball( Random rand )
    {
        super( "Pokeball", "throw at a pokemon to try and capture it" );
        random = rand;
    }

    /**
     * Use the pokeball on a pokemon
     */
    @Override
    public boolean use( Pokemon pokemon )
    {
        int rand = random.nextInt();
        System.out.println( rand );
        if( rand % 2 == 0 )
        {
            return true; // captured
        }
        return false; // escaped
    }
}