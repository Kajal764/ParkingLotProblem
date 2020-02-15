package parkinglot;

public class AirportSecurityInfo implements ParkingStatus {
    public static LotStatus.Status lotStatus;

    @Override
    public LotStatus.Status getLotStatus(LotStatus.Status status) {
        lotStatus=status;
        return lotStatus;
    }

    @Override
    public boolean isLotAvailable() {
        if(lotStatus.equals(LotStatus.Status.Lot_Available))
            return false;
        return true;

    }

}
