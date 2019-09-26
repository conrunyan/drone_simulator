package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class DownTest {

    @Test
    public void getKeyWord() {
        assertEquals("down", Down.getKeyWord());
    }

    @Test
    public void getMessageType() {
        Down bq = new Down("down 180");
        assertEquals("directive", bq.getMessageType());
    }

    @Test
    public void getMessageText() {
        Down bq = new Down("down 180");
        assertEquals("down 180", bq.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        Down bq = new Down("cwX 180");
        assertNull(bq.getMessageText());
    }
}