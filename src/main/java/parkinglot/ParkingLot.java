package parkinglot;

import java.util.TreeMap;

public class ParkingLot {

    TreeMap<Integer, CarInfo> data = new TreeMap<Integer, CarInfo>();
    TreeMap<Integer, Boolean> isLotEmpty = new TreeMap<Integer, Boolean>();
    Integer token = 0;
    int count=0;

    public int park() {
        count++;
        return count;
    }

    public TreeMap<Integer, Boolean> addedInfo(CarInfo info) throws ParkingLotException {
        int park = park();
        if (park<=100) {
            token++;
            CarInfo put = data.put(token,info);
            isLotEmpty.put(token, true);
            return isLotEmpty;
        }
        throw new ParkingLotException("Lot_Not_Available",ParkingLotException.ExceptionType.Lot_Not_Available);
    }

    public void unPark(int key) {
       isLotEmpty.replace(key, false);
    }

}

