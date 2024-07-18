package code.data.entities;

import java.time.LocalDate;

import code.data.enums.ScholarshipType;
import code.data.enums.StudentType;

public class ScholarshipHousedStudent extends ScholarshipStudent {
    protected Room room;

    public ScholarshipHousedStudent(Room room) {
        this.room = room;
    }

    public ScholarshipHousedStudent(String fullName, String mail, String phoneNumber, LocalDate birthDate,
            ScholarshipType scholarshipType,
            StudentType studentType, Room room) {
        super(fullName, mail, phoneNumber, birthDate, scholarshipType, studentType);
        this.room = room;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public ScholarshipHousedStudent() {
        super();
    }

    @Override
    public String toString() {
        return "ScholarshipHousedStudent [id = " + id + ", personalNumber = " + personalNumber + ", room = " + room
                + ", scholarshipType = " + scholarshipType
                + ", fullName = " + fullName + ", mail = " + mail + ", phoneNumber = " + phoneNumber + ", birthDate = "
                + birthDate + "]";
    }

}