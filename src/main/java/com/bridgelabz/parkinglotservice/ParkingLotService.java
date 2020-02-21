package com.bridgelabz.parkinglotservice;

import com.bridgelabz.parkinglotservice.enumeration.CHECKFORPARK;
import com.bridgelabz.parkinglotservice.enumeration.VehicleData;
import com.bridgelabz.parkinglotservice.exception.ParkingLotException;
import com.bridgelabz.parkinglotservice.service.ParkingStatus;
import com.bridgelabz.parkinglotservice.service.VehicleInfo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLotService {

    public int CAPACITY;
    public int TOTAL_SLOT;

    public int count = 0;
    public int i = 1;
    public int value = 0;
    public int handicapSlot = 1;
    int slotNo;

    Map<Integer, Object> slotMap = new HashMap<>();
    List<Integer> timeList = new ArrayList<>();
    ParkingStatus parkingStatus = new ParkingStatus();


    public ParkingLotService(int capacity, int TOTAL_SLOT) {
        this.CAPACITY = capacity;
        this.TOTAL_SLOT = TOTAL_SLOT;
    }

    public int assignSlot() {
        int slotSize = CAPACITY / TOTAL_SLOT;
        count++;
        if (count > 5) {
            value = 0;
            i++;
            count = 1;
        }
        while (count <= TOTAL_SLOT) {
            slotNo = i + slotSize * value;
            value++;
            break;
        }
        return slotNo;
    }

    public int park(VehicleInfo vehicle) throws ParkingLotException {

        if (slotMap.size() == CAPACITY)
            throw new ParkingLotException("Lot_Not_Available", ParkingLotException.ExceptionType.Lot_Not_Available);

        if (slotMap.size() < CAPACITY) {
            assignSlot();
            vehicle.getCheckForPark().parkCar(vehicle, slotNo,this);
        }

        informStatus();
        return slotMap.size();
    }

    public void parkIfNull(VehicleInfo vehicle, int slotNo) {
        this.slotNo = slotNo;
        slotMap.entrySet().stream().forEach(values -> {
            if(values.getKey() != null && values.getValue() == null)
                this.slotNo = values.getKey();
        });
        slotMap.put(this.slotNo, vehicle);

    }

    public void parkLargeCar(VehicleInfo vehicle, int No) {
        this.slotNo = No;
        slotNo = this.slotNo + (CAPACITY / TOTAL_SLOT) - 2;
        while (slotNo > 0) {
            if (slotMap.size() % 5 == 0) {
                slotNo = slotNo - 1;
            }
            if (slotMap.get(slotNo - 1) == null && slotMap.get(slotNo + 1) == null && slotMap.get(slotNo) == null) {
                break;
            }
            slotNo = slotNo - 1;
        }
        slotMap.putIfAbsent(slotNo, vehicle);
    }

    public int parkHandicapDriverCar(VehicleInfo vehicle, int slotNo) {
        this.slotNo = slotNo;
        if (slotMap.containsKey(handicapSlot)) {
            Object o = slotMap.get(handicapSlot);
            slotMap.put(handicapSlot, vehicle);
            slotMap.put(this.slotNo, o);
            handicapSlot = handicapSlot + CAPACITY / TOTAL_SLOT;
            return 0;
        }
        slotMap.put(this.slotNo, vehicle);
        return this.slotNo;
    }


    public int isParked(Object vehicle) {
        for (Map.Entry<Integer, Object> i : slotMap.entrySet()) {
            if (i.getValue() == vehicle)
                return i.getKey();
        }
        return 0;

    }

    public void unPark(Object vehicle) {
        for (Map.Entry<Integer, Object> i : slotMap.entrySet()) {
            if (i.getValue() == vehicle)
                slotMap.put(i.getKey(), null);
        }
        informStatus();
    }


    public boolean isUnparked(Object vehicle) {
        if (slotMap.containsValue(vehicle))
            return false;
        return true;
    }

    private void informStatus() {
        if (slotMap.size() < CAPACITY || slotMap.containsValue(null))
            parkingStatus.getLotStatus(VehicleData.LOT_AVAILABLE);
        else
            parkingStatus.getLotStatus(VehicleData.LOT_FULL);
    }

    public Map<Integer, Object> getCarList(VehicleData... findValue) {


        Map<Integer, Object> collect = slotMap.entrySet().stream()
                .filter(Entry -> Entry.getValue()
                .toString().toLowerCase().contains(findValue[0].toString().toLowerCase()))
                .collect(Collectors.toMap(m -> m.getKey(), m -> m.getValue()));
        if(findValue.length>1)
        {
            collect=collect.entrySet().stream()
                    .filter(m-> m.getValue()
                            .toString().contains(findValue[1].toString()))
                    .collect(Collectors.toMap(m->m.getKey(),m->m.getValue()));

        }
        return collect;
    }


    public List<Integer> getCarWithin30Min() {
        for(int i = 1; i<= CAPACITY; i++ )
        {
            if(slotMap.get(i)!=null)
            {
                VehicleInfo o = (VehicleInfo) slotMap.get(i);
                LocalTime parkTime = o.time;
                LocalTime currentTimeThirtyMinAgo = LocalTime.now().minusMinutes(30);

                int compare = currentTimeThirtyMinAgo.compareTo(parkTime);
                if(compare==1)
                    timeList.add(i); }
        }
    return timeList;
    }


    public Map<Integer, VehicleInfo> getHandicapDriverInfo(int i) {
       Map<Integer,VehicleInfo> vehicle=new HashMap<>();

       for(int k = 0; k<= CAPACITY; k++){
            if(slotMap.get(k)!=null)
            {
                if(assignlot(i,k))
                    vehicle.put(k, (VehicleInfo) slotMap.get(k));

            }
        }
        Map<Integer, VehicleInfo> data=vehicle.entrySet().stream()
                .filter(Entry -> Entry.getValue().checkForPark.equals(CHECKFORPARK.HANDICAP))
                .collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue()));
       return data;

    }


    public boolean assignlot(int slot, int key) {
        int v = 0;
        int value = CAPACITY / TOTAL_SLOT;
        int a[] = new int[TOTAL_SLOT +1];
       for(int i = 1; i<= TOTAL_SLOT; i++)
        {   v=v+value;
            a[i]=v;
        }
        if(key<=a[slot] && key>a[slot-1])
            return true;
        return false;
    }

}



