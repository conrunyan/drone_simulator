package messages;

public class BatteryQuery extends Message{

    public BatteryQuery(String data) {
        matchPattern = getKeyWord();
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "battery?";
    }

    @Override
    public String getMessageType() {
        return "information";
    }

    protected String getMessageText() {
        return payload;
    }
}
