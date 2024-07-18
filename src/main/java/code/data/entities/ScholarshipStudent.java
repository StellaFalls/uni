package code.data.entities;

import java.time.LocalDate;

import code.data.enums.ScholarshipType;
import code.data.enums.StudentType;

public class ScholarshipStudent extends Student {
    protected ScholarshipType scholarshipType;
    protected StudentType studentType;

    public StudentType getStudentType() {
        return studentType;
    }

    public void setStudentType(StudentType studentType) {
        this.studentType = studentType;
    }

    public ScholarshipType getScholarshipType() {
        return scholarshipType;
    }

    public void setScholarshipType(ScholarshipType scholarshipType) {
        this.scholarshipType = scholarshipType;
    }

    public ScholarshipStudent() {
    }

    public ScholarshipStudent(String fullName, String mail, String phoneNumber, LocalDate birthDate,
            ScholarshipType scholarshipType, StudentType studentType) {
        super(fullName, mail, phoneNumber, birthDate);
        this.scholarshipType = scholarshipType;
        this.studentType = studentType;
    }

    @Override
    public String toString() {
        return "ScholarshipStudent [" +
                "id = " + id +
                ", personalNumber = '" + personalNumber +
                ", fullName = '" + fullName +
                ", mail = '" + mail +
                ", phoneNumber = '" + phoneNumber +
                ", birthDate = " + birthDate +
                ", scholarshipType = " + scholarshipType +
                ']';
    }

}