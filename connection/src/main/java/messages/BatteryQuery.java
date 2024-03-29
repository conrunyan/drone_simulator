package messages;

public class BatteryQuery extends Message{

    public BatteryQuery(String data) {
        matchPattern = "battery\\?$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "battery?";
    }

    @Override
    public String getMessageType() {
        return "information";
    }

    public String getMessageText() {
        return payload;
    }
}
