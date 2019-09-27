package messages;

public class Up extends Message{

    public Up(String data) {
        matchPattern = getKeyWord() + " (\\d+)$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "up";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    public String getMessageText() {
        return payload;
    }
}
