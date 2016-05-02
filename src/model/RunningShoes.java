package model;


import java.io.Serializable;

/**
 * Creates running shoes for the trainer to double their speed
 * @author Joshua Raphael
 */
public class RunningShoes extends TrainerItem implements Serializable
{
    private static final long serialVersionUID = 7039593018652493246L;
    
    private static final double SET_SPEED = 3.0;
    private static final String STRING_NAME = "Running Shoes";
    private static final String STRING_DESC = "Doubles the trainers walking speed";

    /**
     * Construct the running shoes item
     */
    public RunningShoes()
    {
        super( STRING_NAME, STRING_DESC );
    }

    /**
     * Double the trainers speed
     * @param trainer
     *     Trainer to use the item on
     */
    @Override
    public void use( Trainer trainer )
    {
        trainer.setSpeed( SET_SPEED );
    }
    
}