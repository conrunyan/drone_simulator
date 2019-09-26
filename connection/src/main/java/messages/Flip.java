package messages;

public class Flip extends Message{

    public Flip(String data) {
        matchPattern = getKeyWord() + " ([u,d,l,r])$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "flip";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    protected String getMessageText() {
        return payload;
    }
}
