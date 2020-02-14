package parkinglot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static parkinglot.AirportSecurity.getStatus;
import static parkinglot.OwnerInfo.getLotStatus;

public class ParkingLotService {

    public int capacity;

    Map<Integer,Object> slotMap=new HashMap<>();

    public ParkingLotService(int capacity) {
        this.capacity = capacity;
    }


    public void park(int slotNo, Object vehicle1) throws ParkingLotException {

        if (slotMap.size() == capacity) {
            throw new ParkingLotException("Lot_Not_Available", ParkingLotException.ExceptionType.Lot_Not_Available);

        }
        if (slotMap.size() < capacity) {
            slotMap.put(slotNo,vehicle1);
        }
        inform();
        informOwner();
    }

    public boolean isParked(int slotNo, Object vehicle) {

        if(slotMap.get(slotNo)==vehicle)
            return true;
        return false;
    }

    public void unPark(int index, Object vehicle) {
            if (slotMap.get(index) == vehicle)
                slotMap.put(index,0);
        informOwner();
    }

    private LotStatus.Status informOwner() {
            if (slotMap.size()<capacity || slotMap.containsValue(0))
                return getLotStatus(LotStatus.Status.Lot_Available);
            return getLotStatus(LotStatus.Status.Lot_Full);

    }


    public LotStatus.Status inform() {
        if(slotMap.size()==capacity)
            return getStatus(LotStatus.Status.Lot_Full);
        return getStatus(LotStatus.Status.Lot_Available);
    }

    public boolean isUnparked(Integer slotNo,Object vehicle) {
        if(slotMap.get(slotNo)==vehicle)
            return false;
        return true;

    }




}



