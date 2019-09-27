package messages;

public class SpeedQuery extends Message{

    public SpeedQuery(String data) {
        matchPattern = "speed\\?$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "speed?";
    }

    @Override
    public String getMessageType() {
        return "information";
    }

    public String getMessageText() {
        return payload;
    }
}
