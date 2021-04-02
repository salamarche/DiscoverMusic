package edu.matc.persistence;

import edu.matc.entity.*;
import edu.matc.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GenericDaoTest {
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
    void insert() {

        List<User> allUsersBeforeInsert = userDao.getAll();

        User newUser = new User("Chidi Anagonye", "chidi.anagonye@goodplace.hll", "123123", "user");
        int id = userDao.insert(newUser);

        List<User> allUsersAfterInsert = userDao.getAll();

        assert(allUsersAfterInsert.size() == (allUsersBeforeInsert.size() + 1));

    }

    @Test
    void getByIdSuccess() {

        User retrievedUser = (User)userDao.getById(1);
        assertNotNull(retrievedUser);
        assert(retrievedUser.getUserName().equals("jcoyne"));


        Artist retrievedArtist = (Artist)artistDao.getById(1);
        assertNotNull(retrievedArtist);
        assert(retrievedArtist.getArtistName().equals("DJ Test"));


    }

    @Test
    void getByPropertyEqualSuccess() {

        String propertyName = "soundcloudId";
        String value = "soundcloudId100";
        List<Artist> list = artistDao.getByPropertyEqual(propertyName, value);
        //logger.info(list.size());
        assert(list.size() == 1);

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
    void deleteArtistWithSuccess() {

        Artist artist1 = (Artist)artistDao.getById(1);
        Set<ArtistEngagement> artistEngagementSet1 = artist1.getArtistUserEngagement();
        assert(artistEngagementSet1.size() == 3);

        for (ArtistEngagement each : artistEngagementSet1) {
            artistEngagementDao.delete(each);
        }

        Artist artist2 = (Artist)artistDao.getById(1);
        Set<ArtistEngagement> artistEngagementSet2 = artist2.getArtistUserEngagement();
        assert(artistEngagementSet2.size() == 0);

        artistDao.delete(artist1);
        assertNull((Artist)artistDao.getById(1));
    }


    @Test
    void getAll() {
        List<User> users = userDao.getAll();
        assertEquals(6, users.size());

        List<Artist> artists = artistDao.getAll();
        assertEquals(2, artists.size());

        List<ArtistEngagement> artistEngagements = artistEngagementDao.getAll();
        assertEquals(4, artistEngagements.size());


    }

    @Test
    void getPreferredArtistsByUser() {
        User user = (User)userDao.getById(1);
        Set<ArtistEngagement> engagements = user.getArtistUserEngagement();

        assert(engagements.size() == 2);
    }

    @Test
    void engagementByArtist() {
        Artist artist = (Artist)artistDao.getById(1);
        Set<ArtistEngagement> engagements = artist.getArtistUserEngagement();
        assert(engagements.size() == 3);
    }
}