package model;

import java.io.Serializable;

/**
 * The inheritance hierarchy for any item used on a pokemon in-game
 * @author Joshua Raphael
 */
public abstract class PokemonItem extends Item implements Serializable
{
    private static final long serialVersionUID = -4451901954314653025L;

    /**
     * Constructs the new pokemon item and sets the name and description of the superclass
     * @param name
     *     String name of the item
     * @param description
     *     String description of the item
     */
    public PokemonItem( String name, String description )
    {
        super( name, description );
    };
    /**
     * Abstract method for using an item on a pokemon
     * @param pokemon
     *    Pokemon to use the item on
     */
    public abstract boolean use( Pokemon pokemon );
}