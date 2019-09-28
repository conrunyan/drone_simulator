package messages;

public class ErrorInfo extends Message {

    public ErrorInfo(String data) {
        matchPattern = getKeyWord() + "$";
        parseIncomingData(data);
    }

    public static String getKeyWord() {
        return "error";
    }

    @Override
    public String getMessageType() {
        return "failure";
    }

    public String getMessageText() {
        return payload;
    }

}
