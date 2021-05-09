package edu.matc.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * ArtistEngagement entity contains getters and setters, and compareTo methods (implements Comparable)
 */
@Entity(name = "ArtistEngagement")
@Table(name = "artist_engagement")
public class ArtistEngagement implements Comparable<ArtistEngagement> {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @ManyToOne
    //@MapsId("userId")
    private User user;

    @ManyToOne
    //@MapsId("artistId")
    private Artist artist;

    @Column(name = "engagementDate")
    private LocalDateTime engagementDate;

    /**
     * Empty constructor
     */
    public ArtistEngagement() {
    }

    /**
     * Loaded constructor with basic ArtistEngagement attributes
     * @param artist
     * @param user
     * @param engagementDate
     */
    public ArtistEngagement(Artist artist, User user, LocalDateTime engagementDate) {
        this.artist = artist;
        this.user = user;
        this.engagementDate = engagementDate;
    }

    /**
     * Artist Engagement id getter
     * @return Artist Engagement id
     */
    public int getId() {
        return id;
    }

    /**
     * Artist Engagement id
     * @param id Artist Engagement id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Artist Engagement user getter
     * @return Artist Engagement user
     */
    public User getUser() {
        return user;
    }

    /**
     * Artist Engagement user setter
     * @param user Artist Engagement user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Artist Engagement artist getter
     * @return Artist Engagement artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Artist Engagement artist setter
     * @param artist Artist Engagement artist
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * Artist Engagement date getter
     * @return Artist Engagement date
     */
    public LocalDateTime getEngagementDate() {
        return engagementDate;
    }

    /**
     * Artist Engagement date setter
     * @param engagementDate Artist Engagement date
     */
    public void setEngagementDate(LocalDateTime engagementDate) {
        this.engagementDate = engagementDate;
    }

    /**
     * Compares engagement based on engagement date
     * @param engagement artist engagement object
     * @return int representing order of object with positive number, negative number or zero
     */
    @Override
    public int compareTo(ArtistEngagement engagement) {
        return getEngagementDate().compareTo(engagement.getEngagementDate());
    }

}
