package parkinglot;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTestCases {


    @Test
    public void whenDriverParkHisCar_SpaceTokenShouldReturnTrue() throws ParkingLotException {
        ParkingLotService parkingLot=new ParkingLotService();
        CarInfo car1=new CarInfo("KL 7221","SuzukiSwift","white",2.0);
        CarInfo car2=new CarInfo("MH 01 CD 3367","Suv","red",2.15);
        CarInfo car3=new CarInfo("TN 7221","Mpv","white",2.15);
        CarInfo car4=new CarInfo("RMD 387","Mrecedes","blue",2.30);
        CarInfo car5=new CarInfo("AD 221","AudiR8","black",2.45);
        parkingLot.park(car1);
        parkingLot.park(car2);
        parkingLot.park(car3);
        parkingLot.park(car4);
       int parkingCount = parkingLot.park(car5);
       Assert.assertEquals(5,parkingCount);
    }

    @Test
    public void whenParkCar_ItShouldFirstCheckSpaceIsEmpty_ThenSpaceTokenShouldReturnTrue() throws ParkingLotException {
        ParkingLotService parkingLot= new ParkingLotService();
        CarInfo car1=new CarInfo("KL 7221","SuzukiSwift","white",2.0);
        int integerBooleanHashMap = parkingLot.park(car1);
        Assert.assertEquals(1,integerBooleanHashMap);
    }


    @Test
    public void whenParkTheCar_ItShouldCheckItsParkingCarShouldNotBeMoreThan100()  {
        Integer status=null;
        try {
            ParkingLotService parkingLot = new ParkingLotService();
            for (int i = 1; i <= 100; i++) {
                CarInfo car1 = new CarInfo("KL 7221", "SuzukiSwift", "white", 2.0);
                int integerBooleanHashMap1 = parkingLot.park(car1); }

            CarInfo car2 = new CarInfo("MH 01 CD 3367", "Suv", "red", 2.15);
            int data = parkingLot.park(car2);
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
                CarInfo car1 = new CarInfo("KL 7221", "SuzukiSwift", "white", 2.0);
                parkingLot.park(car1);
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
        CarInfo car1 = new CarInfo("KL 7221", "SuzukiSwift", "white", 2.0);
        parkingLot.park(car1);
        LotStatus.Status status = parkingLot.checkStatusForAirport();
        Assert.assertEquals( LotStatus.Status.Lot_Available,status);
    }

    @Test
    public void whenLotCapacityFull_AndUnparkTheCarThenItShouldReturnStatusEmptyAgain() throws ParkingLotException {
            ParkingLotService parkingLot = new ParkingLotService();
            for (int i = 1; i <= 101; i++) {
                CarInfo car1 = new CarInfo("KL 7221 "+i, "SuzukiSwift", "white", 2.0);
                parkingLot.park(car1);
            }
            parkingLot.unPark(1);
            parkingLot.unPark(2);
            LotStatus.Status status = parkingLot.checkStatusForOwner();
            System.out.println("Slot1 Is Full");
            Assert.assertEquals(LotStatus.Status.Lot_Available, status);

}


}


