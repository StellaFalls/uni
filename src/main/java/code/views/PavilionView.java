package code.views;

import java.util.List;
import java.util.Scanner;

import code.core.Repository;
import code.core.ViewImpl;

import code.data.entities.Pavilion;
import code.data.entities.Room;

public class PavilionView extends ViewImpl<Pavilion> {
    private final RoomView roomView;
    Repository<Room> roomRepository;
    Repository<Pavilion> pavilionRepository;

    public PavilionView(Scanner scanner, RoomView roomView, Repository<Room> roomRepository, Repository<Pavilion> pavilionRepository) {
        this.scanner = scanner;
        this.roomView = roomView;
        this.roomRepository = roomRepository;
        this.pavilionRepository = pavilionRepository;
    }

    public Pavilion askPavilion() {
        System.out.println("Please input the pavilion's id:");
        int id = scanner.nextInt();
        if ((pavilionRepository.getById(id)) != null) {
            Pavilion pavilion = pavilionRepository.getById(id);
            System.out.println(pavilion);
            return pavilion;
        }
        return null;
    }

    @Override
    public Pavilion instance() {
        Pavilion pavilion = new Pavilion();
        int floorAmount;
        scanner.nextLine();
        do {
            System.out.println("Input the amount of floors:");
            floorAmount = scanner.nextInt();
        } while (floorAmount <= 0);
        pavilion.setFloorAmount(floorAmount);
        int answer;
        do {
            System.out.println("Do you wanna create a room?\n1- Yes\n2- No");
            answer = scanner.nextInt();
            scanner.nextLine();
            if (answer == 1) {
                Room room = new Room();
                int floorNumber;
                do {
                    System.out.println("Input the floor's number:");
                    floorNumber = scanner.nextInt();
                } while (floorNumber > pavilion.getFloorAmount() || floorNumber < 0);
                room.setFloorNumber(floorNumber);
                room.setNumber(generateNumber(room.getId(), "ROOM"));
                room.setRoomType(roomView.askRoomType());
                room.setRoomState(roomView.askState());
                room.setPavilion(pavilion);
                roomRepository.insert(room);
                pavilion.setListRooms(room);
            }
        } while (answer == 1);
        pavilion.setNumber(generateNumber(pavilion.getId(), "PAVILION"));
        return pavilion;
    }

    public Pavilion editPavilion() {
        Pavilion pavilion = askPavilion();
        if (pavilion != null) {
            if (askEdit() == 1) {
                int floorAmount;
                do {
                    System.out.println("Enter the new amount of floors:");
                    floorAmount = scanner.nextInt();
                } while (floorAmount < pavilion.getFloorAmount());
                pavilion.setFloorAmount(floorAmount);
                System.out.println("[Amount of floors edited!]");
                return pavilion;
            } else {
                System.out.println("Input the id of the room to delete:");
                int id = scanner.nextInt();
                Room room = roomView.findRoom(id);
                if (room != null) {
                    for (Room roomToFind : pavilion.getListRooms()) {
                        if (roomToFind.getId() == room.getId()) {
                            pavilion.getListRooms().remove(id - 1);
                            roomToFind.setPavilion(null);
                            System.out.println("[Room deleted!]");
                            return pavilion;
                        }
                    }
                } else {
                    System.out.println("[Room does not exist!]");
                    return null;
                }
            }
        } else {
            System.out.println("[Pavilion does not exist!]");
            return null;
        }
        return null;
    }

    private int askEdit() {
        int answer;
        do {
            System.out
                    .println("Editing the pavilion... Do you want to:\n1- Edit the amount of floors\n2- Delete a room");
            answer = scanner.nextInt();
        } while (answer != 1 && answer != 2);
        return answer;
    }

    public Room affectRoom() {
        Room room = roomView.askRoom();
        if (room != null) {
            Pavilion pavilion = askPavilion();
            if (pavilion != null) {
                if (room.getPavilion() != null) {
                    Pavilion pav = room.getPavilion();
                    List<Room> listRooms = pav.getListRooms();
                    for (Room roomToFind : listRooms) {
                        if (roomToFind.getId() == room.getId()) {
                            int floorNumber;
                            do {
                                System.out.println("Input the new room's floor number:");
                                floorNumber = scanner.nextInt();
                            } while (floorNumber < pavilion.getFloorAmount());
                            pav.getListRooms().remove(roomToFind);
                            room.setPavilion(pavilion);
                            pavilion.setListRooms(room);
                            room.setFloorNumber(floorNumber);
                            return room;
                        }
                    }
                } else {
                    room.setPavilion(pavilion);
                    pavilion.setListRooms(room);
                    return room;
                }
            } else {
                System.out.println("[Pavilion does not exist!]");
                return null;
            }
        } else {
            System.out.println("[Room does not exist!]");
            return null;
        }
        return null;
    }

}