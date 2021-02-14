package edu.matc.persistence;

import edu.matc.entity.User;
import edu.matc.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class UserDaoTest {
    private final Logger logger = LogManager.getLogger(this.getClass());
    UserDao dao;

    /**
     * Set up
     */
    @BeforeEach
    void setUp() {
        dao = new UserDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    /**
     * Verifies gets user by id successfully
     */
    @Test
    void getByIdSuccess() {
        User retrievedUser = dao.getById(3);
        assertEquals("bcurry", retrievedUser.getUserName());
    }

    /**
     * Verifies deletes user successfully
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(3));
        assertNull(dao.getById(3));
    }

    /**
     * Verifies gets all users successfully
     */
    @Test
    void getAllSuccess() {
        List<User> users = dao.getAll();
        assertEquals(6, users.size());
    }

    /**
     * Verify successful get by property (equal match)
     */
    @Test
    void getByPropertyEqualSuccess() {
        List<User> users = dao.getByPropertyEqual("userName", "bcurry");
        assertEquals(1, users.size());
        assertEquals(3, users.get(0).getId());
    }

    /**
     * Verify successful get by property (like match)
     */
    @Test
    void getByPropertyLikeSuccess() {
        List<User> users = dao.getByPropertyLike("userName", "c");
        assertEquals(3, users.size());
    }

    /**
     * Verify successful insert of a user
     */
    @Test
    void insertSuccess() {

        User newUser = new User("fflintstone", "flintstone@bedrock.com", "password");
        int id = dao.insert(newUser);
        assertNotEquals(0,id);
        User insertedUser = dao.getById(id);
        assertEquals("fflintstone", insertedUser.getUserName());

        // Could continue comparing all values, but
        // it may make sense to use .equals()
        // TODO review .equals recommendations http://docs.jboss.org/hibernate/orm/5.2/userguide/html_single/Hibernate_User_Guide.html#mapping-model-pojo-equalshashcode

    }
    @Test
    void saveOrUpdate() {
        User retrievedUser = dao.getById(3);
        assertEquals("bcurry", retrievedUser.getUserName());
        retrievedUser.setUserName("tempName");
        dao.saveOrUpdate(retrievedUser);
        User updatedUser = dao.getById(3);
        assertEquals("tempName", updatedUser.getUserName());

    }



}