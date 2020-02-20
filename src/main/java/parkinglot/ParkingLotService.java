package parkinglot;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ParkingLotService{

    public int capacity;
    public int totalSlot;
    public int count=0;
    public int i=1;
    public int value=0;
    public int handicapSlot=1;
    int slotNo;

    Map<Integer, Object> slotMap=new HashMap<>();
    Map<Integer,Object> toyotoCarMap=new HashMap<>();
    ParkingStatus parkingStatus = new ParkingStatus();

    List carList =new ArrayList();

    public ParkingLotService(int capacity, int totalSlot) {
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

    public void park(VehicleInfo vehicle) throws ParkingLotException {

        if (slotMap.size() == capacity)
            throw new ParkingLotException("Lot_Not_Available", ParkingLotException.ExceptionType.Lot_Not_Available);

        if (slotMap.size() < capacity) {
            assignSlot();
            vehicle.getCheckForPark().parkCar(vehicle,slotNo,this);

        }
            getWhiteCar(vehicle);
            getBlueToyotoCars(vehicle);
            getParkTime();
            informStatus();
    }

    public void parkIfNull(VehicleInfo vehicle,int slotNo) {
        this.slotNo=slotNo;
        for(int i = slotNo; i>0; i--) {
            if(slotMap.containsKey(i) && slotMap.get(i)==null) {
                this.slotNo =i;
                break;
            }

        }
        slotMap.put(this.slotNo,vehicle);

    }

    public void parkLargeCar(VehicleInfo vehicle,int No) {
        this.slotNo=No;
        slotNo = this.slotNo + (capacity / totalSlot) - 2;
        while (slotNo >0 )
        {
            if(slotMap.size()%5==0)
            {
                slotNo=slotNo-1;
            }
            if(slotMap.get(slotNo-1)==null && slotMap.get(slotNo+1)==null && slotMap.get(slotNo)==null) {
                break;
            }
            slotNo=slotNo-1;
        }
        slotMap.putIfAbsent(slotNo,vehicle);
    }

    public int parkHandicapDriverCar(VehicleInfo vehicle, int slotNo) {
        this.slotNo=slotNo;
        if (slotMap.containsKey(handicapSlot)) {
                    Object o = slotMap.get(handicapSlot);
                    slotMap.put(handicapSlot, vehicle);
                    slotMap.put(this.slotNo, o);
                    handicapSlot = handicapSlot + capacity/totalSlot;
                    return 0;
        }
        slotMap.put(this.slotNo,vehicle);
        System.out.println(slotMap);
        return this.slotNo;
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
        return true;
    }

        public String getParkTime() {
            return LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    private void informStatus() {
        if(slotMap.size() < capacity || slotMap.containsValue(null))
            parkingStatus.getLotStatus(VehicleData.Status.Lot_Available);
        else
         parkingStatus.getLotStatus(VehicleData.Status.Lot_Full);
    }

    public void getWhiteCar(VehicleInfo vehicle) {

        if(vehicle.colour.equals(VehicleData.Color.White))
        {
            for (int i = 1; i <= capacity; i++) {
                if (slotMap.get(i)==vehicle) {
                    carList.add(i);
                    break;
                }
            }
        }
    }

    public List getCarList() {
        return carList;
    }

    public void getBlueToyotoCars(VehicleInfo vehicle) {

        if(vehicle.colour.equals(VehicleData.Color.Blue) && vehicle.carType.equals(VehicleData.carType.Toyoto))
        {
            for(int i=1;i<=capacity;i++)
            {
                if(slotMap.get(i)==vehicle) {
                    toyotoCarMap.put(i,vehicle);
                    break;
                }
            }
        }
    }

    public Map<Integer, Object> getToyotoCars() {
        return toyotoCarMap;
    }
}



