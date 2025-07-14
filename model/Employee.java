package model;

import java.util.ArrayList;
import java.util.List;

public class Employee {
    public String name;
    public String department;
    public List<Booking> bookings;

    public Employee(String name, String department) {
        this.name = name;
        this.department = department;
        this.bookings = new ArrayList<>();
    }

    public void printBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }
        for (Booking b : bookings) {
            System.out.println("Booking " + b.id + " (" + b.room.name + ", Slot " + b.startSlot + "-" + (b.startSlot + b.duration - 1) + ")");
        }
    }
}
/*
 * This is the bluprint for the employee .
 *  where we are storing name , department and list of bookings
 *  printBookings() we are displaying the employees booking 
 * 
 */
