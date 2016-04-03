package model;

public class RunningShoes extends TrainerItem
{
    public RunningShoes()
    {
        super( "Running Shoes", "Doubles the trainers walking speed" );
    }

    @Override
    public void use( Trainer trainer )
    {
        trainer.setSpeed( 2.0 );
    }
}