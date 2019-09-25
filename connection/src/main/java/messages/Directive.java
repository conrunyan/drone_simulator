package messages;

public class Directive extends Message {

    public static String getKeyWord() {
        return "dir";
    }

    private String messageType;

    public Directive(String data) {

    }

    @Override
    public String getMessageType() {
        return null;
    }

    private void parseIncomingData(String data) {

    }

    private void determineIfValid() {

    }
}
