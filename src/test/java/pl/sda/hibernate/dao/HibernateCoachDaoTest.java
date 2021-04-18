package pl.sda.hibernate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.hibernate.entity.Coach;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibernateCoachDaoTest {

    public final Coach testCoach1 = new Coach(
            5L,
            "Michal",
            "test email",
            "test address"
    );
    public final Coach testCoach2 = new Coach(
            -5L,
            "Michal 2",
            "test email 2",
            "test address 2"
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
        testCoach.setId(6L);
        testCoach.setName("Michal");
        testCoach.setEmail("test email");
        testCoach.setAddress("test address");
        final int expectedSize = hibernateCoachDao.getAll().size() + 1;

        final Coach savedCoach = hibernateCoachDao.create(testCoach);

        final Coach actualCoach = hibernateCoachDao.findById(savedCoach.getId());
        final int actualSize = hibernateCoachDao.getAll().size();

        final Coach expectedCoach = new Coach();
        expectedCoach.setId(savedCoach.getId());
        expectedCoach.setName(testCoach.getName());
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
        modifiedCoach.setName("modified name");
        modifiedCoach.setEmail("modified email");
        modifiedCoach.setAddress("modified address");

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
