package edu.matc.persistence;

import edu.matc.entity.City;
import edu.matc.entity.Country;
import edu.matc.entity.Region;
import edu.matc.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RegionTest {
    private final Logger logger = LogManager.getLogger(this.getClass());


    GenericDao regionDao;


    @BeforeEach
    void setUp() {
        regionDao = new GenericDao(Region.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");
    }

    @Test
    void getByIdSuccess() {
        Region region = (Region)regionDao.getById(1);
        assertNotNull(region);
        assert(region.getRegionName().equals("Wisconsin"));

    }

    @Test
    void getByPropertyEqualSuccess() {
        String propertyName = "regionName";
        String value = "Wisconsin";
        List<Region> regions = regionDao.getByPropertyEqual(propertyName, value);
        assert(regions.size() == 1 );

    }

    @Test
    void getAll() {

        List<Region> allRegions = regionDao.getAll();
        assert(allRegions.size() == 3);

    }

    @Test
    void getAllCitiesByRegion() {
        Region region = (Region) regionDao.getById(1);
        Set<City> cities = region.getCities();
        assert(cities.size() == 1);
    }

}
