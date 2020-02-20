package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class ParkingLotTestCases {

    VehicleInfo vehicle;
    ParkingLotService service;

    @Before
    public void setUp() {
        vehicle=new VehicleInfo("Sonam",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.White,LocalTime.now());
        service = new ParkingLotService(100,5);
    }

    @Test
    public void whenDriverParkHisCar_SpaceTokenShouldReturnTrue() throws ParkingLotException {
        service.park(vehicle);
        int isParked = service.isParked(vehicle);
        Assert.assertEquals(1,isParked);
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

        int parked = 0;
        for (int i = 1; i <= 100; i++) {
            VehicleInfo vehicle1=new VehicleInfo("Nisha",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.White,LocalTime.now());
            service.park(vehicle1);
             parked = service.isParked(vehicle1);
        }
        Assert.assertEquals(100,parked);
        }


    @Test
    public void whenParkTheCar_ItShouldCheckItsParkingCarShouldNotBeMoreThanLotCapacity() {
        try {
            for (int i = 1; i <= 101; i++) {
                service.park(vehicle);
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
            VehicleInfo vehicle2=new VehicleInfo("Nisha",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.White,LocalTime.now());
            service.park(vehicle2);
            service.isParked(vehicle2);
        }
        boolean slotAvailable = airportSecurity.checkStatus();
        Assert.assertFalse(slotAvailable);

    }


    @Test
    public void givenCarwhenPark_ThenItShouldFirstCheckEmptySpace() throws ParkingLotException {
        service.park(vehicle);
        int parked = service.isParked(vehicle);
        VehicleInfo vehicle1=new VehicleInfo("Nisha",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.White,LocalTime.now());
        service.park(vehicle1);
        service.unPark(vehicle1);
        service.isParked(vehicle1);

        VehicleInfo vehicle2=new VehicleInfo("Nisha",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.White,LocalTime.now());
        service.park(vehicle2);
        int parked3 = service.isParked(vehicle2);

        Assert.assertEquals(1,parked);
        Assert.assertEquals(21,parked3);
    }

    @Test
    public void whenLotAvailable_ItShouldReturnAvaliableSlotStatus() throws ParkingLotException {
        service.park(vehicle);
        VehicleInfo vehicle1=new VehicleInfo("Smita",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.White,LocalTime.now());
        service.park(vehicle1);
        AirportSecurityInfo airportSecurity=new AirportSecurityInfo();
        boolean capacityFull = airportSecurity.checkStatus();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void whenLotCapacityFull_AndUnparkTheCarThenItShouldReturnStatusEmptyAgain() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Nisha",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.White,LocalTime.now());
       service.park(vehicle);
        for (int i = 1; i <= 99; i++) {
            service.park(vehicle1);
        }
        service.unPark(vehicle);
        OwnerInfo ownerInfo=new OwnerInfo();
        boolean lotAvailable = ownerInfo.checkStatus();
        Assert.assertFalse(lotAvailable);
    }

    @Test
    public void whenParkTheCarOwner_ShouldBeAbleToParkCarBasedOnItsChoice() throws ParkingLotException {
        service.park(vehicle);
        int parked = service.isParked(vehicle);
        Assert.assertEquals(1,parked);
    }

    @Test
    public void whenUnParkedTheCar_SystermShouldAbleToUnparkedThatCarOnly() throws ParkingLotException {
        service.park(vehicle);
        service.unPark(vehicle);
        boolean unparked = service.isUnparked(vehicle);
        Assert.assertTrue(unparked);
    }

    @Test
    public void whenParkTheCar_ItShouldParkEvenlyInSlots() throws ParkingLotException {
        service.park(vehicle);
        int parked = service.isParked(vehicle);

        VehicleInfo vehicle1=new VehicleInfo("Pranali",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.White,LocalTime.now());
        service.park(vehicle1);
        int parked1 = service.isParked(vehicle1);

        VehicleInfo vehicle2=new VehicleInfo("Kajal",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.White,LocalTime.now());
        service.park(vehicle2);
        int parked2 = service.isParked(vehicle2);

        Assert.assertEquals(1,parked);
        Assert.assertEquals(21,parked1);
        Assert.assertEquals(41,parked2);
    }

    @Test
    public void whenHandicapDriverWantToParkHisCar_ThatSlotShouldBeNearest() throws ParkingLotException {
        service.park(vehicle);
        VehicleInfo vehicle1=new VehicleInfo("Nisha", CHECKFORPARK.Handicap,  VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.White,LocalTime.now());
        service.park(vehicle1);
        int parked = service.isParked(vehicle);
        int parked1 = service.isParked(vehicle1);
        Assert.assertEquals(21,parked);
        Assert.assertEquals(1,parked1);
    }

    @Test
    public void givenLargeCars_WhenParkItShouldParkInFreeSpace_SoThatEasierToManoeuvre() throws ParkingLotException {

        VehicleInfo vehicle1=new VehicleInfo("Nisha", CHECKFORPARK.Normal, VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.White,LocalTime.now());
        service.park(vehicle1);

        VehicleInfo vehicle2=new VehicleInfo("Nisha",CHECKFORPARK.Large_car, VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.White,LocalTime.now());
         service.park(vehicle2);
        int parked = service.isParked(vehicle1);
        int parked1 = service.isParked(vehicle2);

        Assert.assertEquals(1,parked);
        Assert.assertEquals(39,parked1);
    }

    @Test
    public void givenWhiteCars_WhenParkThenItShouldBeAbleToInvestigateSlotNo() throws ParkingLotException {
        service.park(vehicle);
        service.isParked(vehicle);

        VehicleInfo vehicle1=new VehicleInfo("Nisha",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.White,LocalTime.now());
        service.park(vehicle1);

        Map<Integer, Object> carList = service.getCarList(VehicleData.White);
        Assert.assertTrue(carList.containsKey(1));
        Assert.assertTrue(carList.containsKey(21));
    }

    @Test
    public void givenCars_WhenPark_ShouldAddedOnlyWhiteCars() throws ParkingLotException {
        service.park(vehicle);
        VehicleInfo vehicle1=new VehicleInfo("Prashant",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.Black,LocalTime.now());
        VehicleInfo vehicle2=new VehicleInfo("Amar",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4546",VehicleData.White,LocalTime.now());
        service.park(vehicle1);
        service.park(vehicle2);

        Map<Integer, Object> carList = service.getCarList(VehicleData.White);
        Assert.assertTrue(carList.containsKey(1));
        Assert.assertTrue(carList.containsKey(41));
    }


    @Test
    public void givenCars_WhenPark_ThenItShouldAbleToInvestigateBlueToyotoCars() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Prashant",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.Blue,LocalTime.now());
        VehicleInfo vehicle2=new VehicleInfo("Amar",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4546",VehicleData.Blue,LocalTime.now());
        service.park(vehicle2);
        service.park(vehicle1);

        Map<Integer, Object> toyotoCars = service.getCarList(VehicleData.Blue,VehicleData.Toyoto);
        Assert.assertTrue(toyotoCars.containsKey(1));
        Assert.assertTrue(toyotoCars.containsKey(21));
    }

    @Test
    public void givenCars_WhenPark_ThenItShouldAbleToFindBlueToyotoCarsOnly() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Prashant",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4646",VehicleData.Blue,LocalTime.now());
        VehicleInfo vehicle2=new VehicleInfo("Amar",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4546",VehicleData.White,LocalTime.now());
        service.park(vehicle1);
        service.park(vehicle2);

        Map<Integer, Object> toyotoCars = service.getCarList(VehicleData.Blue,VehicleData.Toyoto);
        Assert.assertFalse(toyotoCars.containsKey(21));
        Assert.assertTrue(toyotoCars.containsKey(1));
    }

    @Test
    public void givenCars_WhenPark_ItShouldAbleToFindBMWCars() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Prashant",CHECKFORPARK.Normal,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.Blue,LocalTime.now());
        VehicleInfo vehicle2=new VehicleInfo("Amar",CHECKFORPARK.Normal,VehicleData.Toyoto,"MH 01 CQ 4546",VehicleData.White,LocalTime.now());
        service.park(vehicle1);
        service.park(vehicle2);

        Map<Integer, Object> cars = service.getCarList(VehicleData.BMW);
        Assert.assertTrue(cars.containsKey(1));
        Assert.assertFalse(cars.containsKey(21));
    }


    @Test
    public void givenCarWhenPark_ItShouldReturnCarThatHasBeenParkedInTheLast30Minutes() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Pranali",CHECKFORPARK.Normal,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.Blue,LocalTime.now());
        service.park(vehicle1);
        VehicleInfo vehicle2=new VehicleInfo("Pranali",CHECKFORPARK.Normal,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.Blue,LocalTime.now());
        service.park(vehicle2);
        VehicleInfo vehicle3=new VehicleInfo("Pranali",CHECKFORPARK.Normal,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.Blue,LocalTime.now());
        service.park(vehicle3);
        vehicle1.time= LocalTime.now().minusMinutes(30);
        vehicle3.time=LocalTime.now().minusMinutes(40);
        List<Integer> carWithin30Min = service.getCarWithin30Min();
        Assert.assertTrue(carWithin30Min.contains(1));
        Assert.assertTrue(carWithin30Min.contains(41));
    }

}


