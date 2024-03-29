package state;

import java.util.Date;

import messages.Message;
import messages.Status;

public class DroneState {

    private boolean inCommandMode;
    private boolean hasTakenOff;
    private boolean videoStreamOn;
    private Date stateTimestamp;
    private Double currentFlightTime;
    private Double positionX;
    private Double positionY;
    private Double positionZ;
    private Integer pitch;
    private Integer roll;
    private Integer yaw;
    private Integer speedX;
    private Integer speedY;
    private Integer speedZ;
    private Integer lowTemperature;
    private Integer highTemperature;
    private Integer flightDistance;
    private Integer height;
    private Integer batteryPercentage;
    private Double barometerMeasurement;
    private Integer motorTime;
    private Double accelerationX;
    private Double accelerationY;
    private Double accelerationZ;
    private Integer orientation;
    private Integer batteryDrainDelta;
    private final Integer maxTemperature = 75;

    public DroneState() {
        resetState();
    }

    public DroneState(DroneState stateToCopy) {
        this.inCommandMode = stateToCopy.isInCommandMode();
        this.hasTakenOff = stateToCopy.hasTakenOff();
        this.videoStreamOn = stateToCopy.isVideoStreamOn();
        this.stateTimestamp = stateToCopy.getStateTimestamp();
        this.currentFlightTime = stateToCopy.getCurrentFlightTime();
        this.positionX = stateToCopy.getPositionX();
        this.positionY = stateToCopy.getPositionY();
        this.positionZ = stateToCopy.getPositionZ();
        this.pitch = stateToCopy.getPitch();
        this.roll = stateToCopy.getRoll();
        this.yaw = stateToCopy.getYaw();
        this.speedX = stateToCopy.getSpeedX();
        this.speedY = stateToCopy.getSpeedY();
        this.speedZ = stateToCopy.getSpeedZ();
        this.lowTemperature = stateToCopy.getLowTemperature();
        this.highTemperature = stateToCopy.getHighTemperature();
        this.flightDistance = stateToCopy.getFlightDistance();
        this.height = stateToCopy.getHeight();
        this.batteryPercentage = stateToCopy.getBatteryPercentage();
        this.barometerMeasurement = stateToCopy.getBarometerMeasurement();
        this.motorTime = stateToCopy.getMotorTime();
        this.accelerationX = stateToCopy.getAccelerationX();
        this.accelerationY = stateToCopy.getAccelerationY();
        this.accelerationZ = stateToCopy.getAccelerationZ();
        this.orientation = stateToCopy.getOrientation();
        this.batteryDrainDelta = stateToCopy.getBatteryDrainDelta();
    }

    public boolean isInCommandMode() { return inCommandMode; }

    public void setInCommandMode(boolean inCommandMode) {
        if (this.inCommandMode == inCommandMode)
            return;

        this.inCommandMode = inCommandMode;
        if (!inCommandMode)
            resetState();
    }

    public boolean hasTakenOff() {
        return hasTakenOff;
    }

    public void setHasTakenOff(boolean hasTakenOff) {
        if (this.hasTakenOff == hasTakenOff)
            return;

        this.hasTakenOff = inCommandMode && hasTakenOff;
        if (!this.hasTakenOff)
            resetFlyingInfo();
    }

    public boolean lowBattery() {
        return this.batteryPercentage <= 20;
    }

    public boolean isVideoStreamOn() { return videoStreamOn; }

    public void setVideoStreamOn(boolean videoStreamOn)
    {
        this.videoStreamOn = inCommandMode && videoStreamOn;
    }

    public Double getCurrentFlightTime() {
        return currentFlightTime;
    }

    public void setCurrentFlightTime(Double currentFlightTime) {
        if (inCommandMode) {
            this.currentFlightTime = currentFlightTime;
        }
    }

    public void updateFlyingInfo(Status status) {
        if (!inCommandMode || status==null) {
            return;
        }

        this.pitch = status.getPitch();
        this.roll = status.getRoll();
        this.yaw = status.getYaw();
        this.speedX = status.getSpeedX();
        this.speedY = status.getSpeedY();
        this.speedZ = status.getSpeedZ();
        this.lowTemperature = status.getLowTemperature();
        this.highTemperature = status.getHighTemperature();
        this.flightDistance = status.getFlightDistance();
        this.height = status.getHeight();
        this.batteryPercentage = status.getBatteryPercentage();
        this.barometerMeasurement = status.getBarometerMeasurement();
        this.motorTime = status.getMotorTime();
        this.accelerationX = status.getAccelerationX();
        this.accelerationY = status.getAccelerationY();
        this.accelerationZ = status.getAccelerationZ();
        this.positionX = status.getPositionX();
        this.positionY = status.getPositionY();
        this.positionZ = status.getPositionZ();
        this.orientation = status.getOrientation();

        drainBattery();

        this.stateTimestamp = new Date();
    }

    public Date getStateTimestamp() {
        return stateTimestamp;
    }

    public void move(double deltaX, double deltaY, double deltaZ) {
        if (!hasTakenOff) return;

        double rotation = Math.toRadians(-orientation);
        double rotatedX = Math.cos(rotation) * deltaX - Math.sin(rotation) * deltaY;
        double rotatedY = Math.sin(rotation) * deltaX + Math.cos(rotation) * deltaY;

        positionX += rotatedX;
        positionY += rotatedY;
        positionZ += deltaZ;
        drainBattery();
    }

    public void rotate(int deltaOrientation) {
        if (!hasTakenOff) return;

        orientation += deltaOrientation;
        orientation = orientation % 360;
        drainBattery();
    }

    public Double getPositionX() {
        return positionX;
    }

    public Double getPositionY() {
        return positionY;
    }

    public Double getPositionZ() {
        return positionZ;
    }

    public Integer getPitch() {
        return pitch;
    }

    public Integer getRoll() {
        return roll;
    }

    public Integer getYaw() {
        return yaw;
    }

    public Integer getSpeedX() {
        return speedX;
    }

    public Integer getSpeedY() {
        return speedY;
    }

    public Integer getSpeedZ() {
        return speedZ;
    }

    public Double getAccelerationX() {
        return accelerationX;
    }

    public Double getAccelerationY() {
        return accelerationY;
    }

    public Double getAccelerationZ() {
        return accelerationZ;
    }

    public Integer getLowTemperature() {
        return lowTemperature;
    }

    public Integer getHighTemperature() {
        return highTemperature;
    }

    public Integer getFlightDistance() {
        return flightDistance;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getBatteryPercentage() {
        return batteryPercentage;
    }

    public Double getBarometerMeasurement() {
        return barometerMeasurement;
    }

    public Integer getMotorTime() {
        return motorTime;
    }

    public Integer getOrientation() { return orientation; }

    public Integer getBatteryDrainDelta() { return batteryDrainDelta; }

    public Integer getMaxTemperature() { return maxTemperature; }

    void drainBattery() {
        batteryPercentage -= batteryDrainDelta;
        if (batteryPercentage < 0) {
            batteryPercentage = 0;
        }
    }

    private void resetState() {
        videoStreamOn = false;
        hasTakenOff = false;
        stateTimestamp = new Date();
        resetFlyingInfo();
    }

    private void resetFlyingInfo() {
        currentFlightTime = 0.0;
        positionX = 0.0;
        positionY = 0.0;
        positionZ = 0.0;
        pitch = 0;
        roll = 0;
        yaw = 0;
        speedX = 0;
        speedY = 0;
        speedZ = 0;
        lowTemperature = 0;
        highTemperature = 60;
        flightDistance = 0;
        height = 0;
        batteryPercentage = 100;
        barometerMeasurement = 0.0;
        motorTime = 0;
        accelerationX = 0.0;
        accelerationY = 0.0;
        accelerationZ = 0.0;
        orientation = 0;
        batteryDrainDelta = 1;
    }
}

