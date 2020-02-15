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


    Map<Integer,Object> slotMap=new HashMap<>();

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

    public void park(Object vehicle1) throws ParkingLotException {

        if (slotMap.size() == capacity) {
            throw new ParkingLotException("Lot_Not_Available", ParkingLotException.ExceptionType.Lot_Not_Available);
        }
        if (slotMap.size() < capacity) {

            assignSlot();
            slotMap.put(slotNo,vehicle1);
            getParkTime();
        }
        informAirportSecurity();
        informOwner();
    }

    public int isParked(Object vehicle) {
        if(slotMap.containsValue(vehicle))
            return slotNo;
        return 0; }


    public void unPark(Object vehicle) {
        int index=0;
        for(int key=0; key<=capacity;key++ ) {
            if (slotMap.containsValue(vehicle) && slotMap.containsKey(key)) {
                index = key;
                slotMap.put(index, 0);
            }
        }
        informOwner();
    }

    public boolean isUnparked(Object vehicle) {
        if(slotMap.containsValue(vehicle))
            return false;
        return true; }

    public String getParkTime() {
        return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }


    private LotStatus.Status informOwner() {
            if (slotMap.size()<capacity || slotMap.containsValue(0))
                return new OwnerInfo().getLotStatus(LotStatus.Status.Lot_Available);
            return new OwnerInfo().getLotStatus(LotStatus.Status.Lot_Full);

    }

    public LotStatus.Status informAirportSecurity(){
        if(slotMap.size()<capacity || slotMap.containsValue(0))
            return new AirportSecurityInfo().getLotStatus(LotStatus.Status.Lot_Available);
        return new AirportSecurityInfo().getLotStatus(LotStatus.Status.Lot_Full);
    }

}



