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


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "artist_engagement",
            joinColumns = {
                @JoinColumn(name = "artist_id"/*, nullable = false, updatable = false*/)
            },
            inverseJoinColumns = {
                @JoinColumn(name = "user_id"/*, nullable = false, updatable = false*/)
             })
    private Set<User> engagedUsers = new HashSet<>();

    /**
     *
     */
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
    public Set<User> getEngagedUsers() {
        return engagedUsers;
    }

    /**
     * @param engagedUsers
     */
    public void setEngagedUsers(Set<User> engagedUsers) {
        this.engagedUsers = engagedUsers;
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
