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
    public boolean use( Object pokemon )
    {
        if( ((Pokemon)pokemon).getCurHP() >= ((Pokemon)pokemon).getMaxHP() )
        {
            return false;
        };
        if( ((Pokemon)pokemon).getCurHP() + 20 > ((Pokemon)pokemon).getMaxHP() )
        {
            int diff = ((Pokemon)pokemon).getMaxHP() - ((Pokemon)pokemon).getCurHP();
            ((Pokemon)pokemon).takeDamage( -diff );
            return true;
        }
        ((Pokemon)pokemon).takeDamage( -20 );
        return true;
    }
}