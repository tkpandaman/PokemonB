package model;


import java.io.Serializable;

/**
 * Creates running shoes for the trainer to double their speed
 * @author Joshua Raphael
 */
public class WalkingShoes extends TrainerItem implements Serializable
{
    private static final long serialVersionUID = 7039593018652493246L;
    private final static String name = "Walking Shoes";
    private final static String description = "Sets the trainers walking speed to default";
    /**
     * Construct the running shoes item
     */
    public WalkingShoes()
    {
        super( name, description );
    }

    /**
     * Returns the trainers original speed
     * @param trainer
     *     Trainer to use the item on
     */
    @Override
    public void use( Trainer trainer )
    {
        trainer.setSpeed( 1.0 );
    }
    
}