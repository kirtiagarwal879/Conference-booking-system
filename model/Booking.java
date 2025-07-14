package model;
/*
In this class we are storing the booking made by an emplotee
duration 
slot and which room
 */

public class Booking {
    public String id;
    public Room room;
    public Employee employee;
    public int startSlot;
    public int duration;

    public Booking(String id, Room room, Employee employee, int startSlot, int duration) {
        this.id = id;
        this.room = room;
        this.employee = employee;
        this.startSlot = startSlot;
        this.duration = duration;
    }
}
