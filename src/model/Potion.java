package model;

public class Potion extends PokemonItem
{

    public Potion()
    {
        super( "Potion", "Heals a pokemon's health by 20 HP" );
    }

    @Override
    public void use( Pokemon pokemon )
    {
        pokemon.setHP( pokemon.getHP() + 20 );
    }
    
}