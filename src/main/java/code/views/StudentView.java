package code.views;

import code.core.Repository;
import code.core.ViewImpl;

import code.data.entities.*;
import code.data.entities.Room;
import code.data.enums.RoomState;
import code.data.enums.RoomType;
import code.data.enums.ScholarshipType;
import code.data.enums.StudentType;

import java.util.Scanner;

public class StudentView extends ViewImpl<Student> {
    Repository<Pavilion> pavilionRepository;
    Repository<Student> studentRepository;
    Repository<Room> roomRepository;

    public StudentView(Scanner scanner, Repository<Pavilion> pavilionRepository, Repository<Student> studentRepository,
                       Repository<Room> roomRepository) {
        this.scanner = scanner;
        this.pavilionRepository = pavilionRepository;
        this.studentRepository = studentRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public Student instance() {
        return new Student();
    }

    private ScholarshipType askScholarshipType() {
        int rep;
        do {
            System.out.println("Which scholarship type?\n1- full\n2- half");
            rep = scanner.nextInt();
        } while (rep != 1 && rep != 2);
        if (rep == 1) {
            return ScholarshipType.fullScholarship;
        } else {
            return ScholarshipType.halfScholarship;
        }
    }

    private boolean findRoom() {
        for (Pavilion pavilion : pavilionRepository.selectAll()) {
            if (pavilion.getListRooms() != null) {
                return true;
            }
        }
        return false;
    }

    public Student affect() {
        ScholarshipHousedStudent scholarshipHousedStudent = findStudentWithoutRoom();
        if (scholarshipHousedStudent != null && scholarshipHousedStudent.getRoom() == null) {
            if (scholarshipHousedStudent.getRoom() == null) {
                System.out.println("Input the pavilion's id:");
                int id = scanner.nextInt();
                Pavilion pavilion = pavilionRepository.getById(id);
                if (pavilion != null) {
                    System.out.println("Input the room's id:");
                    int id1 = scanner.nextInt();
                    if (roomRepository.getById(id1) != null) {

                        Room room = roomRepository.getById(id1);
                        if (room.getListScholarshipHousedStudents().size() < getValue(room)
                                && room.getRoomState() == RoomState.nonArchived) {

                            scholarshipHousedStudent.setRoom(room);
                            room.setListScholarshipHousedStudents(scholarshipHousedStudent);
                            System.out.println("[Room affectation completed!]");
                            return scholarshipHousedStudent;
                        } else {
                            System.out.println("Room full!");
                            return null;
                        }

                    } else {
                        System.out.println("[Room does not exist!]");
                        return null;
                    }
                } else {
                    System.out.println("[Pavilion does not exist!]");
                    return null;
                }

            } else {
                System.out.println("[Error]");
                return null;
            }

        } else {
            System.out.println("[Error]");
            return null;
        }

    }

    private int checkInstance() {
        System.out.println(
                "Which type of student do you want?\n1- Non scholarship\n2- Scholarship non housed\n3- Scholarship housed");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1 -> {
                return 1;
            }
            case 2 -> {
                return 2;
            }
            case 3 -> {
                return 3;
            }
            case 4 -> {
                return 4;
            }
            default -> {
                return -1;
            }
        }
    }

    public Student newInstance() {
        scanner.nextLine();
        System.out.println("Input the student's full name:");
        String fullName = scanner.nextLine();
        System.out.println("Input the student's mail:");
        String mail = scanner.nextLine();
        System.out.println("Input the student's phoneNumber:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Input the student's birthDate (dd-MM-yyyy):");
        String birthDate = scanner.nextLine();
        int a = checkInstance();
        if (a == 1) {
            scanner.nextLine();
            System.out.println("Input the student's address:");
            String address = scanner.nextLine();
            NonScholarshipStudent nonScholarshipStudent = new NonScholarshipStudent(fullName, mail, phoneNumber,
                    formatDate(birthDate), address);
            nonScholarshipStudent.setPersonalNumber(generateNumber(nonScholarshipStudent.getId(), "NSS"));
            return nonScholarshipStudent;
        } else if (a == 2) {
            ScholarshipType scholarshipType = askScholarshipType();
            ScholarshipNonHousedStudent scholarshipNonHousedStudent = new ScholarshipNonHousedStudent(fullName, mail,
                    phoneNumber, formatDate(birthDate), scholarshipType, StudentType.nonHoused);
            scholarshipNonHousedStudent.setPersonalNumber(generateNumber(scholarshipNonHousedStudent.getId(), "SNHS"));
            return scholarshipNonHousedStudent;
        } else if (a == 3) {
            if (findRoom()) {
                ScholarshipType scholarshipType = askScholarshipType();
                scanner.nextLine();
                int roomId;
                do {
                    System.out.println("Enter the room's id:");
                    roomId = scanner.nextInt();

                } while (roomRepository.getById(roomId) == null);
                Room room = roomRepository.getById(roomId);
                if (room.getListScholarshipHousedStudents().size() < getValue(room)
                        && room.getRoomState().compareTo(RoomState.nonArchived) == 0) {
                    ScholarshipHousedStudent scholarshipHousedStudent = new ScholarshipHousedStudent(fullName, mail,
                            phoneNumber, formatDate(birthDate), scholarshipType, null, room);
                    scholarshipHousedStudent.setStudentType(StudentType.housed);
                    room.setListScholarshipHousedStudents(scholarshipHousedStudent);
                    scholarshipHousedStudent.setPersonalNumber(generateNumber(scholarshipHousedStudent.getId(), "SHS"));
                    return scholarshipHousedStudent;
                } else {
                    System.out.println("[Room unavailable!]");
                    ScholarshipHousedStudent scholarshipHousedStudent = new ScholarshipHousedStudent(fullName, mail,
                            phoneNumber, formatDate(birthDate), scholarshipType, null, null);
                    scholarshipHousedStudent.setStudentType(StudentType.housed);
                    scholarshipHousedStudent.setPersonalNumber(generateNumber(scholarshipHousedStudent.getId(), "SHS"));
                    scholarshipHousedStudent.setRoom(null);
                    return scholarshipHousedStudent;
                }
            } else {
                ScholarshipType scholarshipType = askScholarshipType();
                System.out.println("[Room unavailable for now!]");
                ScholarshipHousedStudent scholarshipHousedStudent = new ScholarshipHousedStudent(fullName, mail,
                        phoneNumber, formatDate(birthDate), scholarshipType, null, null);
                scholarshipHousedStudent.setStudentType(StudentType.housed);
                scholarshipHousedStudent.setPersonalNumber(generateNumber(scholarshipHousedStudent.getId(), "SHS"));
                return scholarshipHousedStudent;
            }
        } else {
            System.out.println("[Choice unavailable]");
            return null;
        }

    }

    private ScholarshipHousedStudent findStudentWithoutRoom() {
        System.out.println("Input the student's id:");
        int et = scanner.nextInt();
        System.out.println(studentRepository.getById(et));
        if (studentRepository.getById(et) != null) {
            Student studentFound = studentRepository.getById(et);
            if (studentFound instanceof ScholarshipHousedStudent) {
                return (ScholarshipHousedStudent) studentFound;
            } else {
                System.out.println("[This is not a housed student!]");
            }
        } else {
            System.out.println("[Student does not exist]");
        }
        return null;
    }

    private int getValue(Room room) {
        if (room.getRoomType() == RoomType.solo) {

            return 1;
        } else {

            return 2;
        }
    }

    public int askId() {

        int choice;

        System.out.println("Input the room's id:");
        choice = scanner.nextInt();
        return choice;

    }

}