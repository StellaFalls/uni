package code.data.entities;

import java.util.ArrayList;
import java.util.List;

public class Pavilion {
    private int id;
    private static int counter;
    private String number;
    private int floorAmount;
    List<Room> listRooms = new ArrayList<>();

    public List<Room> getListRooms() {
        return listRooms;
    }

    public void setListRooms(Room room) {
        listRooms.add(room);
    }

    public Pavilion() {
        id = ++counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getNbr() {
        return counter;
    }

    public static void setNbr(int nbr) {
        Pavilion.counter = nbr;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getFloorAmount() {
        return floorAmount;
    }

    public void setFloorAmount(int floorAmount) {
        this.floorAmount = floorAmount;
    }

    @Override
    public String toString() {
        return "Pavilion [id = " + id + ", number = " + number + ", floorAmount = " + floorAmount + "]";
    }

}