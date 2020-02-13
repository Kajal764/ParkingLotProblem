package parkinglot;

import java.util.ArrayList;
import java.util.List;

import static parkinglot.AirportSecurity.getStatus;
import static parkinglot.OwnerInfo.getLotStatus;

public class ParkingLotService {

    public int capacity;

    List slotList = new ArrayList<>();

    public ParkingLotService(int capacity) {
        this.capacity = capacity;
    }


    public void park(Object vehicle1) throws ParkingLotException {

        if (slotList.size() == capacity) {
            throw new ParkingLotException("Lot_Not_Available", ParkingLotException.ExceptionType.Lot_Not_Available);

        }
        if (slotList.size() < capacity) {
            slotList.add(vehicle1);
        }
        inform();
        informOwner();
    }

    public boolean isParked(Object vehicle) {
        return slotList.contains(vehicle);
    }

    public void unPark(Object vehicle) {
        int index = 0;
        for (int i = 0; i < slotList.size(); i++) {
            if (slotList.get(i) == vehicle)
                index = i;
        }
        slotList.set(index, 0);
        informOwner();
    }

    private LotStatus.Status informOwner() {
        for (int i = 0; i < slotList.size(); i++) {
            if (slotList.contains(0)) ;
            return getLotStatus(LotStatus.Status.Lot_Available);
            }
        return getLotStatus(LotStatus.Status.Lot_Full);
    }


    public LotStatus.Status inform() {
        if(slotList.size()==capacity)
            return getStatus(LotStatus.Status.Lot_Full);
        return getStatus(LotStatus.Status.Lot_Available);
    }

    public boolean isUnparked(Object vehicle) {
        if(slotList.contains(vehicle))
            return false;
        return true;
    }



}



