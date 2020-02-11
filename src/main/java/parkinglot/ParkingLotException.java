package parkinglot;

public class ParkingLotException extends Throwable{

    enum ExceptionType{
        Lot_Not_Available,
        Parking_Lot_Full

    }
    ExceptionType type;

    public ParkingLotException(String message,ExceptionType type) {
        super(message);
        this.type = type;
    }
}
