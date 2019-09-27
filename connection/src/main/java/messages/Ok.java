package messages;

public class Ok extends Message{

    public Ok(String data) {
        matchPattern = "ok$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "ok";
    }

    @Override
    public String getMessageType() {
        return "information";
    }

    public String getMessageText() {
        return payload;
    }
}
