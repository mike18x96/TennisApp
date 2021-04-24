package pl.sda.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.hibernate.entity.Coach;

import java.util.List;

public class HibernateCoachDao implements CoachDao {
    private final SessionFactory sessionFactory;

    public HibernateCoachDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Coach create(Coach coach) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.persist(coach);

            tx.commit();
            return coach;
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public Coach update(Coach coach) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            final Coach updatedLocation = (Coach) session.merge(coach);

            tx.commit();
            return updatedLocation;
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public Coach findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Coach.class, id);
        }
    }

    @Override
    public void delete(Coach coach) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.delete(coach);

            tx.commit();
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public List<Coach> getAll() {
        try (Session session = sessionFactory.openSession()) {
            final Query<Coach> coachQuery = session.createQuery("from Coach", Coach.class);
            return coachQuery.getResultList();
        }
    }
}
