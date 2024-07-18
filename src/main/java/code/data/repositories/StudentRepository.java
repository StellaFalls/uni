package code.data.repositories;

import java.util.ArrayList;
import java.util.List;

import code.core.Repository;

import code.data.entities.ScholarshipHousedStudent;
import code.data.entities.Student;

public class StudentRepository implements Repository<Student> {
    List<Student> listStudents = new ArrayList<>();

    @Override
    public boolean insert(Student et) {
        return listStudents.add(et);

    }

    @Override
    public Student getById(int id) {
        for (Student student : listStudents) {
            if (student.getId() == id) {
                return student;
            }

        }
        return null;

    }

    @Override
    public List<Student> selectAll() {
        return listStudents;
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
    public void affect(Student objet) {
        if (objet != null) {
            System.out.println("[Edit executed!]");
        } else {
            System.out.println("[Edit non executed!]");
        }
    }

    @Override
    public List<Student> selectBy(int x) {
        List<Student> list = new ArrayList<>();
        for (Student student : selectAll()) {
            if (student instanceof ScholarshipHousedStudent scholarshipHousedStudent) {
                if (scholarshipHousedStudent.getRoom() != null) {
                    if (scholarshipHousedStudent.getRoom().getId() == x) {
                        list.add(scholarshipHousedStudent);
                    }
                }
            }

        }
        return list;
    }

}