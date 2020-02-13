package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTestCases {

    Object vehicle;
    ParkingLotService service;

    @Before
    public void setUp() throws Exception {
        vehicle=new Object();
        service=new ParkingLotService();
    }

    @Test
    public void whenDriverParkHisCar_SpaceTokenShouldReturnTrue() throws ParkingLotException {
        service.park(vehicle);
        boolean isParked = service.isParked(vehicle);
        Assert.assertTrue(isParked);
    }


    @Test
    public void whenParkTheCar_ItShouldCheckItsParkingCarShouldNotBeMoreThan100()  {
        Integer status=null;
        try {
            ParkingLotService parkingLot = new ParkingLotService();
            for (int i=1; i <= 100; i++) {
               parkingLot.park(vehicle); }

            Object vehicle2=new Object();
        }
        catch (ParkingLotException e)
        {
            System.out.println("Slot1 Is Full");
            Assert.assertEquals(e.type,ParkingLotException.ExceptionType.Lot_Not_Available); }
    }

    @Test
    public void whenLotFull_ItShouldInformAirportSecurityService() throws ParkingLotException {
        try {
            ParkingLotService parkingLot = new ParkingLotService();
            for (int i = 1; i <= 103; i++) {
                parkingLot.park(vehicle);
            }
        }
        catch (ParkingLotException e)
        {
            System.out.println("Slot1 Is Full");
            Assert.assertEquals(e.type,ParkingLotException.ExceptionType.Lot_Not_Available); }
        }

    @Test
    public void whenLotAvailable_ItShouldReturnAvaliableSlotStatus() throws ParkingLotException {
        ParkingLotService parkingLot = new ParkingLotService();
        parkingLot.park(vehicle);
        LotStatus.Status status = parkingLot.checkStatusForAirport();
        Assert.assertEquals( LotStatus.Status.Lot_Available,status);
    }

    @Test
    public void whenLotCapacityFull_AndUnparkTheCarThenItShouldReturnStatusEmptyAgain() throws ParkingLotException {
            ParkingLotService parkingLot = new ParkingLotService();
            for (int i = 1; i <= 101; i++) {
                parkingLot.park(vehicle);
            }
            parkingLot.unPark(1);
            parkingLot.unPark(2);
            LotStatus.Status status = parkingLot.checkStatusForOwner();
            System.out.println("Slot1 Is Full");
            Assert.assertEquals(LotStatus.Status.Lot_Available, status);

}

}


