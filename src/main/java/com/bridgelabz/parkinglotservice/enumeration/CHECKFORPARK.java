package com.bridgelabz.parkinglotservice.enumeration;

import com.bridgelabz.parkinglotservice.ParkingLotService;
import com.bridgelabz.parkinglotservice.service.VehicleInfo;

public enum CHECKFORPARK {

    HANDICAP {
        @Override
        public void parkCar(VehicleInfo vehicle, int slotNo, ParkingLotService service) {
            service.parkHandicapDriverCar(vehicle,slotNo);

        }
    },
    LARGE_CAR {
        @Override
        public void parkCar(VehicleInfo vehicle, int slotNo,ParkingLotService service) {
            service.parkLargeCar(vehicle,slotNo);
        }
    },
    NORMAL {
        @Override
        public void parkCar(VehicleInfo vehicle, int slotNo,ParkingLotService service) {
           service.parkIfNull(vehicle,slotNo);
        }
    };

public abstract void parkCar(VehicleInfo info, int slotNo, ParkingLotService service);
}
