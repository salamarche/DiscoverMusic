package edu.matc.persistence;

import edu.matc.entity.Artist;
import edu.matc.entity.City;
import edu.matc.entity.Region;
import edu.matc.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CityTest {

    private final Logger logger = LogManager.getLogger(this.getClass());


    GenericDao cityDao;


    @BeforeEach
    void setUp() {
        cityDao = new GenericDao(City.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getByIdSuccess() {
        City city = (City) cityDao.getById(1);
        assertNotNull(city);
        assert(city.getCityName().equals("Montreal"));

    }

    @Test
    void getByPropertyEqualSuccess() {
        String propertyName = "cityName";
        String value = "Montreal";
        List<Region> regions = cityDao.getByPropertyEqual(propertyName, value);
        assert(regions.size() == 1 );

    }

    @Test
    void getAll() {

        List<City> allCities = cityDao.getAll();
        assert(allCities.size() == 2);

    }

    @Test
    void getAllArtistsByCity() {
        City city = (City) cityDao.getById(1);
        Set<Artist> artists = city.getArtists();
        assert(artists.size() == 1);
    }

}
