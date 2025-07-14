package model;

import java.util.*;

public class Room {
    public String name;
    public RoomType type;
    public int capacity;
    public Set<Integer> availableSlots;
    public Map<Integer, Booking> bookings;
/*
 * room class is to room name/number ,to store small and large and capacity of that room 
 * and slots 
 */
    public Room(String name, RoomType type, int capacity, List<Integer> slots) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.availableSlots = new HashSet<>(slots);
        this.bookings = new HashMap<>();
    }
    /*
     * Here we are checking is this room is available for this time slot or not 
     * 
     */

    public boolean isAvailable(int startSlot, int duration) {
        for (int i = startSlot; i < startSlot + duration; i++) {
            if (!availableSlots.contains(i) || bookings.containsKey(i)) return false;
        }
        return true;
    }
    /*
     * 
     *  books the room by marking the given time slots as taken and assigning the booking to them.
     */

    public void bookSlots(int startSlot, int duration, Booking booking) {
        for (int i = startSlot; i < startSlot + duration; i++) {
            bookings.put(i, booking);
        }
    }
/*
Here we are cancelling the room for the particular time slots and booking the slot 
 * 
 */
    public void cancelBooking(String bookingId) {
        bookings.entrySet().removeIf(entry -> bookingId.equals(entry.getValue().id));
    }

    /*
     * here we are printing the schedule 
     */
    public void printSchedule() {
        for (int i = 1; i <= 10; i++) {
            if (bookings.containsKey(i)) {
                System.out.println("Slot " + i + ": Booked by " + bookings.get(i).employee.name);
            } else {
                System.out.println("Slot " + i + ": Available");
            }
        }
    }
}
