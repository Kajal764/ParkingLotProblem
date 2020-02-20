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

    public void park(VehicleInfo vehicle) throws ParkingLotException {

        if (slotMap.size() == capacity)
            throw new ParkingLotException("Lot_Not_Available", ParkingLotException.ExceptionType.Lot_Not_Available);

        if (slotMap.size() < capacity) {
            assignSlot();

            if (VehicleData.DriverStatus.IsHandicap == vehicle.driverStatus)
                parkNonHandicapDriverCar(vehicle);
            if(VehicleData.CarSize.Large_Car == vehicle.carSize)
                parkLargeCar(vehicle);
            if(VehicleData.DriverStatus.Normal == vehicle.driverStatus) {
                parkIfNull(vehicle);
            }
        }
            getWhiteCar(vehicle);
            getBlueToyotoCars(vehicle);
            getParkTime();
            informStatus();
    }

    private void parkIfNull(VehicleInfo vehicle) {

        for(int i=slotNo; i>0; i--) {
            if(slotMap.containsKey(i) && slotMap.get(i)==null) {
                slotNo=i;
                value--;
                break;
            }

        }
        slotMap.put(slotNo,vehicle);

    }

    private void parkLargeCar(VehicleInfo vehicle) {

        slotNo = slotNo + (capacity / totalSlot) - 2;
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

    private void parkNonHandicapDriverCar(VehicleInfo vehicle) {
        if (slotMap.containsKey(handicapSlot)) {
                    Object o = slotMap.get(handicapSlot);
                    slotMap.put(handicapSlot, vehicle);
                    slotMap.put(slotNo, o);
                    handicapSlot = handicapSlot + capacity/totalSlot;
        }
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



