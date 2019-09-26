package messages;

public class Down extends Message {

    public Down(String data) {
        matchPattern = getKeyWord() + " (\\d+)$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "down";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    protected String getMessageText() {
        return payload;
    }
}
