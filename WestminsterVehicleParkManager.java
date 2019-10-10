/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WestminsterVehicleParkManager implements VehicleParkManager {
    int numberOfCars = 0;
    int numberOfVans = 0;
    int numberOfMotorbikes = 0;
    int freeLots;
    static boolean exit;
    static List<Vehicle> listParkVehicles;

    Scanner input = new Scanner(System.in);
    //static Vehicle[] listParkVehicles = new Vehicle[freeLots];

    //static Car myCar;
    public WestminsterVehicleParkManager(int listLength) {
        this.freeLots = listLength;
        listParkVehicles = new ArrayList<Vehicle>();
        addInitialVehicles();
    }

    public static void main(String[] args) {

        // create a parking
        int initialFreeSlots = 20;
        VehicleParkManager sys = new WestminsterVehicleParkManager(initialFreeSlots);

        while (!exit)
            exit = sys.runMenu();
        System.out.println("Program closed");
    }

    public void addInitialVehicles() {
         //String brand,String IDPlate, DateTime dateTime,int numberOfDoors,String colour
        DateTime dateTime1 = new DateTime("18/12/1990", "13:00", "18/12/1990", "18:00");
        Car car1 = new Car("Honda", "wg05mve", dateTime1, 4, "green");

        DateTime dateTime2 = new DateTime("17/12/1990", "15:00", "17/12/1990", "18:00");
        Car car2 = new Car("Honda", "w252563", dateTime2, 4, "green");

        addVehicle(car1);
        addVehicle(car2);
    }
    
    public boolean runMenu() {
        System.out.println("\nTo Add a new vehicle press 1\n"
                            + "To print the list of the vehicles press 2\n"
                            + "To delete vehicle press 3\n"
                            + "To statistics press 4\n"
                            + "To print in specific day press 5\n"
                            + "To print price of vehicles 6\n"
                            + "To exit press 7");
        Scanner s = new Scanner(System.in);
        int choise = s.nextInt();
        switch (choise) {
            case 1:

                System.out.println("Enter the brand of your vehicle");
                String brand = s.next();
                System.out.println("Enter the color of your vehicle");
                String colour = s.next();
                System.out.println("Enter ID plate");
                String IDPlate = s.next();
                System.out.println("Enter entry date:");
                String entryDate = s.next();
                System.out.println("Enter entry time:");
                String entryTime = s.next();
                System.out.println("Enter departure date:");
                String departureDate = s.next();
                System.out.println("Enter departure time:");
                String departureTime = s.next();
                DateTime datetime = new DateTime(entryDate, entryTime, departureDate, departureTime);
                System.out.println("Press 1 if you want to add a Car");
                System.out.println("Press 2 if you want to add a Motorbike");
                System.out.println("Press 3 if you want to add a Van");
                System.out.println("");

                int choise2 = s.nextInt();
                s.nextLine();

                switch (choise2) {
                    case 1:
                        System.out.println("Enter the number of your vehicle doors");
                        int numberOfDoors = s.nextInt();
                        //String brand,String IDPlate, DateTime dateTime,int numberOfDoors, String color

                        Car c = new Car(brand, IDPlate, datetime, numberOfDoors, colour);
                        addVehicle(c);

                        break;

                    case 2:
                        System.out.println("Enter engine size");
                        int engineSize = s.nextInt();
                        Motorbike m = new Motorbike(brand, IDPlate, datetime, engineSize, colour);
                        addVehicle(m);

                        break;
                    case 3:
                        System.out.println("Enter cargo volume");
                        int cargoVolume = s.nextInt();
                        Van v = new Van(brand, IDPlate, datetime, cargoVolume, colour);
                        addVehicle(v);

                }
                break;
            case 2:
                printVehicleList();
                break;
            case 3:
                System.out.println("Enter ID plate to delete");
                IDPlate = s.next();
                deleteVehicle(IDPlate);
                break;
            case 4:
                printStatistic();
                break;
            case 5:
                System.out.println("Enter entry date:");
                entryDate = s.next();
                printVehiclesInSpecificDay(entryDate);
                break;
            case 6:
                printParkCharge();
                break;
            case 7:
                exit = true;
                break;
        }
        return exit;
    }

    public int addVehicle(Vehicle vehicle) {

        if (listParkVehicles.size() < freeLots) {
            vehicle.setDuration(vehicle.getDateTime().getTimeDifference());
            listParkVehicles.add(vehicle);

            if (vehicle.getVehicle().equals("Van")) {
                freeLots = freeLots - 1;

            }
            freeLots = freeLots - 1;
        } else {
            System.out.println("No more space available for parking");
        }

        return freeLots;

    }

    public Vehicle deleteVehicle(String IDPlate) {
        Vehicle deletedVehicle = null;
        if (listParkVehicles.size() > 0) {
            deletedVehicle = findVehicle(IDPlate);
            listParkVehicles.remove(deletedVehicle);
            System.out.println(deletedVehicle.getVehicle());
        } else {
            System.out.println("No vehicles parked");
        }
        return deletedVehicle;
    }

    public void printVehicleList() {

        if (listParkVehicles.size() == 0) {

            System.out.println("No vehicles parked");

            return;
        }
        Collections.sort(listParkVehicles);
        for (int i = 0; i < listParkVehicles.size(); i++) {

            System.out.println("Vehicle = " + "ID plate " + listParkVehicles.get(i).getIDPlate() + " entry time = "
                    + listParkVehicles.get(i).getDateTime().getEntryDate() + " " + listParkVehicles.get(i).getDateTime().getEntryTime() + " " + listParkVehicles.get(i).getVehicle());

        }
    }

    public Vehicle findVehicle(String IDPlate) {
        for (int i = 0; i < listParkVehicles.size(); i++) {

            if (listParkVehicles.get(i).getIDPlate().equals(IDPlate)) {
                return listParkVehicles.get(i);
            }
        }
        return null;
    }

    public void printStatistic() {
        int carCounter = 0;
        int motorbikeCounter = 0;
        int vanCounter = 0;
        Vehicle longestVehicle = null;
        Vehicle lastVehicle = null;

        for (int i = 0; i < listParkVehicles.size(); i++) {

            if (listParkVehicles.get(i).getVehicle().equals("Car")) {
                carCounter++;

            } else if (listParkVehicles.get(i).getVehicle().equals("Motorbike")) {
                motorbikeCounter++;

            } else if (listParkVehicles.get(i).getVehicle().equals("Van")) {

                vanCounter++;
            } //if

        } //for
        System.out.println("Percentage of cars = " + 100. * carCounter / listParkVehicles.size() + "%");
        System.out.println("Percentage of motorbikes = " + 100. * motorbikeCounter / listParkVehicles.size() + "%");
        System.out.println("Percentage of vans = " + 100. * vanCounter / listParkVehicles.size() + "%");

        //for (int i = 0; i < listParkVehicles.size(); i++) {
        //listParkVehicles.get(i).setDuration(listParkVehicles.get(i).getDateTime().getTimeDifference());
        //}
        long max = Long.MIN_VALUE;
        for (int i = 0; i < listParkVehicles.size(); i++) {
            if (listParkVehicles.get(i).getDuration() > max) {
                max = listParkVehicles.get(i).getDuration();
                longestVehicle = listParkVehicles.get(i);

            }

        }
        System.out.println("Longest parked vehicle - ID plate " + longestVehicle.getIDPlate() + " " + longestVehicle.getVehicle() + " " + longestVehicle.getDateTime().getEntryDate() + " " + longestVehicle.getDateTime().getEntryTime());

        max = Long.MIN_VALUE;
        for (int i = 0; i < listParkVehicles.size(); i++) {
            if (listParkVehicles.get(i).getDateTime().getEntryDateAndTime() > max) {
                max = listParkVehicles.get(i).getDateTime().getEntryDateAndTime();
                lastVehicle = listParkVehicles.get(i);

            }
        }
        System.out.println("Last parked vehicle - ID plate " + lastVehicle.getIDPlate() + " " + lastVehicle.getVehicle() + " " + lastVehicle.getDateTime().getEntryDate() + " " + lastVehicle.getDateTime().getEntryTime());

    }

    public void printVehiclesInSpecificDay(String entryDate) {

        boolean found = false;
        for (int i = 0; i < listParkVehicles.size(); i++) {

            if (listParkVehicles.get(i).getDateTime().getEntryDate().equals(entryDate)) {
                System.out.println(listParkVehicles.get(i));
                found = true;

            }
        }
        if (!found) {
            System.out.println("No vehicle entered in that day");
        }
    }

    public void printParkCharge() {
        int numberOfHours = 0;
        int totalCost = 0;

        for (int i = 0; i < listParkVehicles.size(); i++) {
            //listParkVehicles.get(i).getDuration();
            numberOfHours = (int) (listParkVehicles.get(i).getDuration() / 3600000);

            System.out.println(numberOfHours);

            if (numberOfHours == 1 || numberOfHours <= 3) {
                totalCost = 3 * numberOfHours;
                //System.out.println("ID plate " + listParkVehicles.get(i).getIDPlate() + " and total cost £" + totalCost);
            }
            if (numberOfHours >= 4 && numberOfHours < 9) {
                int totalCost2 = 3 * 3;
                totalCost = 4 * (numberOfHours - 3);
                totalCost = totalCost + totalCost2;
                //System.out.println("ID plate " + listParkVehicles.get(i).getIDPlate() + " and total cost £" + totalCost);
            } else if (numberOfHours >= 9) {
                totalCost = 0 + 30;
                 //System.out.println("ID plate " + listParkVehicles.get(i).getIDPlate() + " and total cost £" + totalCost);
            }
            System.out.println("ID plate " + listParkVehicles.get(i).getIDPlate() + " and total cost £" + totalCost);
        }
    }//method
} //class