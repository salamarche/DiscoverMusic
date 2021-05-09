package edu.matc.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entity.City;
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
 * City API Service
 */
@Path("/cities")
public class CityService {
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Returns cities within specified region
     * @param regionId regionId
     * @return JSON response containing array of cities
     * @throws JsonProcessingException
     */
    @GET
    @Path("{regionId}")
    @Produces("application/json")
    public Response getCityData(@PathParam("regionId") String regionId) throws JsonProcessingException {
        GenericDao<City> cityDao = new GenericDao<>(City.class);
        GenericDao<Region> regionDao = new GenericDao<>(Region.class);
        Region region = regionDao.getById(Integer.parseInt(regionId));
        Set<City> cities = region.getCities();

        ObjectMapper mapper = new ObjectMapper();
        String cityData;
        JSONObject json = new JSONObject();
        JSONArray cityArray = new JSONArray();

        for (City c : cities) {
            JSONObject cityObject = new JSONObject();
            cityObject.put("id", c.getId());
            cityObject.put("name",c.getCityName());

            //optional add in artists...

            cityArray.add(cityObject);
        }
        json.put("Cities", cityArray);

        cityData = json.toJSONString();
        return Response.status(200).entity(cityData).build();
    }
}
