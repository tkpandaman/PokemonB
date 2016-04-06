package model;

import java.io.Serializable;
import model.pokemon.Pokemon;

/**
 * A potion item that heals a pokemons health by 20 points
 * @author Joshua Raphael
 */
public class Potion extends PokemonItem implements Serializable
{
    private static final long serialVersionUID = 6171109725928356151L;

    public Potion()
    {
        super( "Potion", "Heals a pokemon's health by 20 HP" );
    }

    @Override
    public boolean use( Pokemon pokemon )
    {
        if( pokemon.getCurHP() >= pokemon.getMaxHP() )
        {
            return false;
        };
        if( pokemon.getCurHP() + 20 > pokemon.getMaxHP() )
        {
            int diff = pokemon.getMaxHP() - pokemon.getCurHP();
            pokemon.takeDamage( -diff );
            return true;
        }
        pokemon.takeDamage( -20 );
        return true;
    }
    
}