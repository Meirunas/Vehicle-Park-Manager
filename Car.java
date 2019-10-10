public class Car extends Vehicle {

    private int numberOfDoors;

    public Car(String brand, String IDPlate, DateTime dateTime, int numberOfDoors, String colour) {
        super(brand, IDPlate, dateTime, colour);
        this.numberOfDoors = numberOfDoors;
    }

    public int getnumberOfDoors() {
        return numberOfDoors;
    }

    public String getVehicle() {
        return "Car";
    }

}
