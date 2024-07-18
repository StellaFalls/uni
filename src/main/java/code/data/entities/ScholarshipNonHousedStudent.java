package code.data.entities;

import java.time.LocalDate;

import code.data.enums.ScholarshipType;
import code.data.enums.StudentType;

public class ScholarshipNonHousedStudent extends ScholarshipStudent {
    public ScholarshipNonHousedStudent(String fullName, String mail, String phoneNumber, LocalDate birthDate,
            ScholarshipType scholarshipType, StudentType studentType) {
        super(fullName, mail, phoneNumber, birthDate, scholarshipType, studentType);
    }

}