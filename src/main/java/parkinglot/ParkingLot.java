package parkinglot;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class ParkingLot {

    TreeMap<Integer, CarInfo> data = new TreeMap<Integer, CarInfo>();
    TreeMap<Integer, Boolean> isEmpty = new TreeMap<Integer, Boolean>();
    Integer token = 0;

    public boolean park() {
        return true;
    }

    public TreeMap<Integer, Boolean> addedInfo(CarInfo info) {
        boolean park = park();
        if (park == true) {
            token++;
            CarInfo put = data.put(token, info);
            isEmpty.put(token, true);
        }
        return isEmpty;
    }

    public void unPark(int key) {
       isEmpty.replace(key, false);
    }
    }


//        Boolean replace = isEmpty.replace(integerBooleanHashMap,false);
//        System.out.println(isEmpty);
//        return replace;



//    public void unPark(HashMap<Integer, Boolean> integerBooleanHashMap) {
//        isEmpty.replace(integerBooleanHashMap.,false);
//    }
