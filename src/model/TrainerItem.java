package model;

import java.io.Serializable;

/**
 * Hierarchy for items to be used on the trainer
 *
 */
public abstract class TrainerItem extends Item implements Serializable
{
    private static final long serialVersionUID = -6802313021262429494L;

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