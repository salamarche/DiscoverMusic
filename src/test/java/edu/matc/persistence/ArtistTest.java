package edu.matc.persistence;

import edu.matc.entity.Artist;
import edu.matc.entity.ArtistEngagement;
import edu.matc.entity.City;
import edu.matc.entity.User;
import edu.matc.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArtistTest {
    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao userDao;
    GenericDao artistDao;
    GenericDao artistEngagementDao;
    GenericDao cityDao;


    @BeforeEach
    void setUp() {
        userDao = new GenericDao(User.class);
        artistDao = new GenericDao(Artist.class);
        artistEngagementDao = new GenericDao(ArtistEngagement.class);
        cityDao = new GenericDao<City>(City.class);

        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }


    @Test
    void insertArtistWithSuccess() {
        List<Artist> allArtistsBeforeInsert = artistDao.getAll();
        Artist newArtist = new Artist("abc123", "jukeboxxx", "url", "description", "href");
        artistDao.insert(newArtist);
        List<Artist> allArtistsAfterInsert = artistDao.getAll();
        assert(allArtistsAfterInsert.size() == (allArtistsBeforeInsert.size() + 1));
    }


    @Test
    void updateArtistWithSuccess() {
        Artist artist = (Artist) artistDao.getById(1);
        artist.setArtistName("NewName");
        artistDao.saveOrUpdate(artist);

        Artist artistAfterUpdate = (Artist) artistDao.getById(1);
        assert(artistAfterUpdate.getArtistName().equals("NewName"));
    }

    @Test
    void getByIdSuccess() {

        Artist retrievedArtist = (Artist)artistDao.getById(1);
        assertNotNull(retrievedArtist);
        assert(retrievedArtist.getArtistName().equals("DJ Test"));
    }

    @Test
    void getByPropertyEqualSuccess() {

        String propertyName = "spotifyId";
        String value = "id1";
        List<Artist> list = artistDao.getByPropertyEqual(propertyName, value);
        assert(list.size() == 1);
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
        List<Artist> artists = artistDao.getAll();
        assertEquals(2, artists.size());
    }

    @Test
    void addArtistLocation() {
        Artist artist = (Artist)artistDao.getById(2);
        int artistCities = artist.getCities().size();
        City city = (City)cityDao.getById(1);
        artist.addCity(city);
        artistDao.saveOrUpdate(artist);

        Artist artistAfterUpdate = (Artist)artistDao.getById(2);
        int artistCitiesAfterUpdate = artistAfterUpdate.getCities().size();
        assert (artistCitiesAfterUpdate == artistCities + 1);

    }

    @Test
    void deleteArtistLocation() {
        Artist artist = (Artist)artistDao.getById(2);
        Set<City> artistCities = artist.getCities();
        int artistCitiesLength = artist.getCities().size();
        City cityToRemove = null;
        for (City c: artistCities) {
            cityToRemove = c;
        }

        artist.removeCity(cityToRemove);
        artistDao.saveOrUpdate(artist);

        Artist artistAfterUpdate = (Artist)artistDao.getById(2);
        int artistCitiesLengthAfterUpdate = artistAfterUpdate.getCities().size();

        assert(artistCitiesLengthAfterUpdate == artistCitiesLength - 1);


    }



}