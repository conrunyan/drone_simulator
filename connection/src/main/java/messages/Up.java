package messages;

public class Up extends Message{

    public Up(String data) {
        matchPattern = getKeyWord() + " (\\d+)";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "up";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    protected String getMessageText() {
        return payload;
    }
}
