package com.bridgelabz.parkinglotservice.exception;

public class ParkingLotException extends Throwable{

    public enum ExceptionType{
        Lot_Not_Available,
    }
    public ExceptionType type;

    public ParkingLotException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
