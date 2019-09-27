package messages;

public class Takeoff extends Message{

    public Takeoff(String data) {
        matchPattern = getKeyWord() + "$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "takeoff";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    public String getMessageText() {
        return payload;
    }
}
