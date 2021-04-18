package pl.sda.hibernate.dao;

import pl.sda.hibernate.entity.Parent;
import java.util.List;

    public interface ParentDao {
        Parent create(Parent parent);
        Parent update(Parent parent);
        Parent findById(Long id);
        void delete(Parent parent);

        List<Parent> getAll();

    }
