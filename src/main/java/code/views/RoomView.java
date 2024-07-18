package code.views;

import java.util.List;
import java.util.Scanner;

import code.core.Service;
import code.core.ViewImpl;

import code.data.entities.Pavilion;
import code.data.entities.Room;
import code.data.entities.ScholarshipHousedStudent;
import code.data.enums.RoomType;
import code.data.enums.RoomState;

public class RoomView extends ViewImpl<Room> {
    Service<Pavilion> pavilionService;
    Service<Room> roomService;

    public RoomView(Scanner scanner, Service<Pavilion> pavilionService, Service<Room> roomService) {
        this.scanner = scanner;
        this.pavilionService = pavilionService;
        this.roomService = roomService;
    }

    @Override
    public Room instance() {
        if (!(pavilionService.show().isEmpty())) {
            Room room = new Room();
            scanner.nextLine();
            room.setRoomType(askRoomType());
            int id;
            do {
                System.out.println("Input the pavilion's id:");
                id = scanner.nextInt();
            } while (pavilionService.find(id) == null);
            Pavilion pavilion = pavilionService.find(id);
            int floorNumber;
            do {
                System.out.println("Input the floor's number:");
                floorNumber = scanner.nextInt();
            } while (floorNumber > pavilion.getFloorAmount() || floorNumber < 0);
            room.setFloorNumber(floorNumber);
            room.setPavilion(pavilion);
            pavilion.setListRooms(room);
            room.setRoomState(askState());
            room.setNumber(generateNumber(room.getId(), "ROOM"));
            return room;
        } else {
            System.out.println("[Cannot create a room without existing pavilions!]]");
            return null;
        }
    }

    public RoomType askRoomType() {
        int choice;
        do {
            System.out.println("Which type should the room be?\n1- Solo\n2- Duos");
            choice = scanner.nextInt();
        } while (choice != 1 && choice != 2);
        if (choice == 1) {
            return RoomType.solo;
        } else {
            return RoomType.duos;
        }
    }

    public void changeState(Room room) {
        if (room.getRoomState() == RoomState.archived) {
            room.setRoomState(RoomState.nonArchived);
        } else {
            room.setRoomState(RoomState.archived);
        }
    }

    public void changeType(Room room) {
        if (room.getRoomType() == RoomType.solo) {
            room.setRoomType(RoomType.duos);
        } else {
            room.setRoomType(RoomType.solo);
        }
    }

    public void emptyRoom(Room room) {
        List<ScholarshipHousedStudent> listScholarshipHousedStudents = room.getListScholarshipHousedStudents();
        for (ScholarshipHousedStudent etu : listScholarshipHousedStudents) {
            etu.setRoom(null);
        }
    }

    public Room askRoom() {
        System.out.println("Input the room's Id:");
        int id = scanner.nextInt();
        if (roomService.find(id) != null) {
            return roomService.find(id);

        }
        return null;
    }

    public Room findRoom(int id) {
        for (Pavilion pavilion : pavilionService.show()) {
            for (Room room : pavilion.getListRooms()) {
                if (room.getId() == id) {
                    return room;
                }
            }
        }
        return null;
    }

    public RoomState askState() {
        int answer;
        do {
            System.out.println("What's the room state?\n1- Archived\n2- Non archived");
            answer = scanner.nextInt();
        } while (answer != 1 && answer != 2);
        if (answer == 1) {
            return RoomState.archived;
        } else {
            return RoomState.nonArchived;
        }
    }

    public Room changeRoomState() {
        Room room = roomService.find(askId());
        if (room != null) {
            System.out.println("Your room state is set at " + room.getRoomState());
            System.out.println("Do you wish to change it?\n1- Yes\n2- No");
            int resp = scanner.nextInt();
            if (resp == 1) {
                changeState(room);
                if (room.getRoomState() == RoomState.archived) {
                    emptyRoom(room);
                }
                return room;
            } else {
                System.out.println("[State change failed!]");
                return null;
            }

        } else {
            System.out.println("[Room not found!}");
            return null;
        }
    }

    public Room editRoom() {
        int choice = askEdit();
        Room room = roomService.find(askId());
        if (choice == 1) {
            int floorNumber;
            if (room != null) {
                do {
                    System.out.println("Input the new floor number:");
                    floorNumber = scanner.nextInt();
                } while (floorNumber > room.getPavilion().getFloorAmount());
                room.setFloorNumber(floorNumber);
                System.out.println(room);
                return room;
            } else {
                System.out.println("[Room does not exist!]");
                return null;
            }
        } else {
            System.out.println("The room type is set at " + room.getRoomType());
            System.out.println("Do you wish to change it?\n1- Yes\n2- No");
            int resp = scanner.nextInt();
            if (resp == 1) {
                changeType(room);
                if (room.getRoomType() == RoomType.solo) {
                    List<ScholarshipHousedStudent> list = room.getListScholarshipHousedStudents();
                    for (ScholarshipHousedStudent scholarshipHousedStudent : list) {
                        scholarshipHousedStudent.setRoom(null);
                        return room;
                    }
                    return room;
                }
                return room;
            } else {
                return null;
            }

        }
    }

    public int askId() {

        int choice;
        System.out.println("Input the id:");
        choice = scanner.nextInt();
        return choice;

    }

    public int askEdit() {
        int choice;
        do {
            System.out.println("Do you want to edit\n1- The room's state\n2- The room's type");
            choice = scanner.nextInt();
        } while (choice != 1 && choice != 2);
        if (choice == 1) {
            return 1;
        } else {
            return 2;
        }
    }

}