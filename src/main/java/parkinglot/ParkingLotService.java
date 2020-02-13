package parkinglot;

import java.util.ArrayList;
import java.util.List;
import static parkinglot.AirportSecurity.getStatus;

public class ParkingLotService {

    int count;

    List slotList=new ArrayList<>();

    public void park(Object vehicle1) throws ParkingLotException{

        int park = count++;
        if (park<=100) {
            slotList.add(vehicle1);
        }
        checkStatusForAirport();
        checkStatusForOwner();
    }

    public boolean isParked(Object vehicle) {
        return slotList.contains(vehicle);
    }

    public boolean unPark(int key)
    {
        slotList.remove(key);
        count--;
        checkStatusForAirport();
        checkStatusForOwner();
        return true;
    }

    public LotStatus.Status checkStatusForAirport() {

        if (count <= 100)
            return getStatus(LotStatus.Status.Lot_Available);
        return getStatus(LotStatus.Status.Lot_Full);

    }

    public LotStatus.Status checkStatusForOwner()  {

        if (count <= 100)
            return getStatus(LotStatus.Status.Lot_Available);
        return getStatus(LotStatus.Status.Lot_Full);
    }


}

