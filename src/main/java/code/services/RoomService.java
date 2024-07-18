package code.services;

import java.util.List;

import code.core.Repository;
import code.core.Service;

import code.data.entities.Room;

public class RoomService implements Service<Room> {
    Repository<Room> roomRepository;

    public RoomService(Repository<Room> roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public void save(Room objet) {
        if (objet != null) {
            roomRepository.insert(objet);
        }
    }

    @Override
    public Room find(int id) {
        return roomRepository.getById(id);
    }

    @Override
    public List<Room> show() {
        return roomRepository.selectAll();
    }

    @Override
    public void update(Room objet) {
        roomRepository.update(objet);
    }

    @Override
    public void update1(Room objet) {
        roomRepository.update1(objet);
    }

    @Override
    public void affect(Room room) {
        roomRepository.affect(room);
    }

    @Override
    public List<Room> selectBy(int x) {
        return roomRepository.selectBy(x);
    }

}