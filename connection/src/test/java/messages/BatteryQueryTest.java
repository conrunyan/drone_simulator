package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class BatteryQueryTest {

    @Test
    public void getKeyWord() {
        assertEquals("battery?", BatteryQuery.getKeyWord());
    }

    @Test
    public void getMessageType() {
        BatteryQuery bq = new BatteryQuery("battery?");
        assertEquals("information", bq.getMessageType());
    }

    @Test
    public void getMessageText() {
        BatteryQuery bq = new BatteryQuery("battery?");
        assertEquals("battery?", bq.getMessageText());
        assertTrue(bq.isValid());
    }

    @Test
    public void getMessageTextNegative() {
        BatteryQuery bq = new BatteryQuery("NOT A BATTERY!");
        assertNull(bq.getMessageText());
        assertFalse(bq.isValid());
    }
}