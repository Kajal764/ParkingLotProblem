package parkinglot;

public class AirportSecurity {

    public LotStatus.Status getStatus(LotStatus.Status status) throws ParkingLotException {
        LotStatus.Status lotstatus = status;
        if(lotstatus == LotStatus.Status.Lot_Empty)
            { return lotstatus; }
        throw new ParkingLotException("Lot_Not_Available",ParkingLotException.ExceptionType.Parking_Lot_Full);
    }
}
