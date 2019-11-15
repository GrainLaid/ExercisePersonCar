package net.exercise.DAO;

import net.exercise.entity.PersonEntity;

import java.util.List;

public interface PersonDao {

    long save(PersonEntity personEntity);

    PersonEntity get(long id);

    List<PersonEntity> list();

    void delete(long id);
}
