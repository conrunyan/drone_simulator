package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class LeftTest {

    @Test
    public void getKeyWord() {
        assertEquals("left", Left.getKeyWord());
    }

    @Test
    public void getMessageType() {
        Left bq = new Left("left");
        assertEquals("directive", bq.getMessageType());
    }

    @Test
    public void getMessageText() {
        Left bq = new Left("left 10");
        assertEquals("left 10", bq.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        Left bq = new Left("left awef aw3f");
        assertNull(bq.getMessageText());
    }
}