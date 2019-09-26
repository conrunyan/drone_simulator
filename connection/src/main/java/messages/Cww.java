package messages;

public class Cww extends Message {

    public Cww(String data) {
        matchPattern = getKeyWord() + " (\\d+)";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "cww";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    protected String getMessageText() {
        return payload;
    }
}
