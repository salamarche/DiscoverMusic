package edu.matc.persistence;

import edu.matc.entity.Artist;
import edu.matc.entity.ArtistEngagement;
import edu.matc.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.*;

public class ArtistEngagementDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public ArtistEngagementDao() {}

    public List<ArtistEngagement> getArtistEngagementByArtist(Artist artist) {
        Session session = sessionFactory.openSession();
        String propertyName = "artist";

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ArtistEngagement> query = builder.createQuery( ArtistEngagement.class );
        Root<ArtistEngagement> root = query.from( ArtistEngagement.class );
        query.select(root).where(builder.equal(root.get(propertyName), artist));
        List<ArtistEngagement> artistEngagementList = session.createQuery( query ).getResultList();

        return artistEngagementList;
    }

    public List<ArtistEngagement> getArtistEngagementByUser(User user) {
        Session session = sessionFactory.openSession();
        String propertyName = "user";

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<ArtistEngagement> query = builder.createQuery( ArtistEngagement.class );
        Root<ArtistEngagement> root = query.from( ArtistEngagement.class );
        query.select(root).where(builder.equal(root.get(propertyName), user));
        List<ArtistEngagement> artistEngagementList = session.createQuery( query ).getResultList();

        return artistEngagementList;
    }

}
