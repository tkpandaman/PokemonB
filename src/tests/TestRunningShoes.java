package tests;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;
import model.RunningShoes;
import model.Trainer;

public class TestRunningShoes extends SerializableTestCase
{
    @Test
    public void testRunningShoesGetName()
    {
        RunningShoes shoe = new RunningShoes();
        assertEquals( "Running Shoes", shoe.getName() );
    }
    @Test
    public void testRunningShoesGetDescription()
    {
        RunningShoes shoe = new RunningShoes();
        assertEquals( "Doubles the trainers walking speed", shoe.getDescription() );
    }
    @Test
    public void testRunningShoesIncreaseSpeed()
    {
        Trainer t = new Trainer("Bob");
        RunningShoes shoe = new RunningShoes();
        assertEquals( 1.0, t.getSpeed(), 0.000000000000000000001 );
        shoe.use( t );
        assertEquals( 2.0, t.getSpeed(), 0.000000000000000000001 );
    };
    @Test
    public void testRunningShoesSerializable() throws ClassNotFoundException, IOException
    {
        this.assertObjectSerializable( new RunningShoes() );
    }
}