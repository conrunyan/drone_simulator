package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class GoTest {

    @Test
    public void getKeyWord() {
        assertEquals("go", Go.getKeyWord());
    }

    @Test
    public void getMessageType() {
        Go bq = new Go("go 10 10 20 5");
        assertEquals("directive", bq.getMessageType());
    }

    @Test
    public void getMessageText() {
        Go bq = new Go("go 10 10 20 5");
        assertEquals("go 10 10 20 5", bq.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        Go bq = new Go("go 10 10 20 5 981 981");
        assertNull(bq.getMessageText());
    }
}