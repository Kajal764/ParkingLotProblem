package parkinglot;

import java.util.TreeMap;

public class ParkingLot {

    TreeMap<Integer, CarInfo> data = new TreeMap<Integer, CarInfo>();

    Integer token = 0;
    int count=0;

    public int park() {
        count++;
        return count;
    }

    public int addedInfo(CarInfo info) throws ParkingLotException {
        int park = park();
        if (park<=100) {
            token++;
            CarInfo put = data.put(token,info);
            return token;
        }
        throw new ParkingLotException("Lot_Not_Available",ParkingLotException.ExceptionType.Lot_Not_Available);
    }

    public boolean unPark(int key) {
       data.replace(key,null);
       return true;
    }

}

