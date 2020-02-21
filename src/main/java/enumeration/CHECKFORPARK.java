package enumeration;

import parkinglot.ParkingLotService;
import parkinglot.VehicleInfo;

public enum CHECKFORPARK {

    Handicap {
        @Override
        public void parkCar(VehicleInfo vehicle, int slotNo, ParkingLotService service) {
            service.parkHandicapDriverCar(vehicle,slotNo);

        }
    },
    Large_car {
        @Override
        public void parkCar(VehicleInfo vehicle, int slotNo,ParkingLotService service) {
            service.parkLargeCar(vehicle,slotNo);
        }
    },
    Normal {
        @Override
        public void parkCar(VehicleInfo vehicle, int slotNo,ParkingLotService service) {
           service.parkIfNull(vehicle,slotNo);
        }
    };

    public abstract void parkCar(VehicleInfo info, int slotNo,ParkingLotService service);
}
