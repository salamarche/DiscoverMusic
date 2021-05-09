package edu.matc.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.*;


/**
 * Artist class contains getters and setters for Artist object and utility methods addCity and removeCity.
 */
@Entity(name = "Artist")
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name="spotify_id")
    private String spotifyId;

    @Column(name="artist_name")
    private String artistName;

    @Column(name="avatar_url")
    private String avatarUrl;

    @Column(name="description")
    private String description;

    @Column(name="href")
    private String href;

    @JsonIgnore
    @OneToMany(mappedBy = "artist", fetch = FetchType.EAGER)
    private Set<ArtistEngagement> artistUserEngagement = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "artist_location",
            joinColumns = { @JoinColumn(name = "artist_id") },
            inverseJoinColumns = { @JoinColumn(name = "city_id") }
    )
    @JsonIgnore
    private Set<City> cities = new HashSet<>();


    /**
     * Empty constructor
     */
    public Artist() {}

    /**
     * Loaded constructor with basic artist attributes
     * @param spotifyId Spotify id
     * @param artistName Artist Name
     * @param avatarUrl Artist avatar url
     * @param description Artist description
     * @param href Artist external href
     */
    public Artist(String spotifyId, String artistName, String avatarUrl, String description, String href) {
        this.spotifyId = spotifyId;
        this.artistName = artistName;
        this.avatarUrl = avatarUrl;
        this.description = description;
        this.href = href;
    }


    /**
     * Artist avatar url getter
     * @return avatar url
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * Aritst avatar url setter
     * @param avatarUrl avatar url
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * Artist description getter
     * @return descrption
     */
    public String getDescription() {
        return description;
    }

    /**
     * Artist description setter
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Artist id getter
     * @return artist id
     */
    public int getId() {
        return id;
    }

    /**
     * Artist id setter
     * @param id artist id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Artist Spotify id getter
     * @return spotify id
     */
    public String getSpotifyId() {
        return spotifyId;
    }

    /**
     * Artist spotify id setter
     * @param spotifyId spotify id
     */
    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    /**
     * Artist name getter
     * @return artist name
     */
    public String getArtistName() {
        return artistName;
    }

    /**
     * Artist name setter
     * @param artistName artist name
     */
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    /**
     * Artist engagements getter
     * @return Artist's artist-user engagement s
     */
    public Set<ArtistEngagement> getArtistUserEngagement() {
        return artistUserEngagement;
    }

    /**
     * Artist engagements setter
     * @param artistUserEngagement Artist's artist-user engagements
     */
    public void setArtistUserEngagement(Set<ArtistEngagement> artistUserEngagement) {
        this.artistUserEngagement = artistUserEngagement;
    }

    /**
     * Artist city location getter
     * @return Artist cities
     */
    public Set<City> getCities() {
        return cities;
    }

    /**
     * Artist city location setter
     * @param cities Artist citites
     */
    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    /**
     * Artist Href getter
     * @return Artist external href
     */
    public String getHref() {
        return href;
    }

    /**
     * Artist Href setter
     * @param href Artist external href
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * Adds a location to the Artist's set of locations
     * @param city Artist city location
     */
    public void addCity (City city) {
        this.cities.add(city);
        city.getArtists().add(this);

    }

    /**
     * Removes a location from the Artist's set of locations
     * @param city Artist city location
     */
    public void removeCity (City city) {

        for(City c : this.cities) {
            if(c.getId() == city.getId()) {
                this.cities.remove(c);
                break;
            }
        }
        city.getArtists().remove(this);
    }

}
