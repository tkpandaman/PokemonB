package model;

public abstract class TrainerItem extends Item
{
    public TrainerItem( String name, String description )
    {
        super( name, description );
    }
    public abstract void use( Trainer trainer );
}