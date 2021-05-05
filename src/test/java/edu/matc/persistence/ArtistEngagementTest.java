package edu.matc.persistence;

import edu.matc.entity.Artist;
import edu.matc.entity.ArtistEngagement;
import edu.matc.entity.User;
import edu.matc.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        ArtistEngagement artistEngagement = (ArtistEngagement) artistEngagementDao.getById(1);
        assert(artistEngagement.getUser().getId() == 1);
        assert(artistEngagement.getArtist().getId() == 1);
    }

    @Test
    void deleteArtistEngagementWithSuccess() {
        int aeBefore = artistEngagementDao.getAll().size();
        ArtistEngagement artistEngagement = (ArtistEngagement) artistEngagementDao.getById(1);
        artistEngagementDao.delete(artistEngagement);

        int aeAfter = artistEngagementDao.getAll().size();
        assert (aeAfter == aeBefore - 1);
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

    @Test
    void checkForExistingEngagement() {
        int artistId = 1;

        User user = (User)userDao.getById(1);
        Set<ArtistEngagement> engagements = user.getArtistUserEngagement();
        Boolean artistFound = false;

        for (ArtistEngagement engagement : engagements) {
            Artist thisArtist = engagement.getArtist();
            if (thisArtist.getId() == artistId ) {
                artistFound = true;
                return;
            }
        }
        assert(artistFound == true);

    }

}
