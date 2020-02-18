package parkinglot;


public class VehicleInfo {


    private static VehicleData.Color colour;
    public VehicleData.CarSize carSize;
    public String time;

    public VehicleInfo(VehicleData.CarSize Carsize, VehicleData.Color color, String time) {
        this.carSize=Carsize;
        this.colour=color;
        this.time=time;
    }

    public VehicleInfo() {

    }

    public String getTime()
    { return time; }

    public static VehicleData.Color getColor() {
        return colour;
    }


}
