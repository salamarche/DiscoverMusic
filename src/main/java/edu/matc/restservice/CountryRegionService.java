package edu.matc.restservice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.entity.Country;
import edu.matc.persistence.GenericDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jose4j.json.internal.json_simple.JSONArray;
import org.jose4j.json.internal.json_simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/countries")
public class CountryRegionService {
    private final Logger logger = LogManager.getLogger(this.getClass());
    @GET
    @Produces("application/json")
    public Response getCountryRegionData() throws JsonProcessingException {
        GenericDao<Country> dao = new GenericDao<>(Country.class);
        List<Country> countries = dao.getAll();
        ObjectMapper mapper = new ObjectMapper();
        //This gets absolutely everything. Because of the JSONignore we can only get it in this direction.
        //String countryData = mapper.writeValueAsString(countries);
        String countryData;
        JSONObject json = new JSONObject();
        JSONArray countryArray = new JSONArray();

        for (Country c : countries) {
            JSONObject countryObject = new JSONObject();
            countryObject.put("id", c.getId());
            countryObject.put("name", c.getCountryName());
            countryObject.put("iso3", c.getIso3());
            countryArray.add(countryObject);
        }

        json.put("Countries", countryArray);
        countryData = json.toJSONString();
        return Response.status(200).entity(countryData).build();

    }
}
