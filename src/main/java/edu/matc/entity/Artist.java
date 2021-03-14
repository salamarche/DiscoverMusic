package edu.matc.entity;
import edu.matc.entity.User;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.*;


/**
 *
 */
@Entity(name = "Artist")
@Table(name = "artist")

public class Artist {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    @Column(name = "soundcloud_id")
    private String soundcloudId;

    @Column(name = "artist_name")
    private String artistName;

    private String location;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
    private Set<ArtistEngagement> artistUserEngagement = new HashSet<>();

    /**
     *
     */
    //TODO refactor from soundcloud to spotify naming convention
    public Artist() {}

    /**
     * @param soundcloudId
     * @param artistName
     * @param location
     */
    public Artist(String soundcloudId, String artistName, String location) {
        this.soundcloudId = soundcloudId;
        this.artistName = artistName;
        this.location = location;
    }

    /**
     * @return
     */
    public Set<ArtistEngagement> getArtistUserEngagement() {
        return artistUserEngagement;
    }

    /**
     * @param artistUserEngagement
     */
    public void setArtistUserEngagement(Set<ArtistEngagement> artistUserEngagement) {
        this.artistUserEngagement = artistUserEngagement;
    }

    /**
     * @return
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * @param avatarUrl
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }


    /**
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return
     */
    public String getSoundcloudId() {
        return soundcloudId;
    }

    /**
     * @param soundcloudId
     */
    public void setSoundcloudId(String soundcloudId) {
        this.soundcloudId = soundcloudId;
    }

    /**
     * @return
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * @param artistName
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }



}
