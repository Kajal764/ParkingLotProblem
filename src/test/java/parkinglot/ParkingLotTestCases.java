package parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeMap;

public class ParkingLotTestCases {

    @Test
    public void whenDriverWantToParkHisCar_ShouldAbleToPark() {
        ParkingLot parkingLot= new ParkingLot();
        boolean park = parkingLot.park();
        Assert.assertEquals(true,park);
    }

    @Test
    public void whenDriverParkHisCar_SpaceTokenShouldReturnTrue() {
        ParkingLot parkingLot=new ParkingLot();
        CarInfo car1=new CarInfo("KL 7221","SuzukiSwift","white",2.0);
        CarInfo car2=new CarInfo("MH 01 CD 3367","Suv","red",2.15);
        CarInfo car3=new CarInfo("TN 7221","Mpv","white",2.15);
        CarInfo car4=new CarInfo("RMD 387","Mrecedes","blue",2.30);
        CarInfo car5=new CarInfo("AD 221","AudiR8","black",2.45);

        TreeMap<Integer, Boolean> integerBooleanHashMap1 = parkingLot.addedInfo(car1);
        TreeMap<Integer, Boolean> integerBooleanHashMap2 = parkingLot.addedInfo(car2);
        TreeMap<Integer, Boolean> integerBooleanHashMap3 = parkingLot.addedInfo(car3);
        TreeMap<Integer, Boolean> integerBooleanHashMap4 = parkingLot.addedInfo(car4);
        TreeMap<Integer, Boolean> integerBooleanHashMap5 = parkingLot.addedInfo(car5);

        Assert.assertEquals(integerBooleanHashMap1.get(1),true);
        Assert.assertEquals(integerBooleanHashMap2.get(2),true);
        Assert.assertEquals(integerBooleanHashMap3.get(3),true);
        Assert.assertEquals(integerBooleanHashMap4.get(4),true);
        Assert.assertEquals(integerBooleanHashMap5.get(5),true);
    }


    @Test
    public void whenParkCar_ItShouldFirstCheckSpaceIsEmpty_ThenSpaceTokenShouldReturnTrue() {
        ParkingLot parkingLot= new ParkingLot();
        CarInfo car1=new CarInfo("KL 7221","SuzukiSwift","white",2.0);
        TreeMap<Integer, Boolean> integerBooleanHashMap = parkingLot.addedInfo(car1);
        Assert.assertEquals(integerBooleanHashMap.get(1),true);
    }

    @Test
    public void whenUnparkThecar_ItShouldAssignSpaceEmptyForThatToken() {
        ParkingLot parkingLot=new ParkingLot();
        CarInfo car1=new CarInfo("KL 7221","SuzukiSwift","white",2.0);
        CarInfo car2=new CarInfo("MH 01 CD 3367","Suv","red",2.15);
        TreeMap<Integer, Boolean> integerBooleanHashMap1 = parkingLot.addedInfo(car1);
        TreeMap<Integer, Boolean> integerBooleanHashMap2 = parkingLot.addedInfo(car2);
        int token=2;
        parkingLot.unPark(token);
        System.out.println(integerBooleanHashMap2);
    }
}

