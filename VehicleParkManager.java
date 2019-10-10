/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface VehicleParkManager {

    public abstract int addVehicle(Vehicle vehicle);
    public abstract void printVehicleList();
    public abstract boolean runMenu();
}
