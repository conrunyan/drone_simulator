package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class TimeQueryTest {

    @Test
    public void getKeyWord() {
        assertEquals("time?", TimeQuery.getKeyWord());
    }

    @Test
    public void getMessageType() {
        TimeQuery bq = new TimeQuery("time?");
        assertEquals("information", bq.getMessageType());
    }

    @Test
    public void getMessageText() {
        TimeQuery bq = new TimeQuery("time?");
        assertEquals("time?", bq.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        TimeQuery bq = new TimeQuery("timeaw? q23rq2f34");
        assertNull(bq.getMessageText());
    }
}