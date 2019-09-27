package messages;

public class Forward extends Message{

    public Forward(String data) {
        matchPattern = getKeyWord() + " (\\d+)$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "forward";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    public String getMessageText() {
        return payload;
    }
}
