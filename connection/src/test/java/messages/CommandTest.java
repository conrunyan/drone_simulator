package messages;

import org.junit.Test;

import static org.junit.Assert.*;

public class CommandTest {

    @Test
    public void getKeyWord() {
        assertEquals("command", Command.getKeyWord());
    }

    @Test
    public void getMessageType() {
        Command bq = new Command("command");
        assertEquals("directive", bq.getMessageType());
    }

    @Test
    public void getMessageText() {
        Command bq = new Command("command");
        assertEquals("command", bq.getMessageText());
    }

    @Test
    public void getMessageTextNegative() {
        Command bq = new Command("BAD COMMAND");
        assertNull(bq.getMessageText());
    }
}