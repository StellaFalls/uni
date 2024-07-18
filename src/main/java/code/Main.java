package code;

import java.util.Scanner;

import code.core.Repository;
import code.core.Service;

import code.data.repositories.StudentRepositoryBD;
import code.data.repositories.RoomRepositoryBD;
import code.data.repositories.PavilionRepositoryBD;
import code.data.repositories.StudentRepository;
import code.data.repositories.RoomRepository;
import code.data.repositories.PavilionRepository;

import code.services.StudentService;
import code.services.RoomService;
import code.services.PavilionService;

import code.views.StudentView;
import code.views.RoomView;
import code.views.PavilionView;

import code.data.entities.Student;
import code.data.entities.Room;
import code.data.entities.Pavilion;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Repository<Student> studentRepository = new StudentRepositoryBD();
        Repository<Pavilion> pavilionRepository = new PavilionRepositoryBD();
        Repository<Room> roomRepository = new RoomRepositoryBD(pavilionRepository);

        Service<Student> studentService = new StudentService(studentRepository);
        Service<Room> roomService = new RoomService(roomRepository);
        Service<Pavilion> pavilionService = new PavilionService(pavilionRepository);

        RoomView roomView = new RoomView(scanner, pavilionService, roomService);
        PavilionView pavilionView = new PavilionView(scanner, roomView, roomRepository, pavilionRepository);
        StudentView studentView = new StudentView(scanner, pavilionRepository, studentRepository, roomRepository);

        int choice, choicePavilion, choiceRoom, choiceStudent;

        do {
            switch (choice = menu()) {
                case 1 -> {
                    do {
                        studentView.show(studentService.show());
                        switch (choiceStudent = studentsMenu()) {
                            case 1 -> studentService.save(studentView.newInstance());
                            case 2 -> studentService.affect(studentView.affect());
                            case 3 -> studentView.show(studentService.selectBy(studentView.askId()));
                        }
                    } while (choiceStudent != 4);
                }
                case 2 -> {
                    do {
                        roomView.show(roomService.show());
                        switch (choiceRoom = roomsMenu()) {
                            case 1 -> roomService.save(roomView.instance());
                            case 2 -> roomRepository.update1(roomView.editRoom());
                            case 3 -> roomRepository.update(roomView.changeRoomState());
                            case 4 -> roomView.show(roomRepository.selectBy(roomView.askId()));
                            case 5 -> roomRepository.affect(pavilionView.affectRoom());
                        }
                    } while (choiceRoom != 6);
                }
                case 3 -> {
                    do {
                        pavilionView.show(pavilionService.show());
                        switch (choicePavilion = pavilionsMenu()) {
                            case 1 -> pavilionService.save(pavilionView.instance());
                            case 2 -> pavilionService.update1(pavilionView.editPavilion());
                        }
                    } while (choicePavilion != 3);
                }
            }
        } while (choice != 4);
    }

    public static int menu() {
        System.out.println("\n[Welcome! Do you want to...]\n");
        System.out.println("1- See all students");
        System.out.println("2- See all rooms");
        System.out.println("3- See all pavilions");
        System.out.println("4- Leave:");
        return scanner.nextInt();
    }

    public static int studentsMenu() {
        System.out.println("\n-> Students! Do you want to...\n");
        System.out.println("1- Create a student");
        System.out.println("2- Affect a room to a housed student");
        System.out.println("3- See a room's students ");
        System.out.println("4- Leave:");
        return scanner.nextInt();
    }

    public static int roomsMenu() {
        System.out.println("\n-> Rooms! Do you want to...\n");
        System.out.println("1- Create a room");
        System.out.println("2- Edit a room");
        System.out.println("3- Change the state of a room");
        System.out.println("4- See a pavilion's rooms");
        System.out.println("5- Affect a room to a pavilion");
        System.out.println("6- Leave:");
        return scanner.nextInt();
    }

    public static int pavilionsMenu() {
        System.out.println("\n-> Pavilions! Do you want to...\n");
        System.out.println("1- Create a pavilion");
        System.out.println("2- Edit a pavilion");
        System.out.println("3- Leave:");
        return scanner.nextInt();
    }

}