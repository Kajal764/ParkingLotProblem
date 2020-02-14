package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ParkingLotTestCases {

    VehicleInfo vehicle;
    ParkingLotService service;
    int capacity = 3;


    @Before
    public void setUp() {
        vehicle = new VehicleInfo(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        service = new ParkingLotService(capacity);
    }

    @Test
    public void whenDriverParkHisCar_SpaceTokenShouldReturnTrue() throws ParkingLotException {
        service.park(1,vehicle);
        boolean isParked = service.isParked(vehicle);
        Assert.assertTrue(isParked);
    }

    @Test
    public void whenDriverWantToUnparkHisCar_ThenHeShouldBeAbleToUnpark() throws ParkingLotException {
        service.park(1,vehicle);
        service.unPark(vehicle);
        boolean unparked = service.isUnparked(vehicle);
        Assert.assertTrue(unparked);
    }

    @Test
    public void whenParkTheCar_ItsShouldParkTheCarTillItsCapacity() throws ParkingLotException {

        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        service.park(1,vehicle);
        service.park(2,vehicle2);
        service.park(3,vehicle3);
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
            service.park(1,vehicle);
            service.park(2,vehicle2);
            service.park(3,vehicle3);

            Object vehicle4 = new Object();
            service.park(4,vehicle4);
        } catch (ParkingLotException e) {
            Assert.assertEquals(e.type, ParkingLotException.ExceptionType.Lot_Not_Available);
        }
    }

    @Test
    public void whenLotFull_ItShouldInformAirportSecurityService() throws ParkingLotException {
        AirportSecurity airportSecurity=new AirportSecurity();
            Object vehicle2 = new Object();
            Object vehicle3 = new Object();
            service.park(1,vehicle);
            service.park(2,vehicle2);
            service.park(3,vehicle3);
            boolean capacityFull = airportSecurity.isCapacityFull();
            Assert.assertTrue(capacityFull);

    }

    @Test
    public void whenLotAvailable_ItShouldReturnAvaliableSlotStatus() throws ParkingLotException {
        Object vehicle2 = new Object();
        service.park(1,vehicle);
        service.park(2,vehicle2);
        AirportSecurity airportSecurity=new AirportSecurity();
        boolean capacityFull = airportSecurity.isCapacityFull();
        Assert.assertFalse(capacityFull);
    }

    @Test
    public void whenLotCapacityFull_AndUnparkTheCarThenItShouldReturnStatusEmptyAgain() throws ParkingLotException {
        Object vehicle2 = new Object();
        Object vehicle3 = new Object();
        service.park(1,vehicle);
        service.park(2,vehicle2);
        service.park(3,vehicle3);
        service.unPark(vehicle2);
        service.unPark(vehicle3);

        OwnerInfo ownerInfo=new OwnerInfo();
        boolean lotAvailable = ownerInfo.isLotAvailable();
        Assert.assertTrue(lotAvailable);

    }

    @Test
    public void whenParkTheCarOwner_ShouldBeAbleToParkCarBasedOnItsChoice() throws ParkingLotException {
        service.park(5,vehicle);
        boolean parked = service.isParked( vehicle);
        Assert.assertTrue(parked);
    }

    @Test
    public void whenUnParkedTheCar_SystermShouldAbleToUnparkedThatCarOnly() throws ParkingLotException {
        service.park(1,vehicle);
        service.unPark(vehicle);
        boolean unparked = service.isUnparked(vehicle);
        Assert.assertTrue(unparked);
    }

    @Test
    public void whenParkTheCar_TheParkingTimeAndInformParkTime_ShouldBeEqual() throws ParkingLotException {
        service.park(1,vehicle);
        String time = vehicle.getTime();
        String parkTime = service.getParkTime();
        Assert.assertEquals(parkTime,time);
    }

}


