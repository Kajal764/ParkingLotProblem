package parkinglot;

import java.util.TreeMap;

public class ParkingLot {
    int adddata=0;

    public int getData(CarInfo info) throws ParkingLotException {
        CheckParkingSpace space=new CheckParkingSpace();
         return adddata =adddata+ space.Adddata(info);


    }

    public boolean unPark(int key) {
        CheckParkingSpace space=new CheckParkingSpace();
        return space.removeData(key);
    }

}

