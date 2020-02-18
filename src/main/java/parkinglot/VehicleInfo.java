package parkinglot;


public class VehicleInfo {


    private final LotStatus.CarSize carSize;
    private String time;

    public VehicleInfo(LotStatus.CarSize carSize, String currenttime) {
        this.carSize=carSize;
        this.time=currenttime;
    }

    public String getTime()
    { return time; }

}
