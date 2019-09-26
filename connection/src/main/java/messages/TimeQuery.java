package messages;

public class TimeQuery extends Message{

    public TimeQuery(String data) {
        matchPattern = getKeyWord();
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "time?";
    }

    @Override
    public String getMessageType() {
        return "information";
    }

    protected String getMessageText() {
        return payload;
    }
}
