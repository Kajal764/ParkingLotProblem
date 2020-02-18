package parkinglot;

import parkinglot.VehicleData.Status;

public class ParkingStatus {

    private Status lotStatus;
    AirportSecurityInfo airportSecurityInfo=new AirportSecurityInfo();
    OwnerInfo ownerInfo =new OwnerInfo();


    public void getLotStatus(Status status) {
        lotStatus = status;
        if(lotStatus.equals(Status.Lot_Available))
        {
            airportSecurityInfo.isLotAvailable(true);
            ownerInfo.LotAvailable(true);
        }
        else {
            airportSecurityInfo.isLotAvailable(false);
            ownerInfo.LotAvailable(false);
        }
    }

}
