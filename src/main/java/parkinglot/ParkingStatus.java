package parkinglot;


import enumeration.VehicleData;

public class ParkingStatus {


    AirportSecurityInfo airportSecurityInfo=new AirportSecurityInfo();
    OwnerInfo ownerInfo =new OwnerInfo();
    private VehicleData lotStatus;

    public void getLotStatus(VehicleData status) {
        lotStatus = status;
        if(lotStatus.equals(VehicleData.LOT_AVAILABLE))
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
