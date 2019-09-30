package state;

import messages.*;

import java.util.ArrayList;

public abstract class DroneStateHandler {

    public static DroneState handleMessage(Message msg, DroneState state) {

        ArrayList<String> payloadModifiers = msg.getPayloadModifiers();
        String data = msg.getMessageText();

        if (payloadModifiers.size() < 1) {
            return state;
        }
//
//        if (data.startsWith(Back.getKeyWord()))
//            System.out.println("H");
//        else if (data.startsWith(Command.getKeyWord()))
//            state.setInCommandMode(true);
//        else if (data.startsWith(Cw.getKeyWord()))
//            state.rotate(Integer.parseInt(payloadModifiers.get(0)));
//        else if (data.startsWith(Cww.getKeyWord()))
//            state.rotate(-Integer.parseInt(payloadModifiers.get(0)));
//        else if (data.startsWith(Down.getKeyWord()))
//            state.move(0, 0, Double.parseDouble(payloadModifiers.get(0)));
//        else if (data.startsWith(Flip.getKeyWord()))
//
//        else if (data.startsWith(Forward.getKeyWord()))
//            message = new Forward(data);
//        else if (data.startsWith(Go.getKeyWord()))
//            message = new Go(data);
//        else if (data.startsWith(Land.getKeyWord()))
//            message = new Land(data);
//        else if (data.startsWith(Left.getKeyWord()))
//            message = new Left(data);
//        else if (data.startsWith(Ok.getKeyWord()))
//            message = new Ok(data);
//        else if (data.startsWith(Right.getKeyWord()))
//            message = new Right(data);
//        else if (data.startsWith(SpeedQuery.getKeyWord()))
//            message = new SpeedQuery(data);
//        else if (data.startsWith(Stop.getKeyWord()))
//            message = new Stop(data);
//        else if (data.startsWith(Takeoff.getKeyWord()))
//            message = new Takeoff(data);
//        else if (data.startsWith(TimeQuery.getKeyWord()))
//            message = new TimeQuery(data);
//        else if (data.startsWith(Up.getKeyWord()))
//            message = new Up(data);
//        else if (data.startsWith(ErrorInfo.getKeyWord()))
//            message = new ErrorInfo(data);
//        else {
//            System.out.println("ERROR: Invalid message: " + data);
//        }
        return state;
    }
}
