package edu.matc.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entity.Country;
import edu.matc.entity.Region;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jose4j.json.internal.json_simple.JSONArray;
import org.jose4j.json.internal.json_simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * Region API Service
 */
@Path("/regions")
public class RegionService {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Returns regions within a given country
     * @param countryId countryId
     * @return JSON response with a list of regions within a country
     * @throws JsonProcessingException
     */
    @GET
    @Path("{countryId}")
    @Produces("application/json")
    public Response getRegionData(@PathParam("countryId") String countryId) throws JsonProcessingException {
        GenericDao<Region> regionDao = new GenericDao<>(Region.class);
        GenericDao<Country> countryDao = new GenericDao<>(Country.class);
        Country country = countryDao.getById(Integer.parseInt(countryId));
        Set<Region> regions = country.getRegions();


        ObjectMapper mapper = new ObjectMapper();
        String regionData;
        JSONObject json = new JSONObject();
        JSONArray regionArray = new JSONArray();

        for (Region r :regions) {
            JSONObject regionObject = new JSONObject();
            regionObject.put("id", r.getId());
            regionObject.put("name", r.getRegionName());

            //Adding in cities
            /*
            JSONArray cityArray = new JSONArray();
            Set<City> cities = r.getCities();
            for (City c : cities) {
                JSONObject cityObject = new JSONObject();
                cityObject.put("id", c.getId());
                cityObject.put("name", c.getCityName());
                cityArray.add(cityObject);
            }
            regionObject.put("Cities", cityArray);
            */
            regionArray.add(regionObject);
        }
        json.put("Regions", regionArray);

        regionData = json.toJSONString();
        return Response.status(200).entity(regionData).build();
    }
}
