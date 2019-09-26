package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class RightTest {

    @Test
    public void getKeyWord() {
        assertEquals("right", Right.getKeyWord());
    }

    @Test
    public void getMessageType() {
        Right bq = new Right("right");
        assertEquals("directive", bq.getMessageType());
    }

    @Test
    public void getMessageText() {
        Right bq = new Right("right 10");
        assertEquals("right 10", bq.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        Right bq = new Right("right awef aw3f");
        assertNull(bq.getMessageText());
    }
}