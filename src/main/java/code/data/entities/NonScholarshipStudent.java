package code.data.entities;

import java.time.LocalDate;

public class NonScholarshipStudent extends Student {
    protected String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public NonScholarshipStudent(String fullName, String mail, String phoneNumber, LocalDate birthDate,
            String address) {
        super(fullName, mail, phoneNumber, birthDate);
        this.address = address;
    }

    @Override
    public String toString() {
        return "NonScholarshipStudent [id = " + id + ", address = " + address + ", personalNumber = " + personalNumber
                + ", fullName = "
                + fullName + ", mail = " + mail + ", phoneNumber = " + phoneNumber + ", birthDate = " + birthDate + "]";
    }

}