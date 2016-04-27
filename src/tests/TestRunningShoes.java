package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Random;

import org.junit.Test;

import model.Backpack;
import model.RunningShoes;
import model.Trainer;

public class TestRunningShoes extends SerializableTestCase
{
	Random r = new Random(1337L);
	
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
    	Backpack bp = new Backpack(r);
        Trainer t = new Trainer("Bob", bp);
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
    @Test
    public void testRunningShoesNotEqualToObject()
    {
    	Backpack bp = new Backpack(r);
        Trainer t = new Trainer("Bob", bp);
        RunningShoes shoe = new RunningShoes();
        assertFalse( shoe.equals( t ) );
        assertTrue( shoe.equals( shoe ) );
    }
}