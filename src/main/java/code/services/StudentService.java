package code.services;

import java.util.List;

import code.core.Repository;
import code.core.Service;

import code.data.entities.Student;

public class StudentService implements Service<Student> {
    public StudentService(Repository<Student> studentRepository) {
        this.studentRepository = studentRepository;
    }

    Repository<Student> studentRepository;

    @Override
    public void save(Student et) {
        studentRepository.insert(et);
    }

    @Override
    public Student find(int id) {
        return studentRepository.getById(id);
    }

    @Override
    public List<Student> show() {
        return studentRepository.selectAll();
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
        studentRepository.affect(objet);
    }

    @Override
    public List<Student> selectBy(int x) {
        return studentRepository.selectBy(x);
    }

}