package messages;

public class Command extends Message {

    public Command(String data) {
        matchPattern = getKeyWord() + "$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "command";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    public String getMessageText() {
        return payload;
    }
}
