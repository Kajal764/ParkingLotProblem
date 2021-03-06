package com.bridgelabz.parkinglotservice.parking.lot;

import com.bridgelabz.parkinglotservice.ParkingLotService;
import com.bridgelabz.parkinglotservice.enumeration.CHECKFORPARK;
import com.bridgelabz.parkinglotservice.enumeration.VehicleData;
import com.bridgelabz.parkinglotservice.exception.ParkingLotException;
import com.bridgelabz.parkinglotservice.service.AirportSecurityInfo;
import com.bridgelabz.parkinglotservice.service.OwnerInfo;
import com.bridgelabz.parkinglotservice.service.VehicleInfo;
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
        vehicle=new VehicleInfo("Sonam", CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.WHITE,LocalTime.now());
        service = new ParkingLotService(100,5);
    }

    @Test
    public void givenCarWhenPark_ItsShouldReturnTrue() throws ParkingLotException {
        service.park(vehicle);
        int isParked = service.isParked(vehicle);
        Assert.assertEquals(1,isParked);
    }

    @Test
    public void givenCarWhenUnpark_ShouldBeAbleToUnpark() throws ParkingLotException {
        service.park(vehicle);
        service.unPark(vehicle);
        boolean unparked = service.isUnparked(vehicle);
        Assert.assertTrue(unparked);
    }

    @Test
    public void givenCarWhenPark_ItsShouldParkCarTillItsCapacity() throws ParkingLotException {
        int parked = 0;
        for (int i = 1; i <= 100; i++) {
            VehicleInfo vehicle1=new VehicleInfo("Nisha",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.WHITE,LocalTime.now());
            service.park(vehicle1);
             parked = service.isParked(vehicle1);
        }
        Assert.assertEquals(100,parked);
        }


    @Test
    public void givenCarWhenPark_ItShouldCheckItsParkingCarShouldNotBeMoreThanLotCapacity() throws ParkingLotException {
       try {
           for (int i = 1; i <= 101; i++) {
               service.park(vehicle);
               service.isParked(vehicle);
           }
       }catch (ParkingLotException e)
       {
           Assert.assertEquals(e.type, ParkingLotException.ExceptionType.Lot_Not_Available);
       }

    }

    @Test
    public void givenCarwhenLotFull_ItShouldInformAirportSecurityService() throws ParkingLotException {
        AirportSecurityInfo airportSecurity=new AirportSecurityInfo();
        for (int i = 1; i <= 100; i++) {
            VehicleInfo vehicle2=new VehicleInfo("Nisha",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.WHITE,LocalTime.now());
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
        VehicleInfo vehicle1=new VehicleInfo("Nisha",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.WHITE,LocalTime.now());
        service.park(vehicle1);
        service.unPark(vehicle1);
        service.isParked(vehicle1);

        VehicleInfo vehicle2=new VehicleInfo("Nisha",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.WHITE,LocalTime.now());
        service.park(vehicle2);
        int parked3 = service.isParked(vehicle2);

        Assert.assertEquals(1,parked);
        Assert.assertEquals(21,parked3);
    }

    @Test
    public void givenCarwhenLotAvailable_ItShouldReturnAvaliableSlotStatus() throws ParkingLotException {
        service.park(vehicle);
        VehicleInfo vehicle1=new VehicleInfo("Smita",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.WHITE,LocalTime.now());
        service.park(vehicle1);
        AirportSecurityInfo airportSecurity=new AirportSecurityInfo();
        boolean capacityFull = airportSecurity.checkStatus();
        Assert.assertTrue(capacityFull);
    }

    @Test
    public void givenCarwhenLotCapacityFull_AndUnparkTheCar_ItShouldReturnStatusEmptyAgain() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Nisha",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.WHITE,LocalTime.now());
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
    public void givenCarwhenPark_OwnerShouldBeAbleToParkCarBasedOnItsChoice() throws ParkingLotException {
        service.park(vehicle);
        int parked = service.isParked(vehicle);
        Assert.assertEquals(1,parked);
    }

    @Test
    public void givenCarWhenUnParked_ShouldAbleToUnparkedThatCarOnly() throws ParkingLotException {
        service.park(vehicle);
        service.unPark(vehicle);
        boolean unparked = service.isUnparked(vehicle);
        Assert.assertTrue(unparked);
    }

    @Test
    public void whenParkTheCar_ItShouldParkEvenlyInSlots() throws ParkingLotException {
        service.park(vehicle);
        int parked = service.isParked(vehicle);

        VehicleInfo vehicle1=new VehicleInfo("Pranali",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.WHITE,LocalTime.now());
        service.park(vehicle1);
        int parked1 = service.isParked(vehicle1);

        VehicleInfo vehicle2=new VehicleInfo("Kajal",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.WHITE,LocalTime.now());
        service.park(vehicle2);
        int parked2 = service.isParked(vehicle2);

        Assert.assertEquals(1,parked);
        Assert.assertEquals(21,parked1);
        Assert.assertEquals(41,parked2);
    }

    @Test
    public void whenHandicapDriverWantToParkHisCar_ThatSlotShouldBeNearest() throws ParkingLotException {
        service.park(vehicle);
        VehicleInfo vehicle1=new VehicleInfo("Nisha", CHECKFORPARK.HANDICAP,  VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.WHITE,LocalTime.now());
        service.park(vehicle1);
        int parked = service.isParked(vehicle);
        int parked1 = service.isParked(vehicle1);
        Assert.assertEquals(21,parked);
        Assert.assertEquals(1,parked1);
    }

    @Test
    public void givenLargeCars_WhenParkItShouldParkInFreeSpace_SoThatEasierToManoeuvre() throws ParkingLotException {

        VehicleInfo vehicle1=new VehicleInfo("Nisha", CHECKFORPARK.NORMAL, VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.WHITE,LocalTime.now());
        service.park(vehicle1);

        VehicleInfo vehicle2=new VehicleInfo("Nisha",CHECKFORPARK.LARGE_CAR, VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.WHITE,LocalTime.now());
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

        VehicleInfo vehicle1=new VehicleInfo("Nisha",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.WHITE,LocalTime.now());
        service.park(vehicle1);

        Map<Integer, Object> carList = service.getCarList(VehicleData.WHITE);
        Assert.assertTrue(carList.containsKey(1));
        Assert.assertTrue(carList.containsKey(21));
    }

    @Test
    public void givenCars_WhenPark_ShouldAddedOnlyWhiteCars() throws ParkingLotException {
        service.park(vehicle);
        VehicleInfo vehicle1=new VehicleInfo("Prashant",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.BLACK,LocalTime.now());
        VehicleInfo vehicle2=new VehicleInfo("Amar",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4546",VehicleData.WHITE,LocalTime.now());
        service.park(vehicle1);
        service.park(vehicle2);

        Map<Integer, Object> carList = service.getCarList(VehicleData.WHITE);
        Assert.assertTrue(carList.containsKey(1));
        Assert.assertTrue(carList.containsKey(41));
    }


    @Test
    public void givenCars_WhenPark_ThenItShouldAbleToInvestigateBlueToyotoCars() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Prashant",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        VehicleInfo vehicle2=new VehicleInfo("Amar",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4546",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle2);
        service.park(vehicle1);

        Map<Integer, Object> toyotoCars = service.getCarList(VehicleData.BLUE,VehicleData.TOYOTO);
        Assert.assertTrue(toyotoCars.containsKey(1));
        Assert.assertTrue(toyotoCars.containsKey(21));
    }

    @Test
    public void givenCars_WhenPark_ThenItShouldAbleToFindBlueToyotoCarsOnly() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Prashant",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        VehicleInfo vehicle2=new VehicleInfo("Amar",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4546",VehicleData.WHITE,LocalTime.now());
        service.park(vehicle1);
        service.park(vehicle2);

        Map<Integer, Object> toyotoCars = service.getCarList(VehicleData.BLUE,VehicleData.TOYOTO);
        Assert.assertFalse(toyotoCars.containsKey(21));
        Assert.assertTrue(toyotoCars.containsKey(1));
    }

    @Test
    public void givenCars_WhenPark_ItShouldAbleToFindBMWCars() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Prashant",CHECKFORPARK.NORMAL,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        VehicleInfo vehicle2=new VehicleInfo("Amar",CHECKFORPARK.NORMAL,VehicleData.TOYOTO,"MH 01 CQ 4546",VehicleData.WHITE,LocalTime.now());
        service.park(vehicle1);
        service.park(vehicle2);

        Map<Integer, Object> cars = service.getCarList(VehicleData.BMW);
        Assert.assertTrue(cars.containsKey(1));
        Assert.assertFalse(cars.containsKey(21));
    }


    @Test
    public void givenCarWhenPark_ItShouldReturnCarThatHasBeenParkedInTheLast30Minutes() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Pranali",CHECKFORPARK.NORMAL,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle1);
        VehicleInfo vehicle2=new VehicleInfo("Pranali",CHECKFORPARK.NORMAL,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle2);
        VehicleInfo vehicle3=new VehicleInfo("Pranali",CHECKFORPARK.NORMAL,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle3);
        vehicle1.time= LocalTime.now().minusMinutes(30);
        vehicle3.time=LocalTime.now().minusMinutes(40);
        List<Integer> carWithin30Min = service.getCarWithin30Min();
        Assert.assertTrue(carWithin30Min.contains(1));
        Assert.assertTrue(carWithin30Min.contains(41));
    }


    @Test
    public void givenCarWhenPark_ThenItSlotNoContainsKeyShouldReturnTrue() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Pranali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle1);
        VehicleInfo vehicle2=new VehicleInfo("Pranali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle2);
        boolean assignlot = service.assignlot(3, 45);
        Assert.assertTrue(assignlot);
    }

    @Test
    public void givenCarWhenPark_ThenItShouldReturnSlotWiseVehicleInfo() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("Pranali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle1);
        VehicleInfo vehicle2=new VehicleInfo("Pranali",CHECKFORPARK.NORMAL,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle2);

        VehicleInfo vehicle3=new VehicleInfo("Pranali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle3);

        VehicleInfo vehicle4=new VehicleInfo("Pranali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle4);

        VehicleInfo vehicle5=new VehicleInfo("Pranali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle5);

        VehicleInfo vehicle6=new VehicleInfo("Pranali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle6);
        VehicleInfo vehicle7=new VehicleInfo("Pranali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle7);
        Map<Integer, VehicleInfo> vehicleInfoMap =service.getHandicapDriverInfo(2);
        Map<Integer, VehicleInfo> handicapDriverInfo = service.getHandicapDriverInfo(4);
        Assert.assertTrue(vehicleInfoMap.containsKey(21)&& vehicleInfoMap.containsKey(22));
        Assert.assertTrue(handicapDriverInfo.containsKey(61));

    }

    @Test
    public void givenCarWhenPark_ThenItShouldReturnSlotWiseHandicapDriverInfo() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("runali",CHECKFORPARK.NORMAL,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle1);

        VehicleInfo vehicle2=new VehicleInfo("ali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle2);

        VehicleInfo vehicle3=new VehicleInfo("Pranali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle3);

        VehicleInfo vehicle4=new VehicleInfo("Pranali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle4);

        VehicleInfo vehicle5=new VehicleInfo("Pranali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle5);

        Map<Integer, VehicleInfo> vehicleInfoMap =service.getHandicapDriverInfo(2);
        Assert.assertTrue(vehicleInfoMap.containsKey(21));
        Map<Integer, VehicleInfo> vehicle =service.getHandicapDriverInfo(4);
        Assert.assertTrue(vehicle.containsKey(61));
    }

    @Test
    public void givenCarWhenPark_ThenItShouldNotReturnSlotWiseNormalDriverInfo() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("runali",CHECKFORPARK.NORMAL,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle1);

        VehicleInfo vehicle2=new VehicleInfo("ali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle2);

        VehicleInfo vehicle3=new VehicleInfo("Pranali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle3);

        VehicleInfo vehicle4=new VehicleInfo("Pranali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle4);

        VehicleInfo vehicle5=new VehicleInfo("Pranali",CHECKFORPARK.NORMAL,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle5);

        Map<Integer, VehicleInfo> vehicleInfoMap =service.getHandicapDriverInfo(2);
        Map<Integer, VehicleInfo> vehicle =service.getHandicapDriverInfo(4);
        Assert.assertTrue(vehicleInfoMap.containsKey(21));
        Assert.assertFalse(vehicle.containsKey(61));
    }

    @Test
    public void givenCarWhenParkThenItShouldReturnItsCorrectRecors() throws ParkingLotException {
        VehicleInfo vehicle1=new VehicleInfo("runali",CHECKFORPARK.NORMAL,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle1);
        VehicleInfo vehicle2=new VehicleInfo("ali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        service.park(vehicle2);
        VehicleInfo vehicle3=new VehicleInfo("Pranali",CHECKFORPARK.HANDICAP,VehicleData.BMW,"MH 01 CQ 4646",VehicleData.BLUE,LocalTime.now());
        int park = service.park(vehicle3);
        Assert.assertEquals(3,park);
    }
}


