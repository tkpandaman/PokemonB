package tests;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import model.RunningShoes;
import model.Trainer;

public class TestRunningShoes
{
    @Test
    public void testGetters()
    {
        RunningShoes shoe = new RunningShoes();
        assertEquals( "Running Shoes", shoe.getName() );
        assertEquals( "Doubles the trainers walking speed", shoe.getDescription() );
    }
    @Test
    public void testShoes()
    {
        Trainer t = new Trainer("Bob");
        RunningShoes shoe = new RunningShoes();
        assertEquals( 1.0, t.getSpeed(), 0.000000000000000000001 );
        shoe.use( t );
        assertEquals( 2.0, t.getSpeed(), 0.000000000000000000001 );
    };
}