package pl.sda.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import pl.sda.hibernate.dao.CoachDao;
import pl.sda.hibernate.dao.HibernateCoachDao;
import pl.sda.hibernate.entity.Coach;

public class HibernateApp {

    static SessionFactory sessionFactory;

    static CoachDao coachDao;

    public static void main(String[] args) {

        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Coach.class)
                .buildSessionFactory();
        coachDao = new HibernateCoachDao(sessionFactory);

        System.out.println("\n\n--------------------->\n" +
                "Hibernate Session Factory Created");


    }

}
