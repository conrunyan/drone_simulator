package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class BackTest {

    @Test
    public void getKeyWord() {
        assertEquals("back", Back.getKeyWord());
    }

    @Test
    public void getMessageType() {
        Back test = new Back("back 10");
        assertEquals("directive", test.getMessageType());
    }

    @Test
    public void getMessageText() {
        Back test = new Back("back 10");
        assertEquals("back 10", test.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        Back test = new Back("back XX");
        assertNull(test.getMessageText());
    }
}