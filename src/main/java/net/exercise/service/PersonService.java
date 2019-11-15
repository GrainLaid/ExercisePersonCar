package net.exercise.service;

import net.exercise.entity.PersonEntity;

import java.util.List;

public interface PersonService {
    long save(PersonEntity personEntity);

    PersonEntity get(long id);

    List<PersonEntity> list();

    void delete(long id);
}
