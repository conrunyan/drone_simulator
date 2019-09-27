package messages;

public class Back extends Message{

    public Back(String data) {
        matchPattern = getKeyWord() + " (\\d+)$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "back";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    public String getMessageText() {
        return payload;
    }
}
