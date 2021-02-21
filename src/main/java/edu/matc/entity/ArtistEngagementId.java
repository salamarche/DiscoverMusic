package edu.matc.entity;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class ArtistEngagementId implements Serializable {
    private static final long serialVersionUID = 1L;


    private int artistId;
    private int userId;

    public ArtistEngagementId() {}

    public ArtistEngagementId(int artistId, int userId) {
        super();
        this.artistId = artistId;
        this.userId = userId;

    }

    public int getArtistId() {
        return artistId;
    }

    public void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
