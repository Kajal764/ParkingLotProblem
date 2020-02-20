package parkinglot;


public class ParkingStatus {


    AirportSecurityInfo airportSecurityInfo=new AirportSecurityInfo();
    OwnerInfo ownerInfo =new OwnerInfo();
    private VehicleData lotStatus;

    public void getLotStatus(VehicleData status) {
        lotStatus = status;
        if(lotStatus.equals(VehicleData.Lot_Available))
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
