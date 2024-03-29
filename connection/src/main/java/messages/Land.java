package messages;

public class Land extends Message{

    public Land(String data) {
        matchPattern = getKeyWord() + "$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "land";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    public String getMessageText() {
        return payload;
    }
}
