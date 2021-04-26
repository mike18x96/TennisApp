package pl.sda.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.hibernate.entity.Group;

import java.util.List;

public class HibernateGroupDao implements GroupDao {
    private final SessionFactory sessionFactory;

    public HibernateGroupDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Group create(Group group) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.persist(group);

            tx.commit();
            return group;
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public Group update(Group coach) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            final Group updatedCoach = (Group) session.merge(coach);

            tx.commit();
            return updatedCoach;
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public Group findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Group.class, id);
        }
    }

    @Override
    public void delete(Group group) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.delete(group);

            tx.commit();
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public List<Group> getAll() {
        try (Session session = sessionFactory.openSession()) {
            final Query<Group> coachQuery = session.createQuery("from Coach", Group.class);
            return coachQuery.getResultList();
        }
    }
}
