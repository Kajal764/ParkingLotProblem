package parkinglot;

public class CarInfo {

    public String numberPlate;
    public String model;
    public String colour;
    public Double time;

    public CarInfo(String numberPlate, String model, String colour, Double time) {
        this.numberPlate = numberPlate;
        this.model = model;
        this.colour = colour;
        this.time = time;
    }

    @Override
    public String toString() {
        return "CarInfo{" +
                "numberPlate='" + numberPlate + '\'' +
                ", model='" + model + '\'' +
                ", colour='" + colour + '\'' +
                ", time=" + time +
                '}';
    }
}
