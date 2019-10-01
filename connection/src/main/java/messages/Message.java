package messages;

import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Message {
    private boolean valid = false;
    String payload = null;
    ArrayList<String> payloadModifiers = new ArrayList<String>();
    InetAddress remoteIPAddr = null;
    int remotePort = 0;
    String matchPattern;

    public static Message decode(byte[] bytes, int offset, int length) {
        Message message = null;
        if (bytes!=null) {
            length = Math.min(bytes.length, offset + length);

            String data = new String(bytes, offset, length, StandardCharsets.UTF_8);
            data = data.trim();

            if (data.startsWith(Status.getKeyWord()))
                message = new Status(data);
            else if (data.startsWith(Back.getKeyWord()))
                message = new Back(data);
            else if (data.startsWith(BatteryQuery.getKeyWord()))
                message = new BatteryQuery(data);
            else if (data.startsWith(Command.getKeyWord()))
                message = new Command(data);
            else if (data.startsWith(Cw.getKeyWord()))
                message = new Cw(data);
            else if (data.startsWith(Cww.getKeyWord()))
                message = new Cww(data);
            else if (data.startsWith(Down.getKeyWord()))
                message = new Down(data);
            else if (data.startsWith(Flip.getKeyWord()))
                message = new Flip(data);
            else if (data.startsWith(Forward.getKeyWord()))
                message = new Forward(data);
            else if (data.startsWith(Go.getKeyWord()))
                message = new Go(data);
            else if (data.startsWith(Land.getKeyWord()))
                message = new Land(data);
            else if (data.startsWith(Left.getKeyWord()))
                message = new Left(data);
            else if (data.startsWith(Ok.getKeyWord()))
                message = new Ok(data);
            else if (data.startsWith(Right.getKeyWord()))
                message = new Right(data);
            else if (data.startsWith(SpeedQuery.getKeyWord()))
                message = new SpeedQuery(data);
            else if (data.startsWith(Stop.getKeyWord()))
                message = new Stop(data);
            else if (data.startsWith(Takeoff.getKeyWord()))
                message = new Takeoff(data);
            else if (data.startsWith(TimeQuery.getKeyWord()))
                message = new TimeQuery(data);
            else if (data.startsWith(Up.getKeyWord()))
                message = new Up(data);
            else if (data.startsWith(ErrorInfo.getKeyWord()))
                message = new ErrorInfo(data);
            else {
                System.out.println("ERROR: Invalid message: " + data);
                message = new ErrorInfo(data);
            }
        }
        return message;
    }

    public byte[] encode() { return getMessageText().getBytes(StandardCharsets.UTF_8); }

    public abstract String getMessageType();

    public String getMessageText() {
        return getMessageText();
    }

    public InetAddress getRemoteIPAddr() {
        return this.remoteIPAddr;
    }

    public int getRemotePort() {
        return this.remotePort;
    }

    public void setRemoteIPAddr(InetAddress remoteIPAddr) {
        this.remoteIPAddr = remoteIPAddr;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

    public ArrayList<String> getPayloadModifiers() {
        parseIncomingData(this.payload);
        return this.payloadModifiers;
    }

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
            if (match.groupCount() > 0) {
                for (int i = 1; i < match.groupCount() + 1; i++) {
                    payloadModifiers.add(match.group(i));
                }
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%s %s", this.getClass().getSimpleName(), getMessageText());
    }

}
