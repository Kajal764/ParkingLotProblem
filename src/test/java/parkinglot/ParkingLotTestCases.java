package parkinglot;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTestCases {


    @Test
    public void whenDriverParkHisCar_SpaceTokenShouldReturnTrue() throws ParkingLotException {
        ParkingLot parkingLot=new ParkingLot();
        CarInfo car1=new CarInfo("KL 7221","SuzukiSwift","white",2.0);
        CarInfo car2=new CarInfo("MH 01 CD 3367","Suv","red",2.15);
        CarInfo car3=new CarInfo("TN 7221","Mpv","white",2.15);
        CarInfo car4=new CarInfo("RMD 387","Mrecedes","blue",2.30);
        CarInfo car5=new CarInfo("AD 221","AudiR8","black",2.45);
        parkingLot.getData(car1);
        parkingLot.getData(car2);
        parkingLot.getData(car3);
        parkingLot.getData(car4);
       int integerBooleanHashMap5 = parkingLot.getData(car5);
       Assert.assertEquals(5,integerBooleanHashMap5);
    }


    @Test
    public void whenParkCar_ItShouldFirstCheckSpaceIsEmpty_ThenSpaceTokenShouldReturnTrue() throws ParkingLotException {
        ParkingLot parkingLot= new ParkingLot();
        CarInfo car1=new CarInfo("KL 7221","SuzukiSwift","white",2.0);
        int integerBooleanHashMap = parkingLot.getData(car1);
        Assert.assertEquals(1,integerBooleanHashMap);
    }

    @Test
    public void whenUnparkThecar_ItShouldAssignSpaceEmptyForThatToken() throws ParkingLotException {
        ParkingLot parkingLot=new ParkingLot();
        CarInfo car1=new CarInfo("KL 7221","SuzukiSwift","white",2.0);
        CarInfo car2=new CarInfo("MH 01 CD 3367","Suv","red",2.15);
        parkingLot.getData(car1);
        parkingLot.getData(car2);
        int token=2;
        boolean b = parkingLot.unPark(token);
        Assert.assertEquals(true,b);
    }

    @Test
    public void whenParkTheCar_ItShouldCheckItsParkingCarShouldNotBeMoreThan100() throws ParkingLotException {
        Integer status=null;
        try {
            ParkingLot parkingLot = new ParkingLot();
            for (int i = 1; i <= 100; i++) {
                CarInfo car1 = new CarInfo("KL 7221", "SuzukiSwift", "white", 2.0);
                int integerBooleanHashMap1 = parkingLot.getData(car1); }

            CarInfo car2 = new CarInfo("MH 01 CD 3367", "Suv", "red", 2.15);
            parkingLot.getData(car2);
        }
        catch (ParkingLotException e)
        {
            System.out.println("Slot1 Is Full");
            Assert.assertEquals(e.type,ParkingLotException.ExceptionType.Lot_Not_Available); }
    }






}

