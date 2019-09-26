package messages;

import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Message {
    private boolean valid = true;
    String payload = null;
    String matchPattern;

    public static Message decode(byte[] bytes, int offset, int length) {
        Message message = null;
        if (bytes!=null) {
            length = Math.min(bytes.length, offset + length);

            String data = new String(bytes, offset, length, StandardCharsets.UTF_8);
            data = data.trim();

            if (data.startsWith(Status.getKeyWord()))
                message = new Status(data);

            // TODO: decode all of the other kinds of messages based on what the message starts with.  If it doesn't
            //       start with a recognized key work, assume it is an info message (i.e., a reply to a query)
        }
        return message;
    }

    public byte[] encode() { return getMessageText().getBytes(StandardCharsets.UTF_8); }

    public abstract String getMessageType();

    protected String getMessageText() { return getMessageType(); }

    public boolean isValid() { return valid; }

    protected void setIsValid(boolean valid) { this.valid = valid; }

    protected void parseIncomingData(String data) {
        if (data==null || data.isEmpty())
            return;

        Pattern pat = Pattern.compile(matchPattern);
        Matcher match = pat.matcher(data);

        if (match.find()) {
            setIsValid(true);
            payload = data;
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getClass().getSimpleName(), getMessageText());
    }

}
