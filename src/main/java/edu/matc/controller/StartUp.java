package edu.matc.controller;

import lombok.SneakyThrows;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 * StartUp runs application startup Procedures
 */
@WebServlet(
        name = "applicationStartup",
        urlPatterns = { "/startup" },
        loadOnStartup = 1
)
public class StartUp extends HttpServlet {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Initializing behavior.
     * @param config servlet configuration object
     * @throws ServletException
     */
    @SneakyThrows
    public void init(ServletConfig config) throws ServletException {

        //TODO load properties here, change behavior in LoginPageView, LoginAction, SpotifyApiDao

        // Replaces country data available to JS.
        // Currently treating as static data, but can be re-configured to refresh on startUp

        /*
        CountryService service = new CountryService();
        Response result = service.getCountryData();
        String data = (String) result.getEntity();


        //this is relative to tomcat bin folder
        String filePath = "../../DiscoverMusic/src/main/webapp/data/countries.json";


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

         */

    }
}
