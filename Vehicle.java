/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Vehicle implements Comparable<Vehicle> {

    private String brand;
    private String IDPlate;
    private DateTime dateTime;
    private String colour;
    private long duration;

    public Vehicle(String brand, String IDPlate, DateTime dateTime, String colour) {
        this.brand = brand;
        this.IDPlate = IDPlate;
        this.dateTime = dateTime;
        this.colour = colour;

    }

    public String getBrand() {
        return brand;

    }

    public void setIDPlate(String IDPlate) {
        this.IDPlate = IDPlate;

    }

    public String getIDPlate() {
        return IDPlate;
    }

    public DateTime getDateTime() {
        return dateTime;
    }

    public String getColor() {
        return colour;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String toString() {
        return "ID plate " + IDPlate + " Type " + getVehicle() + " " + "entry time " + dateTime.getEntryTime();
    }

    public int compareTo(Vehicle vehicle2) {

        try {
            SimpleDateFormat df = new SimpleDateFormat("HH:mm");

            Date entryTimeVehicle1 = df.parse(getDateTime().getEntryTime());
            Date entryTimeVehicle2 = df.parse(vehicle2.getDateTime().getEntryTime());

            return entryTimeVehicle2.compareTo(entryTimeVehicle1);
        } catch (ParseException ex) {
            System.out.println("Wrong time format");
        }
        return 0;
    }

    public abstract String getVehicle();
}
