package messages;

public class Left extends Message{

    public Left(String data) {
        matchPattern = getKeyWord() + " (\\d+)";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "left";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    protected String getMessageText() {
        return payload;
    }
}
