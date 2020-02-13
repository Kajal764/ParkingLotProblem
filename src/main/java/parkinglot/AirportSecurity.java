package parkinglot;

public class AirportSecurity {

    private static LotStatus.Status capacity;

    public static LotStatus.Status getStatus(LotStatus.Status status)  {
        capacity=status;
        return status;
    }

    public boolean isCapacityFull() {
        if(capacity.equals(LotStatus.Status.Lot_Full))
            return true;
        return false;
    }
}
