package pl.sda.hibernate.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import pl.sda.hibernate.entity.Parent;

import java.util.List;

public class HibernateParentDao implements ParentDao{
    private final SessionFactory sessionFactory;

    public HibernateParentDaoDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Parent create(Parent parent) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.persist(parent);

            tx.commit();
            return parent;
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;}
    }

    @Override
    public Parent update(Parent parent) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            final Parent updatedParent = (Parent) session.merge(parent);

            tx.commit();
            return updatedParent;
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
    }

    @Override
    public Parent findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Parent.class, id);
        }
    }

    @Override
    public void delete(Parent parent) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();

            session.delete(parent);

            tx.commit();
        } catch (Exception ex) {
            if (tx != null && !tx.getRollbackOnly()) {
                tx.rollback();
            }
            throw ex;
        }
    }

    @Override
    public List<Parent> getAll() {
        try (Session session = sessionFactory.openSession()) {
            final Query<Parent> locationQuery = session.createQuery("from Parent", Parent.class);
            return locationQuery.getResultList();
        }
    }
}
