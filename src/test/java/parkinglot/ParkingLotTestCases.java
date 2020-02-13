package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParkingLotTestCases {

    Object vehicle;
    ParkingLotService service;
    int capacity = 3;

    @Before
    public void setUp() {
        vehicle = new Object();
        service = new ParkingLotService(capacity);
    }

    @Test
    public void whenDriverParkHisCar_SpaceTokenShouldReturnTrue() throws ParkingLotException {
        service.park(vehicle);
        boolean isParked = service.isParked(vehicle);
        Assert.assertTrue(isParked);
    }

    @Test
    public void whenDriverWantToUnparkHisCar_ThenHeShouldBeAbleToUnpark() throws ParkingLotException {
        service.park(vehicle);
        service.unPark(vehicle);
        boolean unparked = service.isUnparked(vehicle);
        Assert.assertTrue(unparked);
    }

    @Test
    public void whenParkTheCar_ItsShouldParkTheCarTillItsCapacity() throws ParkingLotException {

        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        service.park(vehicle);
        service.park(vehicle2);
        service.park(vehicle3);
        boolean parked = service.isParked(vehicle);
        boolean parked1 = service.isParked(vehicle2);
        boolean parked2 = service.isParked(vehicle3);
        Assert.assertTrue(parked && parked1 && parked2);

    }

    @Test
    public void whenParkTheCar_ItShouldCheckItsParkingCarShouldNotBeMoreThanLotCapacity() throws ParkingLotException {
        try {
            Object vehicle2 = new Object();
            Object vehicle3 = new Object();
            service.park(vehicle);
            service.park(vehicle2);
            service.park(vehicle3);

            Object vehicle4 = new Object();
            service.park(vehicle4);
        } catch (ParkingLotException e) {
            System.out.println("Slot1 Is Full");
            Assert.assertEquals(e.type, ParkingLotException.ExceptionType.Lot_Not_Available);
        }
    }

    @Test
    public void whenLotFull_ItShouldInformAirportSecurityService() throws ParkingLotException {
        try {
            Object vehicle2 = new Object();
            Object vehicle3 = new Object();
            service.park(vehicle);
            service.park(vehicle2);
            service.park(vehicle3);

            Object vehicle4 = new Object();
            service.park(vehicle4);

        } catch (ParkingLotException e) {
            System.out.println("Slot1 Is Full");
            Assert.assertEquals(e.type, ParkingLotException.ExceptionType.Lot_Not_Available);
        }
    }

    @Test
    public void whenLotAvailable_ItShouldReturnAvaliableSlotStatus() throws ParkingLotException {
        service.park(vehicle);
        LotStatus.Status status = service.checkStatusForAirport();
        Assert.assertEquals(LotStatus.Status.Lot_Available, status);
    }

    @Test
    public void whenLotCapacityFull_AndUnparkTheCarThenItShouldReturnStatusEmptyAgain() throws ParkingLotException {
        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        service.park(vehicle);
        service.park(vehicle2);
        service.park(vehicle3);

        service.unPark(1);
        service.unPark(2);
        LotStatus.Status status = service.checkStatusForOwner();
        Assert.assertEquals(LotStatus.Status.Lot_Available, status);
    }

}


