package parkinglot;

import java.util.TreeMap;

public class CheckParkingSpace
{
   int count=0;
   int token=0;
    TreeMap<Integer, CarInfo> data = new TreeMap<Integer, CarInfo>();

   public int park()
    {   count++;
        return count;
    }

    public int Adddata(CarInfo info) throws ParkingLotException {
       try {
           CheckParkingSpace space = new CheckParkingSpace();
           int park = space.park();
           if (park <= 100) {
               token++;
               CarInfo put = data.put(token, info);
               return token;
           }
           throw new ParkingLotException("Lot_Not_Available", ParkingLotException.ExceptionType.Lot_Not_Available); }
       finally {
          informAirportSecurity();
       }
    }

    public LotStatus.Status informAirportSecurity() throws ParkingLotException {
        AirportSecurity security = new AirportSecurity();
        if (count <= 100) {
            return security.getStatus(LotStatus.Status.Lot_Empty);
        }
        return security.getStatus(LotStatus.Status.Lot_Full);

    }

    public boolean removeData(int key) {
        data.replace(key,null);
        return true;
    }
}
