package edu.matc.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "ArtistEngagement")
@Table(name = "artist_engagement")

public class ArtistEngagement {
    @EmbeddedId
    private ArtistEngagementId id = new ArtistEngagementId();

    @ManyToOne
    @MapsId("userId")
    private User user;

    @ManyToOne
    @MapsId("artistId")
    private Artist artist;
    private LocalDateTime engagementDate;

    public ArtistEngagement() {
    }

    public ArtistEngagement(Artist artist, User user, LocalDateTime engagementDate) {
        this.artist = artist;
        this.user = user;
        this.engagementDate = engagementDate;
    }
    public ArtistEngagementId getId() {
        return id;
    }

    public void setId(ArtistEngagementId id) {
        this.id = id;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public LocalDateTime getEngagementDate() {
        return engagementDate;
    }

    public void setEngagementDate(LocalDateTime engagementDate) {
        this.engagementDate = engagementDate;
    }



}
