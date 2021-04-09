package edu.matc.persistence;

import edu.matc.entity.ArtistEngagement;
import edu.matc.entity.Country;
import edu.matc.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CountryTest {
    private final Logger logger = LogManager.getLogger(this.getClass());


    GenericDao countryDao;

    @BeforeEach
    void setUp() {
        countryDao = new GenericDao(Country.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getByIdSuccess() {
        Country retrievedCountry = (Country)countryDao.getById(1);
        assertNotNull(retrievedCountry);
        assert(retrievedCountry.getCountryName().equals("United States"));

    }

    @Test
    void getByPropertyEqualSuccess() {


    }

    @Test
    void getAll() {

    }

}
