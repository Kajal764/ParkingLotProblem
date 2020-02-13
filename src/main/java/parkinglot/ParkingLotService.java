package parkinglot;

import java.util.Map;
import java.util.TreeMap;

import static parkinglot.AirportSecurity.getStatus;

public class ParkingLotService {

    int count;
    int token;
    Map<Integer, Object> data = new TreeMap<Integer, Object>();

    public int vehicleCount(){
        count++;
        return count;
    }

    public int park(Object vehicle1) throws ParkingLotException {

        int park = vehicleCount();
        if (park<=100) {
            token++;
            data.put(token, vehicle1);
            return token;
        }
        checkStatusForAirport();
        checkStatusForOwner();
        return 0;

    }

    public boolean unPark(int key)
    {
        data.remove(key);
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

