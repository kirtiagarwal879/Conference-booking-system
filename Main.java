/*
 * I am designing my conference-room-booking system in this way 
 * The structuring of the code is like that 
 * conference-room-booking system
 *  |                \           \
 * Main class       service      model
 * (demo class)  BookingManager   Booking
 *                                Employee
 *                                 Room
                                RoomType 
 */

import service.BookingManager;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        BookingManager manager = new BookingManager();

        manager.registerRoom("Room A", "Small", Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        manager.registerRoom("Room B", "Large", Arrays.asList(1,2,3,4,5,6,7,8,9,10));

        manager.registerEmployee("Alice", "Marketing");
        manager.registerEmployee("Bob", "Sales");
/*  
 * Here alice and bob are booking the rooms for the particular attendees as per there 
 * requiremet 
 */
        manager.bookRoom("Alice", 7, 2, 2);// 10-12
        manager.bookRoom("Alice", 8, 2, 3);// 11-1
//        manager.bookRoom("Bob", 7, 2, 2);// Room A, Slot 2-3
//        manager.bookRoom("Bob", 15, 1, 1);      // Room B, Slot 1
//        manager.registerEmployee("Charlie", "Engineering");// new employee registered with there department
//        manager.bookRoom("Charlie", 8, 3, 4);   // Room A, Slot 4-6
//        manager.bookRoom("Alice", 7, 2, 6);     // Should fail
//        manager.cancelBooking("BKG100");       // Cancel Alice's original booking
//        manager.viewSchedule("Room A");
        manager.viewSchedule("Alice");

    
    }

}
// N
// 10 -

