package pl.sda.hibernate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.hibernate.entity.Address;
import pl.sda.hibernate.entity.Coach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibernateCoachDaoTest {

    public final Coach testCoach1 = new Coach(
            null,
            "Michal",
            "LastName",
            "test email",
            new Address("street test", "street no test", "flat no test", "zip code test", "city test")
    );
    public final Coach testCoach2 = new Coach(
            null,
            "Michal 2",
            "LastName",
            "test email 2",
            new Address("street test 2", "street no test 2", "flat no test 2", "zip code test 2", "city test 2")
    );
    private HibernateCoachDao hibernateCoachDao;
    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Coach.class)
                .buildSessionFactory();
        hibernateCoachDao = new HibernateCoachDao(sessionFactory);

        final Coach savedTestCoach1 = hibernateCoachDao.create(testCoach1);
        testCoach1.setId(savedTestCoach1.getId());
        final Coach savedTestCoach2 = hibernateCoachDao.create(testCoach2);
        testCoach2.setId(savedTestCoach2.getId());
    }

    @Test
    void shouldCreateLocation() {
        Coach testCoach = new Coach();
        testCoach.setFirstName("Michal");
        testCoach.setLastName("LastName");
        testCoach.setEmail("test email");
        testCoach.setAddress(new Address("street test", "street no test", "flat no test", "zip code test", "city test"));
        final int expectedSize = hibernateCoachDao.getAll().size() + 1;

        final Coach savedCoach = hibernateCoachDao.create(testCoach);

        final Coach actualCoach = hibernateCoachDao.findById(savedCoach.getId());
        final int actualSize = hibernateCoachDao.getAll().size();

        final Coach expectedCoach = new Coach();
        expectedCoach.setId(savedCoach.getId());
        expectedCoach.setFirstName(testCoach.getFirstName());
        expectedCoach.setLastName(testCoach.getLastName());
        expectedCoach.setEmail(testCoach.getEmail());
        expectedCoach.setAddress(testCoach.getAddress());

        assertEquals(expectedSize, actualSize);
        assertEquals(expectedCoach, actualCoach);
    }

    @Test
    void shouldFindById() {

        final Coach actualCoach = hibernateCoachDao.findById(testCoach1.getId());
        assertEquals(testCoach1, actualCoach);
    }

    @Test
    void shouldUpdateLocation() {

        final Coach modifiedCoach = hibernateCoachDao.findById(testCoach2.getId());
        modifiedCoach.setId(1L);
        modifiedCoach.setFirstName("modified name");
        modifiedCoach.setLastName("modified name");
        modifiedCoach.setEmail("modified email");
        modifiedCoach.setAddress(new Address("modified street", "modified street no", "modified flat", "modified zip code", "modified city"));

        final Coach updatedCoach = hibernateCoachDao.update(modifiedCoach);

        assertEquals(modifiedCoach, updatedCoach);
        assertNotSame(modifiedCoach, updatedCoach);

        final Coach actualCoach = hibernateCoachDao.findById(updatedCoach.getId());
        assertEquals(modifiedCoach, actualCoach);
    }

    @Test
    void shouldDeleteCoach() {
        final int expectedSize = hibernateCoachDao.getAll().size() - 1;

        hibernateCoachDao.delete(testCoach1);

        final List<Coach> coachList = hibernateCoachDao.getAll();
        final int actualSize = coachList.size();
        assertEquals(expectedSize, actualSize);
        assertFalse(coachList.contains(testCoach1));

        final Coach unexpectedCoach = hibernateCoachDao.findById(testCoach1.getId());
        assertNull(unexpectedCoach);
    }

    @Test
    void shouldGetAll() {
        final List<Coach> coachList = hibernateCoachDao.getAll();

        assertEquals(2, coachList.size());
        assertTrue(coachList.contains(testCoach1));
        assertTrue(coachList.contains(testCoach2));
    }

    @AfterEach
    void tearDown() {
        sessionFactory.close();
    }


}
