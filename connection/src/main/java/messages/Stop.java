package messages;

public class Stop extends Message{

    public Stop(String data) {
        matchPattern = getKeyWord() + "$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "stop";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    public String getMessageText() {
        return payload;
    }
}
