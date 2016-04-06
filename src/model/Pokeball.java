package model;

import java.io.Serializable;
import java.util.Random;
import model.pokemon.*;

public class Pokeball extends PokemonItem implements Serializable
{
    Random random;
    public Pokeball( Random rand )
    {
        super( "Pokeball", "throw at a pokemon to try and capture it" );
        random = rand;
    }

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