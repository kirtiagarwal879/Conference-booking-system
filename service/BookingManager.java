package service;

import model.*;

import java.util.*;
import java.util.logging.Logger;

public class BookingManager {
    private static final Logger logger = Logger.getLogger(BookingManager.class.getName());
    private Map<String, Room> rooms = new HashMap<>();
    private Map<String, Employee> employees = new HashMap<>();
    private Map<String, Booking> bookings = new HashMap<>();
    private int bookingCounter = 100;

    /*
     * here we adds a new conference room to the system by taking its name, size (Small or Large), and time slots it’s available for, so that employees can book it later.
     */
public void registerRoom(String name, String type, List<Integer> slots) {
        RoomType roomType = type.equalsIgnoreCase("Small") ? RoomType.SMALL : RoomType.LARGE;
        int capacity = roomType == RoomType.SMALL ? 10 : 30;
        rooms.put(name, new Room(name, roomType, capacity, slots));
        System.out.println("Registered Room: "+name+" "+"("+type +")");

    }
    /*
     * Here we are adding a new employee to the system by saving their name and department, so they can book rooms later.
     */

    public void registerEmployee(String name, String department) {
        employees.put(name, new Employee(name, department));
        System.out.println("Registered Employee: " + name + " [" + department + "]");
    }
    /*
     * Here we are  booking a meeting room for an employee by checking who they are
     * how many people are attending,
     *  and which time slots they need — 
     * then automatically selects the smallest available
     *  room that fits and confirms the booking.
     */

    public void bookRoom(String employeeName, int attendees, int duration, int startSlot) {
        Employee employee = employees.get(employeeName);
        if (employee == null) {
            logger.warning("Booking failed: Employee " + employeeName + " not registered.");
            System.out.println("Employee not registered.");
            return;
        }

        List<Room> suitableRooms = new ArrayList<>();
        for (Room room : rooms.values()) {
            if (room.capacity >= attendees && room.isAvailable(startSlot, duration)) {
                suitableRooms.add(room);
            }
        }

        if (suitableRooms.isEmpty()) {
            System.out.println("No suitable room available for given criteria.");
            return;
        }

        suitableRooms.sort(Comparator.comparingInt(r -> r.capacity));
        Room selectedRoom = suitableRooms.get(0);

        String bookingId = "BKG" + bookingCounter++;
        Booking booking = new Booking(bookingId, selectedRoom, employee, startSlot, duration);
        selectedRoom.bookSlots(startSlot, duration, booking);
        employee.bookings.add(booking);// and in alice bookings
        bookings.put(bookingId, booking);
        System.out.println("Booking successful for " + selectedRoom.name + ", Slot " + startSlot + "-" + (startSlot + duration - 1) + ". Booking ID: " + bookingId);
    }
    /*
     * Here  cancels a booking using its ID — if the booking exists, it removes it from the room, the employee’s list, and the system, making the time slot available again.
     */

    public void cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking == null) {
         logger.info("Booking cancelled: " + bookingId + ". Room: " + booking.room.name +
                ", Slot: " + booking.startSlot + "-" + (booking.startSlot + booking.duration - 1) +
                ", Employee: " + booking.employee.name);
            System.out.println("Invalid Booking ID.");
            return;
        }

        booking.room.cancelBooking(bookingId);
        booking.employee.bookings.remove(booking);
        bookings.remove(bookingId);

        System.out.println("Booking " + bookingId + " cancelled. " + booking.room.name + ", Slot " + booking.startSlot + "-" + (booking.startSlot + booking.duration - 1) + " now available.");
    }
/* This is for to view the schedule */

    public void viewSchedule(String name) {
        if (rooms.containsKey(name)) {
            rooms.get(name).printSchedule();
        } else if (employees.containsKey(name)) {
            employees.get(name).printBookings();
        } else {
            System.out.println("No room or employee found with name: " + name);
        }
    }
}
