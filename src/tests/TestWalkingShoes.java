package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.Random;
import org.junit.Test;

import model.Backpack;
import model.WalkingShoes;
import model.Trainer;

public class TestWalkingShoes extends SerializableTestCase
{
    @Test
    public void testRunningShoesGetName()
    {
        WalkingShoes shoe = new WalkingShoes();
        assertEquals( "Walking Shoes", shoe.getName() );
    }
    @Test
    public void testRunningShoesGetDescription()
    {
        WalkingShoes shoe = new WalkingShoes();
        assertEquals( "Sets the trainers walking speed to default", shoe.getDescription() );
    }
    @Test
    public void testRunningShoesIncreaseSpeed()
    {
        Backpack bp = new Backpack(new Random());
        Trainer t = new Trainer("Bob", bp);
        t.setSpeed( 2.0 );
        WalkingShoes shoe = new WalkingShoes();
        assertEquals( 2.0, t.getSpeed(), 0.000000000000000000001 );
        shoe.use( t );
        assertEquals( 1.0, t.getSpeed(), 0.000000000000000000001 );
    };
    @Test
    public void testRunningShoesSerializable() throws ClassNotFoundException, IOException
    {
        this.assertObjectSerializable( new WalkingShoes() );
    }
    @Test
    public void testRunningShoesNotEqualToObject()
    {
        Backpack bp = new Backpack(new Random());
        Trainer t = new Trainer("Bob", bp);
        WalkingShoes shoe = new WalkingShoes();
        assertFalse( shoe.equals( t ) );
        assertTrue( shoe.equals( shoe ) );
    }
}