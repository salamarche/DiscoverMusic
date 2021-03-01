package edu.matc.persistence;

import edu.matc.entity.Artist;
import edu.matc.entity.ArtistEngagement;
import edu.matc.entity.ArtistEngagementId;
import edu.matc.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

public class ArtistEngagementDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public ArtistEngagementDao() {}

    public ArtistEngagement getByCompositeId(Artist artist, User user) {
        Session session = sessionFactory.openSession();
        String property1 = "artist";
        String property2 = "user";

        CriteriaBuilder builder = session.getCriteriaBuilder();  //cb
        CriteriaQuery<ArtistEngagement> query = builder.createQuery( ArtistEngagement.class ); //cr
        Root<ArtistEngagement> root = query.from( ArtistEngagement.class );

        Predicate[] predicates = new Predicate[2];
        predicates[0] = builder.equal(root.get(property1), artist);
        predicates[1] = builder.equal(root. get(property2), user);
        query.select(root).where(predicates);

        ArtistEngagement artistEngagement = session.createQuery( query ).getSingleResult();
        return artistEngagement;
    }



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
