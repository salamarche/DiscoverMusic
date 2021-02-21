package edu.matc.persistence;

import edu.matc.entity.*;
import edu.matc.test.util.Database;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericDaoTest {
    private final Logger logger = LogManager.getLogger(this.getClass());
    GenericDao userDao;
    GenericDao artistDao;

    @BeforeEach
    void setUp() {
        userDao = new GenericDao(User.class);
        artistDao = new GenericDao(Artist.class);
        Database database = Database.getInstance();
        database.runSQL("cleandb.sql");

    }

    @Test
    void getByIdSuccess() {

        User retrievedUser = (User)userDao.getById(1);
        assertNotNull(retrievedUser);
        assert(retrievedUser.getUserName().equals("jcoyne"));


        Artist retrievedArtist = (Artist)artistDao.getById(1);
        assertNotNull(retrievedArtist);
        assert(retrievedArtist.getArtistName().equals("DJ Test"));


    }

    @Test
    void deleteWithCascadeSuccess() {
        //userDao.delete((User)userDao.getById(1));
        //assertNull((User)userDao.getById(1));

        artistDao.delete((Artist)artistDao.getById(1));
        assertNull((Artist)artistDao.getById(1));


    }

    @Test
    void getAll() {
    }
}