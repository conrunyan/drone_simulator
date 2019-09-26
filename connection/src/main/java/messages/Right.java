package messages;

public class Right extends Message{

    public Right(String data) {
        matchPattern = getKeyWord() + " (\\d+)";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "right";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    protected String getMessageText() {
        return payload;
    }
}
