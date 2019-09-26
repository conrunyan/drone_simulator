package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class UpTest {

    @Test
    public void getKeyWord() {
        assertEquals("up", Up.getKeyWord());
    }

    @Test
    public void getMessageType() {
        Up bq = new Up("up");
        assertEquals("directive", bq.getMessageType());
    }

    @Test
    public void getMessageText() {
        Up bq = new Up("up 159");
        assertEquals("up 159", bq.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        Up bq = new Up("timeaw? q23rq2f34");
        assertNull(bq.getMessageText());
    }
}