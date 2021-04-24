package pl.sda.hibernate.dao;

import pl.sda.hibernate.entity.Student;
import java.util.List;

public interface StudentDao {
    Student create(Student student);
    Student update(Student student);
    Student findById(Long id);
    void delete(Student student);

    List<Student> getAll();


}
