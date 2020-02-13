package parkinglot;

import java.util.ArrayList;
import java.util.List;
import static parkinglot.AirportSecurity.getStatus;

public class ParkingLotService {

    int count;

    List slotList=new ArrayList<>();

    public int vehicleCount(){
        count++;
        return count;
    }

    public int park(Object vehicle1) throws ParkingLotException {

        int park = vehicleCount();
        if (park<=100) {
            slotList.add(vehicle1);
            return slotList.size();
        }
        checkStatusForAirport();
        checkStatusForOwner();
        return 0;
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

