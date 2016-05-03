package tests;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.junit.Test;
import model.WinCondition;

public class TestWinCondition extends SerializableTestCase
{
    @Test
    public void testWinConditionValues()
    {
        assertEquals( WinCondition.valueOf( "STEPS" ), model.WinCondition.STEPS );
        assertEquals( WinCondition.valueOf( "POKEMON" ), model.WinCondition.POKEMON );
        assertEquals( WinCondition.valueOf( "SAFARIBALLS" ), model.WinCondition.SAFARIBALLS );
    }
    @Test
    public void serializeableTest() throws ClassNotFoundException, IOException {
        WinCondition s = model.WinCondition.STEPS;
        this.assertObjectSerializable(s);
    }
}