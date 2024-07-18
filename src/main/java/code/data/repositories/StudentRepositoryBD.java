package code.data.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import code.core.Repository;

import code.data.entities.*;
import code.data.entities.NonScholarshipStudent;
import code.data.entities.Student;
import code.data.enums.ScholarshipType;
import code.data.enums.StudentType;

public class StudentRepositoryBD implements Repository<Student> {

    @Override
    public boolean insert(Student objet) {
        int checker = 0;
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
            Statement statement = connection.createStatement();
            if (objet instanceof NonScholarshipStudent nsn) {
                checker = statement.executeUpdate(String.format(
                        "INSERT INTO `student` (`fullName`, `personalNumber`, `mail`, `phoneNumber`, `birthDate`, `address`, `scholarship`, `room`,`type`) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', NULL, NULL, NULL);",
                        nsn.getFullName(), nsn.getPersonalNumber(), nsn.getMail(), nsn.getPhoneNumber(),
                        nsn.getBirthDate().toString(), nsn.getAddress()));
            } else if (objet instanceof ScholarshipNonHousedStudent snhs) {
                checker = statement.executeUpdate(String.format(
                        "INSERT INTO `student` (`fullName`, `personalNumber`, `mail`, `phoneNumber`, `birthDate`, `address`, `scholarship`, `room`,`type`) VALUES ('%s', '%s', '%s', '%s', '%s', NULL, '%s', NULL,'%s');",
                        snhs.getFullName(), snhs.getPersonalNumber(), snhs.getMail(), snhs.getPhoneNumber(),
                        snhs.getBirthDate().toString(), snhs.getScholarshipType().toString(), snhs.getStudentType().toString()));
            } else if (objet instanceof ScholarshipHousedStudent shs) {
                if (shs.getRoom() != null) {
                    checker = statement.executeUpdate(String.format(
                            "INSERT INTO `student` (`fullName`, `personalNumber`, `mail`, `phoneNumber`, `birthDate`, `address`, `scholarship`, `room`,`type`) VALUES ('%s', '%s', '%s', '%s', '%s', 'NULL','%s', '%d','%s');",
                            shs.getFullName(), shs.getPersonalNumber(), shs.getMail(), shs.getPhoneNumber(),
                            shs.getBirthDate().toString(), shs.getScholarshipType().toString(), shs.getRoom().getId(),
                            shs.getStudentType().toString()));
                } else {
                    checker = statement.executeUpdate(String.format(
                            "INSERT INTO `student` (`fullName`, `personalNumber`, `mail`, `phoneNumber`, `birthDate`, `address`, `scholarship`, `room`,`type`) VALUES ('%s', '%s', '%s', '%s', '%s',NULL,'%s', NULL,'%s');",
                            shs.getFullName(), shs.getPersonalNumber(), shs.getMail(), shs.getPhoneNumber(),
                            shs.getBirthDate().toString(), shs.getScholarshipType().toString(),
                            shs.getStudentType().toString()));
                }
            }
        } catch (ClassNotFoundException var5) {
            System.out.println("[Driver loading failed!]");
        } catch (SQLException var6) {
            System.out.println("[Connection failed!]");
        }
        return checker == 0;
    }

    @Override
    public Student getById(int id) {
        Connection connection;

        ScholarshipHousedStudent shs = new ScholarshipHousedStudent();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(String.format("SELECT * FROM `student` WHERE `id`= '%d';", id));

            while (resultSet.next()) {
                shs.setId(resultSet.getInt("id"));
                shs.setFullName(resultSet.getString("fullName"));
                shs.setPersonalNumber(resultSet.getString("personalNumber"));
                shs.setMail(resultSet.getString("mail"));
                shs.setPhoneNumber(resultSet.getString("phoneNumber"));
                shs.setBirthDate(formatDate(resultSet.getString("birthDate")));
                shs.setScholarshipType(ScholarshipType.valueOf(resultSet.getString("scholarship")));
            }
            return shs;
        } catch (ClassNotFoundException var5) {
            System.out.println("[Driver loading failed!]");
        } catch (SQLException var6) {
            System.out.println("[Connection failed!]");
        }
        return null;
    }

    @Override
    public List<Student> selectAll() {
        List<Student> studentList = new ArrayList<>();
        Connection connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `student` ");
            while (rs.next()) {
                Student student = new Student();

                student.setId(rs.getInt("id"));

                student.setPersonalNumber(rs.getString("personalNumber"));

                student.setMail(rs.getString("mail"));

                student.setFullName(rs.getString("fullName"));

                student.setPhoneNumber(rs.getString("phoneNumber"));

                student.setBirthDate(formatDate(rs.getString("birthDate")));

                studentList.add(student);

            }

            System.out.println("[Connection established!]");
        } catch (Exception e) {
            System.out.println("[Driver loading failed!]");
        }
        return studentList;
    }

    public static LocalDate formatDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }

    @Override
    public void update(Student objet) {
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public void update1(Student objet) {
        throw new UnsupportedOperationException("Unimplemented method 'update1'");
    }

    @Override
    public void affect(Student student) {
        if (student != null) {
            System.out.println(student);
            ScholarshipHousedStudent shs = (ScholarshipHousedStudent) student;
            Connection connection = null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
                Statement statement = connection.createStatement();
                statement.executeUpdate(String.format(
                        "UPDATE `student` SET `room`='%d' WHERE `id`='%d' AND `student`.`type`='%s' ;",
                        shs.getRoom().getId(),
                        shs.getId(), StudentType.housed.name()));


            } catch (ClassNotFoundException e) {
                System.out.println("[Driver loading failed!]");
                e.fillInStackTrace();
            } catch (SQLException e) {
                System.out.println("[Connection failed!]");
                e.fillInStackTrace();
            } finally {
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    e.fillInStackTrace();
                }
            }
        } else {
            System.out.println("[Edits non operated!]");
        }
    }

    @Override
    public List<Student> selectBy(int x) {
        List<Student> studentList = new ArrayList<>();
        Connection connection;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/exam2", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(String.format("SELECT * FROM `student` WHERE `room`='%d';", x));
            while (rs.next()) {
                Student student = new Student();

                student.setId(rs.getInt("id"));
                student.setPersonalNumber(rs.getString("personalNumber"));
                student.setMail(rs.getString("mail"));
                student.setFullName(rs.getString("fullName"));
                student.setPhoneNumber(rs.getString("phoneNumber"));
                student.setBirthDate(formatDate(rs.getString("birthDate")));
                studentList.add(student);

            }
        } catch (Exception e) {
            System.out.println("[Driver loading failed!]");
        }
        return studentList;
    }

}