package parkinglot;

public class OwnerInfo {

    private static LotStatus.Status capacity;

    public static LotStatus.Status getLotStatus(LotStatus.Status status)  {
        capacity=status;
        return status;
    }

    public boolean isLotAvailable() {
        if(capacity.equals(LotStatus.Status.Lot_Available))
            return true;
        return false;
    }



}

