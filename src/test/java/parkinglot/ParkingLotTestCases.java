package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ParkingLotTestCases {

    VehicleInfo vehicle;
    ParkingLotService service;
    int capacity = 100;
    private boolean isHandicap;

    @Before
    public void setUp() {
        vehicle = new VehicleInfo(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        service = new ParkingLotService(capacity);
    }

    @Test
    public void whenDriverParkHisCar_SpaceTokenShouldReturnTrue() throws ParkingLotException {
        service.park(vehicle,isHandicap);
        int isParked = service.isParked(vehicle);
        Assert.assertEquals(1,isParked);
    }

    @Test
    public void whenDriverWantToUnparkHisCar_ThenHeShouldBeAbleToUnpark() throws ParkingLotException {
        service.park(vehicle,isHandicap);
        service.unPark(vehicle);
        boolean unparked = service.isUnparked(vehicle);
        Assert.assertTrue(unparked);
    }

    @Test
    public void whenParkTheCar_ItsShouldParkTheCarTillItsCapacity() throws ParkingLotException {

        int parked = 0;
        for (int i = 1; i <= 100; i++) {
            Object vehicle2 = new Object();
            service.park(vehicle2,isHandicap);
             parked = service.isParked(vehicle2);
        }
        Assert.assertEquals(100,parked);
        }


    @Test
    public void whenParkTheCar_ItShouldCheckItsParkingCarShouldNotBeMoreThanLotCapacity() throws ParkingLotException {
        try {
            for (int i = 1; i <= 101; i++) {
                Object vehicle = new Object();
                service.park(vehicle,isHandicap);
                service.isParked(vehicle);
            }
        } catch (ParkingLotException e) {
            Assert.assertEquals(e.type, ParkingLotException.ExceptionType.Lot_Not_Available);
        }
    }

    @Test
    public void whenLotFull_ItShouldInformAirportSecurityService() throws ParkingLotException {
        AirportSecurityInfo airportSecurity=new AirportSecurityInfo();
        for (int i = 1; i <= 100; i++) {
                Object vehicle2 = new Object();
                service.park(vehicle2,isHandicap);
                service.isParked(vehicle2);
            }
            boolean capacityFull = airportSecurity.isLotAvailable();
            Assert.assertTrue(capacityFull);

    }


    @Test
    public void whenLotAvailable_ItShouldReturnAvaliableSlotStatus() throws ParkingLotException {
        Object vehicle2 = new Object();
        service.park(vehicle,isHandicap);
        service.park(vehicle2,isHandicap);
        AirportSecurityInfo airportSecurity=new AirportSecurityInfo();
        boolean capacityFull = airportSecurity.isLotAvailable();
        Assert.assertFalse(capacityFull);
    }

    @Test
    public void whenLotCapacityFull_AndUnparkTheCarThenItShouldReturnStatusEmptyAgain() throws ParkingLotException {
       Object vehicle1=new Object();
       int parked=0;
       service.park(vehicle1,isHandicap);
        for (int i = 1; i <= 99; i++) {
            Object vehicle2 = new Object();
            service.park(vehicle2,isHandicap);
        }
        service.unPark(vehicle1);
        OwnerInfo ownerInfo=new OwnerInfo();
        boolean lotAvailable = ownerInfo.isLotAvailable();
        Assert.assertTrue(lotAvailable);
    }

    @Test
    public void whenParkTheCarOwner_ShouldBeAbleToParkCarBasedOnItsChoice() throws ParkingLotException {
        service.park(vehicle,isHandicap);
        int parked = service.isParked(vehicle);
        Assert.assertEquals(1,parked);
    }

    @Test
    public void whenUnParkedTheCar_SystermShouldAbleToUnparkedThatCarOnly() throws ParkingLotException {
        service.park(vehicle,isHandicap);
        service.unPark(vehicle);
        boolean unparked = service.isUnparked(vehicle);
        Assert.assertTrue(unparked);
    }

    @Test
    public void whenParkTheCar_TheParkingTimeAndInformParkTime_ShouldBeEqual() throws ParkingLotException {
        service.park(vehicle,isHandicap);
        String time = vehicle.getTime();
        String parkTime = service.getParkTime();
        Assert.assertEquals(parkTime,time);
    }

    @Test
    public void whenParkTheCar_ItShouldParkEvenlyInSlots() throws ParkingLotException {
        service.park(vehicle,isHandicap);
        int parked = service.isParked(vehicle);

        VehicleInfo vehicle2 =new VehicleInfo(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        service.park(vehicle2,isHandicap);
        int parked1 = service.isParked(vehicle2);

        VehicleInfo vehicle3 =new VehicleInfo(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        service.park(vehicle3,isHandicap);
        int parked2 = service.isParked(vehicle3);

        Assert.assertEquals(1,parked);
        Assert.assertEquals(21,parked1);
        Assert.assertEquals(41,parked2);
    }

    @Test
    public void whenHandicapDriverWantToParkHisCar_HisSlotShouldBeNearest() throws ParkingLotException {
        isHandicap=false;
        service.park(vehicle,isHandicap);
        isHandicap=true;
        Object vehicle3=new Object();
        service.park(vehicle3,isHandicap);
        int parked = service.isParked(vehicle);
        int parked2 = service.isParked(vehicle3);
        Assert.assertEquals(21,parked);
        Assert.assertEquals(1,parked2);

    }
}


