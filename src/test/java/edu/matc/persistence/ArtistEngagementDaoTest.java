package edu.matc.persistence;

import edu.matc.entity.Artist;
import edu.matc.entity.ArtistEngagement;
import edu.matc.entity.User;
import edu.matc.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArtistEngagementDaoTest {
    private final Logger logger = LogManager.getLogger(this.getClass());
    ArtistEngagementDao artistEngagementDao;
    GenericDao<Artist> artistDao;
    GenericDao<User> userDao;

    @BeforeEach
    void setUp() {
        artistEngagementDao = new ArtistEngagementDao();
        artistDao = new GenericDao(Artist.class);
        userDao = new GenericDao<>(User.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getArtistEngagementByArtist() {
        Artist artist = (Artist)artistDao.getById(1);
        List<ArtistEngagement> artistEngagementList = artistEngagementDao.getArtistEngagementByArtist(artist);
        assert(artistEngagementList.size() == 3);

    }

    @Test
    void getArtistEngagementByUser() {
        User user = (User)userDao.getById(1);
        List<ArtistEngagement> artistEngagementList = artistEngagementDao.getArtistEngagementByUser(user);
        assert(artistEngagementList.size() == 2);
    }
}