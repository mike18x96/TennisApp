package pl.sda.hibernate.dao;

import pl.sda.hibernate.entity.Group;

import java.util.List;

public interface GroupDao {
    Group create(Group group);
    Group update(Group group);
    Group findById(Long id);
    void delete(Group group);

    List<Group> getAll();

}
