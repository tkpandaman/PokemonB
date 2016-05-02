package model;

import java.io.Serializable;
import java.util.Random;

/**
 * Pokeball item used to capture pokemon
 * @author Joshua Raphael
 *
 */
public class Pokeball extends PokemonItem implements Serializable
{
    private static final long serialVersionUID = 2656695956168857439L;
    
    private static final String STRING_NAME = "Pokeball";
    private static final String STRING_DESC = "throw at a pokemon to try and capture it" ;
   
    // pass in random value so we can test capturing
    public Random random;
    
    /**
     * Construct the pokeball object
     * @param rand
     *     Random value passed in to randomly catch pokemon
     */
    public Pokeball( Random rand )
    {
        super( STRING_NAME, STRING_DESC );
        random = rand;
    }

    /**
     * Use the pokeball on a pokemon
     */
    @Override
    public boolean use( Object catchRate )
    {
        double rand = random.nextDouble();
        if( rand <= (double)catchRate )
        {
            return true; // captured
        }
        return false; // escaped
    }
}