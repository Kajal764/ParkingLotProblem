package parkinglot;


public class VehicleInfo {


    public VehicleData.Color colour;
    public VehicleData.carType carType;
    public String plateNo;
    public String driverName;
    public VehicleData.CarSize carSize;
    public String time;

    public VehicleInfo(String driverName, VehicleData.carType carType, String plateNo, VehicleData.CarSize carSize, VehicleData.Color color, String time) {
        this.driverName=driverName;
        this.carType=carType;
        this.plateNo=plateNo;
        this.carSize=carSize;
        this.colour=color;
        this.time=time;
    }

    public String getTime()
    { return time; }


    @Override
    public String toString() {
        return "VehicleInfo{" +
                "colour=" + colour +
                ", carType=" + carType +
                ", plateNo='" + plateNo + '\'' +
                ", driverName='" + driverName + '\'' +
                ", carSize=" + carSize +
                ", time='" + time + '\'' +
                '}';
    }
}
