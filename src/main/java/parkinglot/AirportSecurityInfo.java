package parkinglot;

public class AirportSecurityInfo {

    private static boolean parkingStatus;

    public void isLotAvailable(boolean status) {
        this.parkingStatus=status;
    }

    public boolean checkStatus() {
        if(parkingStatus)
            return true;
        return false;

    }

}
