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
    void getByIdSuccess() {

        User retrievedUser = (User)userDao.getById(1);
        assertNotNull(retrievedUser);
        assert(retrievedUser.getUserName().equals("jcoyne"));


        Artist retrievedArtist = (Artist)artistDao.getById(1);
        assertNotNull(retrievedArtist);
        assert(retrievedArtist.getArtistName().equals("DJ Test"));


    }

    @Test
        //TODO: this works for artist and user but it won't work the same for artist engagement
    /**
     *
     */
    void getByPropertyEqualSuccess() {
        /*
        User user = (User)userDao.getById(1);
        String propertyName = "artist";
        List<ArtistEngagement> list = artistEngagementDao.getByPropertyEqual(propertyName, user);
        logger.info(list);
        logger.info(list.size());
        */

        String propertyName = "soundcloudId";
        String value = "soundcloudId100";
        List<Artist> list = artistDao.getByPropertyEqual(propertyName, value);
        //logger.info(list.size());
        assert(list.size() == 1);


    }

    @Test
    void deleteWithCascadeSuccess() {
        //userDao.delete((User)userDao.getById(1));
        //assertNull((User)userDao.getById(1));


        //artistDao.delete((Artist)artistDao.getById(1));
        //assertNull((Artist)artistDao.getById(1));


    }

    @Test
    void getAll() {
    }
}