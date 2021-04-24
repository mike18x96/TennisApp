package pl.sda.hibernate.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.sda.hibernate.entity.Address;
import pl.sda.hibernate.entity.Parent;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibernateParentDaoTest {

    public final Parent testParent1 = new Parent(
            null,
            "Artur",
            "55555555",
            "test email",
            new Address("street test", "street no test", "flat no test", "zip code test", "city test")
    );
    public final Parent testParent2 = new Parent(
            null,
            "Michal",
            "666666666",
            "test email 2",
            new Address("street test 2", "street no test 2", "flat no test 2", "zip code test 2", "city test 2")
    );
    private HibernateParentDao hibernateParentDao;
    private SessionFactory sessionFactory;

    @BeforeEach
    void setUp() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Parent.class)
                .buildSessionFactory();
        hibernateParentDao = new HibernateParentDao(sessionFactory);

        final Parent savedTestParent1 = hibernateParentDao.create(testParent1);
        testParent1.setId(savedTestParent1.getId());
        final Parent savedTestParent2 = hibernateParentDao.create(testParent2);
        testParent2.setId(savedTestParent2.getId());
    }

    @Test
    void shouldCreateLocation() {
        Parent testParent = new Parent();
        testParent.setName("Michal");
        testParent.setEmail("test email");
        testParent.setAddress(new Address("street test", "street no test", "flat no test", "zip code test", "city test"));
        final int expectedSize = hibernateParentDao.getAll().size() + 1;

        final Parent savedParent = hibernateParentDao.create(testParent);

        final Parent actualParent = hibernateParentDao.findById(savedParent.getId());
        final int actualSize = hibernateParentDao.getAll().size();

        final Parent expectedParent = new Parent();
        expectedParent.setId(savedParent.getId());
        expectedParent.setName(testParent.getName());
        expectedParent.setEmail(testParent.getEmail());
        expectedParent.setAddress(testParent.getAddress());

        assertEquals(expectedSize, actualSize);
        assertEquals(expectedParent, actualParent);
    }

    @Test
    void shouldFindById() {

        final Parent actualParent = hibernateParentDao.findById(testParent1.getId());
        assertEquals(testParent1, actualParent);
    }

    @Test
    void shouldUpdateLocation() {

        final Parent modifiedParent = hibernateParentDao.findById(testParent2.getId());
        modifiedParent.setId(1L);
        modifiedParent.setName("modified name");
        modifiedParent.setEmail("modified email");
        modifiedParent.setAddress(new Address("modified street", "modified street no", "modified flat", "modified zip code", "modified city"));

        final Parent updatedParent = hibernateParentDao.update(modifiedParent);

        assertEquals(modifiedParent, updatedParent);
        assertNotSame(modifiedParent, updatedParent);

        final Parent actualParent = hibernateParentDao.findById(updatedParent.getId());
        assertEquals(modifiedParent, actualParent);
    }

    @Test
    void shouldDeleteParent() {
        final int expectedSize = hibernateParentDao.getAll().size() - 1;

        hibernateParentDao.delete(testParent1);

        final List<Parent> coachList = hibernateParentDao.getAll();
        final int actualSize = coachList.size();
        assertEquals(expectedSize, actualSize);
        assertFalse(coachList.contains(testParent1));

        final Parent unexpectedParent = hibernateParentDao.findById(testParent1.getId());
        assertNull(unexpectedParent);
    }

    @Test
    void shouldGetAll() {
        final List<Parent> coachList = hibernateParentDao.getAll();

        assertEquals(2, coachList.size());
        assertTrue(coachList.contains(testParent1));
        assertTrue(coachList.contains(testParent2));
    }

    @AfterEach
    void tearDown() {
        sessionFactory.close();
    }


}
