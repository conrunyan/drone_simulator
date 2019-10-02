package messages;

public class Flip extends Message{

    public Flip(String data) {
        matchPattern = getKeyWord() + " ([f,b,l,r])$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "flip";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    public String getMessageText() {
        return payload;
    }
}
