package edu.matc.controller;

import edu.matc.restservice.CountryService;
import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileWriter;

@WebServlet(
        name = "applicationStartup",
        urlPatterns = { "/startup" },
        loadOnStartup = 1
)
public class StartUp extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());
    @SneakyThrows
    public void init(ServletConfig config) throws ServletException {

        CountryService service = new CountryService();
        Response result = service.getCountryData();
        String data = (String) result.getEntity();

        //TODO will this work out on AWS?
        //this is relative to tomcat bin folder
        String filePath = "../../DiscoverMusic/src/main/webapp/data/countries.json";

        //TODO if this isn't really going to change this can just be a static resource for how it's currently used

        //delete existing
        File fileToDelete = new File(filePath);
        if (fileToDelete.delete()) {
            logger.info("file deleted");
        } else {
            logger.info("file not deleted");
        }

        //write file
        try {
            FileWriter file = new FileWriter(filePath);
            file.write(data);
            file.close();
            logger.info("file loaded");
        } catch(Exception e) {
            logger.error("StartUp/init: " + e);
        }


    }
}
