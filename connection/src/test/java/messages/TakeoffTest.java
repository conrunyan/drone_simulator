package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class TakeoffTest {

    @Test
    public void getKeyWord() {
        assertEquals("takeoff", Takeoff.getKeyWord());
    }

    @Test
    public void getMessageType() {
        Takeoff bq = new Takeoff("takeoff");
        assertEquals("directive", bq.getMessageType());
    }

    @Test
    public void getMessageText() {
        Takeoff bq = new Takeoff("takeoff");
        assertEquals("takeoff", bq.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        Takeoff bq = new Takeoff("speed? q23rq2f34");
        assertNull(bq.getMessageText());
    }
}