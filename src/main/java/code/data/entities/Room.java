package code.data.entities;

import java.util.ArrayList;
import java.util.List;

import code.data.enums.RoomState;
import code.data.enums.RoomType;

public class Room {
    private int id;
    private static int counter;
    private String number;
    private int floorNumber;
    private RoomType roomType;
    private RoomState roomState;
    private Pavilion pavilion;
    List<ScholarshipHousedStudent> listScholarshipHousedStudents = new ArrayList<>();

    public List<ScholarshipHousedStudent> getListScholarshipHousedStudents() {
        return listScholarshipHousedStudents;
    }

    public void setListScholarshipHousedStudents(ScholarshipHousedStudent scholarshipHousedStudent) {
        listScholarshipHousedStudents.add(scholarshipHousedStudent);
    }

    public Room() {
        id = ++counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Pavilion getPavilion() {
        return pavilion;
    }

    public void setPavilion(Pavilion pavilion) {
        this.pavilion = pavilion;
    }

    public RoomState getRoomState() {
        return roomState;
    }

    public void setRoomState(RoomState roomState) {
        this.roomState = roomState;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int nbr) {
        Room.counter = nbr;
    }

    @Override
    public String toString() {
        return "Room [id = " + id + ", number = " + number + ", floorNumber = " + floorNumber + ", roomType = "
                + roomType
                + ", roomState = " + roomState + ", pavilion = " + pavilion + "]";
    }

}