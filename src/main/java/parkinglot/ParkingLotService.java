package parkinglot;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLotService{

    public int capacity;
    public int totalSlot;

    public int count=0;
    public int i=1;
    public int value=0;
    public int handicapSlot=1;
    int slotNo;


    Map<Integer, Object> slotMap=new HashMap<>();
    ParkingStatus parkingStatus = new ParkingStatus();

    List whiteCarList=new ArrayList();

    public ParkingLotService(int capacity,int totalSlot) {
        this.capacity = capacity;
        this.totalSlot=totalSlot;
    }

    public int assignSlot(){
        int slotSize=capacity/totalSlot;
        count++;
        if(count >5)
        {
            value=0;
            i++;
            count=1;
        }
        while (count <= totalSlot)
        {
            slotNo=i+slotSize*value;
            value++;
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
        getWhiteCar(vehicle);
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
            parkingStatus.getLotStatus(VehicleData.Status.Lot_Available);
        else
         parkingStatus.getLotStatus(VehicleData.Status.Lot_Full);
    }

    public void getWhiteCar(Object vehicle) {
        VehicleData.Color color = VehicleInfo.getColor();
        if(color.equals(VehicleData.Color.White))
        {
            for (int i = 1; i <= capacity; i++) {
                if (slotMap.get(i)==vehicle)
                    whiteCarList.add(i);
            } }
    }

    public List getWhiteCarList() {
        return whiteCarList;
    }
}



