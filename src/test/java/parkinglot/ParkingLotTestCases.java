package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class ParkingLotTestCases {

    VehicleInfo vehicle;
    ParkingLotService service;
    private boolean isHandicap;

    @Before
    public void setUp() {
        vehicle=new VehicleInfo("Sonam",VehicleData.carType.Toyoto,"MH 01 CQ 4646",VehicleData.CarSize.Small_Car,VehicleData.Color.White,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        service = new ParkingLotService(100,5);
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
            VehicleInfo vehicle1=new VehicleInfo("Nisha",VehicleData.carType.Toyoto,"MH 01 CQ 4646",VehicleData.CarSize.Small_Car,VehicleData.Color.White,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
            service.park(vehicle1,isHandicap);
             parked = service.isParked(vehicle1);
        }
        Assert.assertEquals(100,parked);
        }


    @Test
    public void whenParkTheCar_ItShouldCheckItsParkingCarShouldNotBeMoreThanLotCapacity() throws ParkingLotException {
        try {
            for (int i = 1; i <= 101; i++) {
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
            VehicleInfo vehicle2=new VehicleInfo("Nisha",VehicleData.carType.Toyoto,"MH 01 CQ 4646",VehicleData.CarSize.Small_Car,VehicleData.Color.White,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
            service.park(vehicle2,isHandicap);
            service.isParked(vehicle2);
        }
        boolean slotAvailable = airportSecurity.checkStatus();
        Assert.assertFalse(slotAvailable);

    }


    @Test
    public void whenLotAvailable_ItShouldReturnAvaliableSlotStatus() throws ParkingLotException {
        service.park(vehicle,isHandicap);
        VehicleInfo vehicle1=new VehicleInfo("Smita",VehicleData.carType.Toyoto,"MH 01 CQ 4646",VehicleData.CarSize.Small_Car,VehicleData.Color.White,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        service.park(vehicle1,isHandicap);
        AirportSecurityInfo airportSecurity=new AirportSecurityInfo();
        boolean capacityFull = airportSecurity.checkStatus();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void whenLotCapacityFull_AndUnparkTheCarThenItShouldReturnStatusEmptyAgain() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Nisha",VehicleData.carType.Toyoto,"MH 01 CQ 4646",VehicleData.CarSize.Small_Car,VehicleData.Color.White,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
       service.park(vehicle,isHandicap);
        for (int i = 1; i <= 99; i++) {
            Object vehicle2 = new Object();
            service.park(vehicle1,isHandicap);
        }
        service.unPark(vehicle);
        OwnerInfo ownerInfo=new OwnerInfo();
        boolean lotAvailable = ownerInfo.checkStatus();
        Assert.assertFalse(lotAvailable);
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

        VehicleInfo vehicle1=new VehicleInfo("Pranali",VehicleData.carType.Toyoto,"MH 01 CQ 4646",VehicleData.CarSize.Small_Car,VehicleData.Color.White,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        service.park(vehicle1,isHandicap);
        int parked1 = service.isParked(vehicle1);

        VehicleInfo vehicle2=new VehicleInfo("Kajal",VehicleData.carType.Toyoto,"MH 01 CQ 4646",VehicleData.CarSize.Small_Car,VehicleData.Color.White,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        service.park(vehicle2,isHandicap);
        int parked2 = service.isParked(vehicle2);

        Assert.assertEquals(1,parked);
        Assert.assertEquals(21,parked1);
        Assert.assertEquals(41,parked2);
    }

    @Test
    public void whenHandicapDriverWantToParkHisCar_HisSlotShouldBeNearest() throws ParkingLotException {
        isHandicap=false;
        service.park(vehicle,isHandicap);
        isHandicap=true;
        VehicleInfo vehicle1=new VehicleInfo("Nisha",VehicleData.carType.Toyoto,"MH 01 CQ 4646",VehicleData.CarSize.Small_Car,VehicleData.Color.White,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        service.park(vehicle1,isHandicap);
        int parked = service.isParked(vehicle);
        int parked2 = service.isParked(vehicle1);
        Assert.assertEquals(21,parked);
        Assert.assertEquals(1,parked2);

    }

    @Test
    public void givenLargeCars_WhenParkItShouldParkInFreeSpace_SoThatEasierToManoeuvre() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Nisha",VehicleData.carType.Toyoto,"MH 01 CQ 4646",VehicleData.CarSize.Large_Car,VehicleData.Color.White,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        service.park(vehicle1,isHandicap);
        int parked = service.isParked(vehicle1);
        Assert.assertEquals(1,parked);

    }

    @Test
    public void givenWhiteCars_WhenParkThenItShouldBeAbleToInvestigateSlotNo() throws ParkingLotException {
        service.park(vehicle,isHandicap);
        service.isParked(vehicle);

        VehicleInfo vehicle1=new VehicleInfo("Nisha",VehicleData.carType.Toyoto,"MH 01 CQ 4646",VehicleData.CarSize.Small_Car,VehicleData.Color.White,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        service.park(vehicle1,isHandicap);

        List whiteCar = service.getCarList();
       Assert.assertEquals(1,whiteCar.get(0));
       Assert.assertEquals(21,whiteCar.get(1));

    }

    @Test
    public void givenCars_WhenParkThenOnlyWhiteCarShouldGetAdd() throws ParkingLotException {
        service.park(vehicle,isHandicap);

        VehicleInfo vehicle1=new VehicleInfo("Prashant",VehicleData.carType.Toyoto,"MH 01 CQ 4646",VehicleData.CarSize.Small_Car,VehicleData.Color.Black,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        VehicleInfo vehicle2=new VehicleInfo("Amar",VehicleData.carType.Toyoto,"MH 01 CQ 4546",VehicleData.CarSize.Small_Car,VehicleData.Color.White,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        service.park(vehicle1,isHandicap);
        service.park(vehicle2,isHandicap);


        List whiteCar = service.getCarList();
        Assert.assertEquals(1,whiteCar.get(0));
        Assert.assertEquals(41,whiteCar.get(1));

    }


    @Test
    public void givenCars_WhenPark_ThenItShouldAbleToInvestigateBlueToyotoCars() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Prashant",VehicleData.carType.Toyoto,"MH 01 CQ 4646",VehicleData.CarSize.Small_Car,VehicleData.Color.Blue,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        VehicleInfo vehicle2=new VehicleInfo("Amar",VehicleData.carType.Toyoto,"MH 01 CQ 4546",VehicleData.CarSize.Small_Car,VehicleData.Color.Blue,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        service.park(vehicle2,isHandicap);
        service.park(vehicle1,isHandicap);

        Map<Integer, Object> toyotoCars = service.getToyotoCars();
        Assert.assertTrue(toyotoCars.containsKey(1));
        Assert.assertTrue(toyotoCars.containsKey(21));
    }

    @Test
    public void givenCars_WhenPark_ThenItShouldAbleToFindBlueToyotoCarsOnly() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Prashant",VehicleData.carType.Toyoto,"MH 01 CQ 4646",VehicleData.CarSize.Small_Car,VehicleData.Color.Blue,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        VehicleInfo vehicle2=new VehicleInfo("Amar",VehicleData.carType.Toyoto,"MH 01 CQ 4546",VehicleData.CarSize.Small_Car,VehicleData.Color.White,LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm")));
        service.park(vehicle1,isHandicap);
        service.park(vehicle2,isHandicap);

        Map<Integer, Object> toyotoCars = service.getToyotoCars();
        Assert.assertFalse(toyotoCars.containsKey(21));
        Assert.assertTrue(toyotoCars.containsKey(1));
    }


}


