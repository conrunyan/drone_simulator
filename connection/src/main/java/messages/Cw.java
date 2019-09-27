package messages;

public class Cw extends Message {

    public Cw(String data) {
        matchPattern = getKeyWord() + "(\\d+)$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "cw ";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    public String getMessageText() {
        return payload;
    }
}
