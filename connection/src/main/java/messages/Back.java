package messages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Back extends Message{

    public Back(String data) {
        matchPattern = getKeyWord() + " (\\d+)";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "back";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    protected String getMessageText() {
        return payload;
    }
}
