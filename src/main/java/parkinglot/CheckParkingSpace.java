package parkinglot;

import java.util.Map;
import java.util.TreeMap;

public class CheckParkingSpace
{
    int count;
    int token;
    Map<Integer, CarInfo> data = new TreeMap<Integer, CarInfo>();

   public int park(){
        count++;
        return count;
    }

    public int  Adddata(CarInfo info) throws ParkingLotException {
        CheckParkingSpace space=new CheckParkingSpace();
        int park = space.park();
        if (park<=100) {
            token++;
            data.put(token, info);
            return token;
        }
    informOwner();
    informAirportSecurity();
    return 0;
    }

    public LotStatus.Status informOwner()
    {
        OwnerInfo info=new OwnerInfo();
        if (count <= 100) {
            return info.getStatus(LotStatus.Status.Lot_Available);
        }
        return info.getStatus(LotStatus.Status.Lot_Full);
    }

    public  LotStatus.Status informAirportSecurity()
    {
        AirportSecurity security = new AirportSecurity();
        if (count<= 100) {
            return security.getStatus(LotStatus.Status.Lot_Available);
        }
        return security.getStatus(LotStatus.Status.Lot_Full);
    }

    public boolean removeData(int key) {
        data.remove(key);
        count--;
        informOwner();
        informAirportSecurity();
        return true;
    }
}
