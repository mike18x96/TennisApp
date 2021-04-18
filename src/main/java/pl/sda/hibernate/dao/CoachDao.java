package pl.sda.hibernate.dao;

import pl.sda.hibernate.entity.Coach;

import java.util.List;

public interface CoachDao {
    Coach create(Coach location);
    Coach update(Coach location);
    Coach findById(Long id);
    void delete(Coach location);

    List<Coach> getAll();

}
