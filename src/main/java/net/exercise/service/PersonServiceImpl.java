package net.exercise.service;

import net.exercise.DAO.PersonDao;
import net.exercise.entity.PersonEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonServiceImpl implements PersonService {

    private PersonDao personDao;

    @Autowired
    public void setPersonDao(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Transactional
    @Override
    public long save(PersonEntity personEntity) {
        return personDao.save(personEntity);
    }

    @Override
    public PersonEntity get(long id) {
        return personDao.get(id);
    }

    @Override
    public List<PersonEntity> list() {
        return personDao.list();
    }


    @Transactional
    @Override
    public void delete(long id) {
        personDao.delete(id);
    }
}
