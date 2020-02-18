package parkinglot;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ParkingLotService{

    public int capacity;
    public int slot=-19;
    public int slotNo=slot;
    public int count=0;
    public int i=1;
    public int handicapSlot=1;

    Map<Integer, Object> slotMap=new HashMap<>();
    ParkingStatus parkingStatus = new ParkingStatus();

    public ParkingLotService(int capacity) {
        this.capacity = capacity;
    }

    public int assignSlot(){
        count++;
        if(count >5)
        {
            slotNo=slot+i;
            count=1;
            i++;
        }
        while (count <= 5)
        {
            slotNo=slotNo+20;
            break;
        }
        return slotNo;
    }

    public void park(Object vehicle, boolean isHandicap) throws ParkingLotException {

        if (slotMap.size() == capacity)
            throw new ParkingLotException("Lot_Not_Available", ParkingLotException.ExceptionType.Lot_Not_Available);

        if (slotMap.size() < capacity) {
            assignSlot();
            if(isHandicap)
            {
                if(slotMap.containsKey(handicapSlot))
                {
                    Object o = slotMap.get(handicapSlot);
                    slotMap.put(handicapSlot,vehicle);
                    slotMap.put(slotNo,o);
                    handicapSlot=handicapSlot+20;
                }
            }
            else {
                slotMap.put(slotNo, vehicle);
            }
        }
        getParkTime();
        informStatus();
    }


    public int isParked(Object vehicle) {
        for (int i = 1; i <= capacity; i++) {
            if (slotMap.get(i)==vehicle)
                return i;
        }
        return 0;
    }

    public void unPark(Object vehicle) {
        for (int i = 1; i <= capacity; i++) {
            if (slotMap.get(i)==vehicle)
                slotMap.put(i,null);
        }
        informStatus();
    }


    public boolean isUnparked(Object vehicle) {
        if(slotMap.containsValue(vehicle))
            return false;
        return true; }

        public String getParkTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    private void informStatus() {
        if(slotMap.size() < capacity || slotMap.containsValue(null))
            parkingStatus.getLotStatus(LotStatus.Status.Lot_Available);
        else
         parkingStatus.getLotStatus(LotStatus.Status.Lot_Full);
    }
}



