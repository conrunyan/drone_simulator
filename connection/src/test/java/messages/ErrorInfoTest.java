package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class ErrorInfoTest {

    @Test
    public void getKeyWord() {
        assertEquals("error", ErrorInfo.getKeyWord());
    }

    @Test
    public void getMessageType() {
        Message msg = new ErrorInfo("error");
        assertEquals("failure", msg.getMessageType());
    }

    @Test
    public void getMessageText() {
        Message msg = new ErrorInfo("error");
        assertEquals("error", msg.getMessageText());
    }

    @Test
    public void getMessageBad() {
        Message msg2 = new ErrorInfo("errorBAD");
        assertNull(msg2.getMessageText());
    }
}