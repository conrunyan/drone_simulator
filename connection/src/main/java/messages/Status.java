package messages;

import utils.StringUtils;

public class Status extends Message {
    public static String getKeyWord() { return "mid"; }

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
    private Double positionX;
    private Double positionY;
    private Double positionZ;
    private int orientation;

    public Status(Integer pitch, Integer roll, Integer yaw, Integer speedX, Integer speedY, Integer speedZ,
                  Integer lowTemperature, Integer highTemperature, Integer flightDistance, Integer height,
                  Integer batteryPercentage, Double barometerMeasurement, Integer motorTime,
                  Double accelerationX, Double accelerationY, Double accelerationZ,
                  Double positionX, Double positionY, Double positionZ, Integer orientation) {
        this.pitch = pitch;
        this.roll = roll;
        this.yaw = yaw;
        this.speedX = speedX;
        this.speedY = speedY;
        this.speedZ = speedZ;
        this.lowTemperature = lowTemperature;
        this.highTemperature = highTemperature;
        this.flightDistance = flightDistance;
        this.height = height;
        this.batteryPercentage = batteryPercentage;
        this.barometerMeasurement = barometerMeasurement;
        this.motorTime = motorTime;
        this.accelerationX = accelerationX;
        this.accelerationY = accelerationY;
        this.accelerationZ = accelerationZ;
        this.positionX = positionX;
        this.positionY = positionY;
        this.positionZ = positionZ;
        this.orientation = orientation;
    }

    public Status(String data) {
        parseData(data);
    }

    @Override
    public String getMessageText() {
        return String.format("mid:-1;x:0;y:0;z:0;mpry:0,0,0;pitch:%s;roll:%s;yaw:%s;"+
                        "vgx:%s;vgy:%s;vgz:%s;"+
                        "templ:%s;temph:%s;"+
                        "tof:%s;h:%s;"+
                        "bat:%s;baro:%s;"+
                        "time:%s;"+
                        "agx:%s;agy:%s;agz:%s;"+
                        "posx:%s;posy:%s;posz:%s;ornt:%s",
                StringUtils.formatInteger(pitch), StringUtils.formatInteger(roll), StringUtils.formatInteger(yaw),
                StringUtils.formatInteger(speedX), StringUtils.formatInteger(speedY), StringUtils.formatInteger(speedZ),
                StringUtils.formatInteger(lowTemperature), StringUtils.formatInteger(highTemperature),
                StringUtils.formatInteger(flightDistance), StringUtils.formatInteger(height),
                StringUtils.formatInteger(batteryPercentage), StringUtils.formatDouble(barometerMeasurement),
                StringUtils.formatInteger(motorTime),
                StringUtils.formatDouble(accelerationX), StringUtils.formatDouble(accelerationY), StringUtils.formatDouble(accelerationZ),
                StringUtils.formatDouble(positionX), StringUtils.formatDouble(positionY), StringUtils.formatDouble(positionZ), StringUtils.formatInteger(orientation));
    }

    @Override
    public String getMessageType() { return "status"; }

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

    public Double getPositionX() {
        return positionX;
    }

    public Double getPositionY() {
        return positionY;
    }

    public Double getPositionZ() {
        return positionZ;
    }

    public int getOrientation() {
        return orientation;
    }

    private void parseData(String data) {
        if (data==null || data.isEmpty())
            return;

        String[] stateFields = data.trim().split(";");
        if (stateFields.length!=25) {
            return;
        }

        pitch = parseInteger("pitch", stateFields[5]);
        roll = parseInteger("roll", stateFields[6]);
        yaw = parseInteger("yaw", stateFields[7]);
        speedX = parseInteger("vgx", stateFields[8]);
        speedY = parseInteger("vgy", stateFields[9]);
        speedZ = parseInteger("vgz", stateFields[10]);
        lowTemperature = parseInteger("templ", stateFields[11]);
        highTemperature = parseInteger("temph", stateFields[12]);
        flightDistance = parseInteger("tof", stateFields[13]);
        height = parseInteger("h", stateFields[14]);
        batteryPercentage = parseInteger("bat", stateFields[15]);
        barometerMeasurement = parseDouble("baro", stateFields[16]);
        motorTime = parseInteger("time", stateFields[17]);
        accelerationX = parseDouble("agx", stateFields[18]);
        accelerationY = parseDouble("agy", stateFields[19]);
        accelerationZ = parseDouble("agz", stateFields[20]);
        positionX = parseDouble("posx", stateFields[21]);
        positionY = parseDouble("posy", stateFields[22]);
        positionZ = parseDouble("posz", stateFields[23]);
        orientation = parseInteger("ornt", stateFields[24]);
    }

    private Integer parseInteger(String expectedLabel, String data) {
        String valueToParse = getValueToParse(expectedLabel, data);
        if (valueToParse==null)
            return null;

        Integer result = null;
        try {
            result = Integer.parseInt(valueToParse);
        }
        catch (NumberFormatException e) {
            // TODO: handle error, i.e., log it
        }
        return result;
    }

    private Double parseDouble(String expectedLabel, String data) {
        String valueToParse = getValueToParse(expectedLabel, data);
        if (valueToParse==null)
            return null;

        Double result = null;
        try {
            result = Double.parseDouble(valueToParse);
        }
        catch (NumberFormatException e) {
            // TODO: handle error, i.e., log it
        }
        return result;
    }

    private String getValueToParse(String expectedLabel, String data) {
        if (expectedLabel==null || expectedLabel.isEmpty() || data==null || data.isEmpty())
            return null;

        String[] parts = data.split(":");
        if (parts.length!=2)
            return null;

        if (!parts[0].trim().equals(expectedLabel))
            return null;

        return parts[1].trim();
    }

}
