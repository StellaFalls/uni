package code.data.entities;

import java.time.LocalDate;

public class Student {
    protected int id;
    private static int counter;
    protected String personalNumber;
    protected String fullName;
    protected String mail;
    protected String phoneNumber;
    protected LocalDate birthDate;

    public Student(String fullName, String mail, String phoneNumber, LocalDate birthDate) {
        this.id = ++counter;
        this.fullName = fullName;
        this.mail = mail;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Student.counter = counter;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Student [id = " + id + ", personalNumber = " + personalNumber + ", fullName = " + fullName + ", mail = "
                + mail
                + ", phoneNumber = " + phoneNumber + ", birthDate = " + birthDate + "]";
    }

}