package parkinglot;

public class ParkingLotService {

    CheckParkingSpace space=new CheckParkingSpace();

    public int park(CarInfo info) throws ParkingLotException {
        return space.Adddata(info);
    }

    public boolean unPark(int key) {
        return space.removeData(key);
    }

    public LotStatus.Status checkStatusForAirport() {
       return space.informOwner();
    }

    public LotStatus.Status checkStatusForOwner()  {
        return space.informOwner();
    }

}

