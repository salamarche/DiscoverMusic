package edu.matc.persistence;

import edu.matc.entity.Artist;
import edu.matc.entity.ArtistEngagement;
import edu.matc.entity.Region;
import edu.matc.entity.User;
import edu.matc.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao userDao;
    GenericDao artistDao;
    GenericDao artistEngagementDao;


    @BeforeEach
    void setUp() {
        userDao = new GenericDao(User.class);
        artistDao = new GenericDao(Artist.class);
        artistEngagementDao = new GenericDao(ArtistEngagement.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    @Test
    void insertUserWithSuccess() {

        List<User> allUsersBeforeInsert = userDao.getAll();
        User newUser = new User("Chidi Anagonye", "chidi.anagonye@goodplace.hll", "user");
        userDao.insert(newUser);
        List<User> allUsersAfterInsert = userDao.getAll();
        assert(allUsersAfterInsert.size() == (allUsersBeforeInsert.size() + 1));

    }

    @Test
    void updateUserWithSuccess() {
        User user = (User)userDao.getById(1);
        user.setUserName("NewName");
        userDao.saveOrUpdate(user);

        User userAfterUpdate = (User)userDao.getById(1);
        assert(userAfterUpdate.getUserName().equals("NewName"));

    }



    @Test
    void getByIdSuccess() {

        User retrievedUser = (User)userDao.getById(1);
        assertNotNull(retrievedUser);
        assert(retrievedUser.getUserName().equals("jcoyne"));


    }

    @Test
    void getByPropertyEqualSuccess() {
        String propertyName = "userRole";
        String value = "user";
        List<Region> regions = userDao.getByPropertyEqual(propertyName, value);
        assert(regions.size() == 6 );


    }

    @Test
    void deleteUserWithSuccess() {

        User user1 = (User)userDao.getById(1);
        Set<ArtistEngagement> artistEngagementSet1 = user1.getArtistUserEngagement();
        assert(artistEngagementSet1.size() == 2);

        for (ArtistEngagement each : artistEngagementSet1) {
            artistEngagementDao.delete(each);

        }
        User user2 = (User)userDao.getById(1);
        Set<ArtistEngagement> artistEngagementSet2 = user2.getArtistUserEngagement();
        assert(artistEngagementSet2.size() == 0);


        userDao.delete(user1);
        assertNull((User)userDao.getById(1));

    }


    @Test
    void getAll() {
        List<User> users = userDao.getAll();
        assertEquals(6, users.size());

    }

}