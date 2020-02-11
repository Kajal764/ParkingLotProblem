package parkinglot;

import java.util.Map;
import java.util.TreeMap;

import static parkinglot.AirportSecurity.getStatus;

public class CheckParkingSpace
{
    int count;
    int token;
    Map<Integer, CarInfo> data = new TreeMap<>();

   public int park(){
        count++;
        return count;
    }

    public int  Adddata(CarInfo info) {
        CheckParkingSpace space=new CheckParkingSpace();
        int park = space.park();
        if (park<=100) {
            token++;
            data.put(token, info);
            return token;
        }
    informOwner();
    return 0;
    }

    public LotStatus.Status informOwner()
    {
        if (count <= 100)
            return getStatus(LotStatus.Status.Lot_Available);
        return getStatus(LotStatus.Status.Lot_Full);
    }


    public boolean removeData(int key) {
        data.remove(key);
        count--;
        informOwner();
        return true;
    }
}
