package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class StopTest {

    @Test
    public void getKeyWord() {
        assertEquals("stop", Stop.getKeyWord());
    }

    @Test
    public void getMessageType() {
        Stop bq = new Stop("stop");
        assertEquals("directive", bq.getMessageType());
    }

    @Test
    public void getMessageText() {
        Stop bq = new Stop("stop");
        assertEquals("stop", bq.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        Stop bq = new Stop("speed? q23rq2f34");
        assertNull(bq.getMessageText());
    }
}