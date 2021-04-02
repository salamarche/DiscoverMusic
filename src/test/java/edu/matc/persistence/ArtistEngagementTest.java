package edu.matc.persistence;

import edu.matc.entity.*;
import edu.matc.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArtistEngagementTest {
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



    }

    @Test
    void getByPropertyEqualSuccess() {



    }

    @Test
    void deleteArtistEngagementWithSuccess() {


    }

    @Test
    void addArtistEngagementWithSuccess() {
        List<ArtistEngagement> beforeUpdate = artistEngagementDao.getAll();

        Artist artist = (Artist)artistDao.getById(2);
        User user = (User)userDao.getById(3);
        LocalDateTime dateTime = LocalDateTime.now();
        ArtistEngagement engagement = new ArtistEngagement(artist, user, dateTime);
        artistEngagementDao.insert(engagement);

        List<ArtistEngagement> afterUpdate = artistEngagementDao.getAll();

        assert(afterUpdate.size() == (beforeUpdate.size() + 1));


    }



    @Test
    void getAll() {

        List<ArtistEngagement> artistEngagements = artistEngagementDao.getAll();
        assertEquals(4, artistEngagements.size());

    }

    @Test
    void getEngagementByUser() {
        User user = (User)userDao.getById(1);
        Set<ArtistEngagement> engagements = user.getArtistUserEngagement();
        assert(engagements.size() == 2);
    }

    @Test
    void getEngagementByArtist() {
        Artist artist = (Artist)artistDao.getById(1);
        Set<ArtistEngagement> engagements = artist.getArtistUserEngagement();
        assert(engagements.size() == 3);
    }

}
