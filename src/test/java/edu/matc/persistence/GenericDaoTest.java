package edu.matc.persistence;

import edu.matc.entity.*;
import edu.matc.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenericDaoTest {
    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao userDao;
    GenericDao artistDao;
    GenericDao genericArtistEngagementDao;
    ArtistEngagementDao specialArtistEngagementDao;

    @BeforeEach
    void setUp() {
        userDao = new GenericDao(User.class);
        artistDao = new GenericDao(Artist.class);
        genericArtistEngagementDao = new GenericDao(ArtistEngagement.class);
        specialArtistEngagementDao = new ArtistEngagementDao();
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

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

        User user = (User)userDao.getById(1);
        List<ArtistEngagement> artistEngagementList = specialArtistEngagementDao.getArtistEngagementByUser(user);
        assert(artistEngagementList.size() == 2);

        for (ArtistEngagement each : artistEngagementList) {
            genericArtistEngagementDao.delete(each);
        }

        List<ArtistEngagement> artistEngagementListAfterDelete = specialArtistEngagementDao.getArtistEngagementByUser(user);
        assert(artistEngagementListAfterDelete.size() == 0);

        userDao.delete(user);
        assertNull((User)userDao.getById(1));

    }

    @Test
    void deleteArtistWithSuccess() {

        Artist artist = (Artist)artistDao.getById(1);
        List<ArtistEngagement> artistEngagementList = specialArtistEngagementDao.getArtistEngagementByArtist(artist);
        logger.info(artistEngagementList.size());
        assert(artistEngagementList.size() == 3);

        for (ArtistEngagement each : artistEngagementList) {
            genericArtistEngagementDao.delete(each);
        }

        List<ArtistEngagement> artistEngagementListAfterDelete = specialArtistEngagementDao.getArtistEngagementByArtist(artist);
        assert(artistEngagementListAfterDelete.size() == 0);

        artistDao.delete(artist);
        assertNull((Artist)artistDao.getById(1));
    }


    @Test
    void getAll() {
        List<User> users = userDao.getAll();
        assertEquals(6, users.size());

        List<Artist> artists = artistDao.getAll();
        assertEquals(2, artists.size());

        List<ArtistEngagement> artistEngagements = genericArtistEngagementDao.getAll();
        assertEquals(4, artistEngagements.size());


    }
}