package messages;

public class Command extends Message {

    public Command(String data) {
        // TODO: Implement constructor
    }

    public static String getKeyWord() {
        return "command";
    }

    @Override
    public String getMessageType() {
        return "directive";
    }

    protected String getMessageText() {
        // TODO: Implement getMessageText
        return getMessageType();
    }
}
