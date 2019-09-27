package messages;

public class Go extends Message{

    public Go(String data) {
        matchPattern = getKeyWord() + " (\\d+ \\d+ \\d+ \\d+)$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "go";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    public String getMessageText() {
        return payload;
    }
}
