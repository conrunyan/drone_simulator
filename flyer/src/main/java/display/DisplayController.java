package display;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import state.DroneState;

public class DisplayController {

    private DroneState state;

    public DisplayController() {
        state = new DroneState();
    }

    @FXML
    private Text pitch;
    @FXML
    private Text roll;
    @FXML
    private Text yaw;
    @FXML
    private Text speedX;
    @FXML
    private Text speedY;
    @FXML
    private Text speedZ;
    @FXML
    private Text lowTemperature;
    @FXML
    private Text highTemperature;
    @FXML
    private Text flightDistance;
    @FXML
    private Text height;
    @FXML
    private Text batteryPercentage;
    @FXML
    private Text barometerMeasurement;
    @FXML
    private Text motorTime;
    @FXML
    private Text accelerationX;
    @FXML
    private Text accelerationY;
    @FXML
    private Text accelerationZ;
    @FXML
    private Text positionX;
    @FXML
    private Text positionY;
    @FXML
    private Text positionZ;
    @FXML
    private Text orientation;

    public void updateSceneState(DroneState state) {
        this.pitch.setText(state.getPitch().toString());
        this.roll.setText(state.getRoll().toString());
        this.yaw.setText(state.getYaw().toString());
        this.speedX.setText(state.getSpeedX().toString());
        this.speedY.setText(state.getSpeedY().toString());
        this.speedZ.setText(state.getSpeedZ().toString());
        this.lowTemperature.setText(state.getLowTemperature().toString());
        this.highTemperature.setText(state.getHighTemperature().toString());
        this.flightDistance.setText(state.getFlightDistance().toString());
        this.height.setText(state.getHeight().toString());
        this.batteryPercentage.setText(state.getBatteryPercentage().toString());
        this.barometerMeasurement.setText(state.getBarometerMeasurement().toString());
        this.motorTime.setText(state.getMotorTime().toString());
        this.accelerationX.setText(state.getAccelerationX().toString());
        this.accelerationY.setText(state.getAccelerationY().toString());
        this.accelerationZ.setText(state.getAccelerationZ().toString());
        this.positionX.setText(state.getPositionX().toString());
        this.positionY.setText(state.getPositionY().toString());
        this.positionZ.setText(state.getPositionZ().toString());
        this.orientation.setText(state.getOrientation().toString());
    }
}
