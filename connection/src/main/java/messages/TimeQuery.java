package messages;

public class TimeQuery extends Message{

    public TimeQuery(String data) {
        matchPattern = "time\\?$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "time?";
    }

    @Override
    public String getMessageType() {
        return "information";
    }

    public String getMessageText() {
        return payload;
    }
}
