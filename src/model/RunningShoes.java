package model;


import java.io.Serializable;

/**
 * Creates running shoes for the trainer to double their speed
 * @author Joshua Raphael
 */
public class RunningShoes extends TrainerItem implements Serializable
{
    private static final long serialVersionUID = 7039593018652493246L;

    /**
     * Construct the running shoes item
     */
    public RunningShoes()
    {
        super( "Running Shoes", "Doubles the trainers walking speed" );
    }

    /**
     * Double the trainers speed
     * @param trainer
     *     Trainer to use the item on
     */
    @Override
    public void use( Trainer trainer )
    {
        trainer.setSpeed( 2.0 );
    }
    
}