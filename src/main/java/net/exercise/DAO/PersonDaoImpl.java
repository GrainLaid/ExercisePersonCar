package net.exercise.DAO;

import net.exercise.entity.PersonEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public long save(PersonEntity personEntity) {
        sessionFactory.getCurrentSession().save(personEntity);
        return personEntity.getId();
    }

    @Override
    public PersonEntity get(long id) {
        return sessionFactory.getCurrentSession().get(PersonEntity.class, id);
    }

    @Override
    public List<PersonEntity> list() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<PersonEntity> cq = cb.createQuery(PersonEntity.class);
        Root<PersonEntity> root = cq.from(PersonEntity.class);
        cq.select(root);
        Query<PersonEntity> query = session.createQuery(cq);
        return query.getResultList();
    }


    @Override
    public void delete(long id) {
        Session session = sessionFactory.getCurrentSession();
        PersonEntity personEntity = session.byId(PersonEntity.class).load(id);
        session.delete(personEntity);
    }
}
