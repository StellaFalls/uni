package code.data.repositories;

import java.util.ArrayList;
import java.util.List;

import code.core.Repository;

import code.data.entities.Room;

public class RoomRepository implements Repository<Room> {

    List<Room> listRooms = new ArrayList<>();

    @Override
    public boolean insert(Room objet) {
        return listRooms.add(objet);
    }

    @Override
    public Room getById(int id) {
        for (Room room : listRooms) {
            if (room.getId() == id) {
                return room;
            }

        }
        return null;
    }

    @Override
    public List<Room> selectAll() {
        return listRooms;
    }

    @Override
    public void update(Room room) {

        if (room != null) {
            System.out.println("[Edit executed!]");
        } else {
            System.out.println("[Edit non executed!]");
        }

    }

    @Override
    public void update1(Room room) {
        if (room != null) {
            System.out.println("[Edit executed!]");
        } else {
            System.out.println("[Edit non executed!]");
        }
    }

    @Override
    public void affect(Room room) {
        if (room != null) {
            System.out.println("[Edit executed!]");
        } else {
            System.out.println("[Edit non executed!]");
        }
    }

    @Override
    public List<Room> selectBy(int x) {
        List<Room> list = new ArrayList<>();
        for (Room room : listRooms) {
            if (room.getPavilion().getId() == x) {
                list.add(room);

            }

        }
        return list;

    }

}