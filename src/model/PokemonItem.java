package model;

public abstract class PokemonItem extends Item
{
    public PokemonItem( String name, String description )
    {
        super( name, description );
    };
    public abstract void use( Pokemon pokemon );
}