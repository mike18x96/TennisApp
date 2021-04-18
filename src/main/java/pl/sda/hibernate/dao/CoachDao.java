package pl.sda.hibernate.dao;

import pl.sda.hibernate.entity.Coach;

import java.util.List;

public interface CoachDao {
    Coach create(Coach coach);
    Coach update(Coach coach);
    Coach findById(Long id);
    void delete(Coach coach);

    List<Coach> getAll();

}
