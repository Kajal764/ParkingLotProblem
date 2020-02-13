package parkinglot;

import java.util.ArrayList;
import java.util.List;
import static parkinglot.AirportSecurity.getStatus;

public class ParkingLotService {

    private final int capacity;

    List slotList=new ArrayList<>();

    public ParkingLotService(int capacity) {
        this.capacity=capacity;
    }

    public void park(Object vehicle1) throws ParkingLotException{

        if(slotList.size() == capacity)
            throw new ParkingLotException("Lot_Not_Available",ParkingLotException.ExceptionType.Lot_Not_Available);
        if (slotList.size() < capacity)
            slotList.add(vehicle1);

        checkStatusForAirport();
        checkStatusForOwner();
    }

    public boolean isParked(Object vehicle) {
        return slotList.contains(vehicle);
    }

    public void unPark(Object vehicle)
    {
        slotList.remove(vehicle);
        checkStatusForAirport();
        checkStatusForOwner();
    }

    public boolean isUnparked(Object vehicle) {
        if(slotList.contains(vehicle))
            return false;
        return true;
    }

    public LotStatus.Status checkStatusForAirport() {
        if (slotList.size()<= 100)
            return getStatus(LotStatus.Status.Lot_Available);
        return getStatus(LotStatus.Status.Lot_Full);

    }

    public LotStatus.Status checkStatusForOwner()  {
        if (slotList.size() <= 100)
            return getStatus(LotStatus.Status.Lot_Available);
        return getStatus(LotStatus.Status.Lot_Full);
    }

}

