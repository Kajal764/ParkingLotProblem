package parkinglot;

import parkinglot.LotStatus.Status;

public interface ParkingStatus {

    public abstract Status getLotStatus(Status status) ;
    public abstract boolean isLotAvailable() ;

    }
