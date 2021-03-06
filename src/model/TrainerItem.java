package model;

/**
 * Hierarchy for items to be used on the trainer
 * @author Joshua Raphael
 */
public abstract class TrainerItem extends Item
{
    private static final long serialVersionUID = -255055332706335957L;
    /**
     * Constructs a new trainer item
     * @param name
     *     String name of the item
     * @param description
     *     String description of the item
     */
    public TrainerItem( String name, String description )
    {
        super( name, description );
    }
    /**
     * Outline for the use method to use the item on a trainer
     * @param trainer
     *     Trainer to have the item used on
     */
    public abstract void use( Trainer trainer );
}