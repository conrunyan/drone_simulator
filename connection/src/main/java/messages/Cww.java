package messages;

public class Cww extends Message {

    public Cww(String data) {
        matchPattern = getKeyWord() + "(\\d+)$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "cww ";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    public String getMessageText() {
        return payload;
    }
}
