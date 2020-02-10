package parkinglot;

import java.util.HashMap;

public class ParkingLot {
    int token=0;
    public boolean park() {
        return true;
    }

    public HashMap<Integer, Boolean> addedInfo(CarInfo info) {
        HashMap<Integer, CarInfo> data=new HashMap<>();
        HashMap<Integer,Boolean> isEmpty=new HashMap<Integer,Boolean>();
        token++;
        CarInfo put = data.put(token,info);
        isEmpty.put(token,true);
       return isEmpty;
    }

}
