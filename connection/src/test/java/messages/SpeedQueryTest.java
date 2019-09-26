package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpeedQueryTest {

    @Test
    public void getKeyWord() {
        assertEquals("speed?", SpeedQuery.getKeyWord());
    }

    @Test
    public void getMessageType() {
        SpeedQuery bq = new SpeedQuery("speed?");
        assertEquals("information", bq.getMessageType());
    }

    @Test
    public void getMessageText() {
        SpeedQuery bq = new SpeedQuery("speed?");
        assertEquals("speed?", bq.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        SpeedQuery bq = new SpeedQuery("speed? q23rq2f34");
        assertNull(bq.getMessageText());
    }
}