package messages;

public class Left extends Message{

    public Left(String data) {
        matchPattern = getKeyWord() + " (\\d+)$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "left";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    public String getMessageText() {
        return payload;
    }
}
