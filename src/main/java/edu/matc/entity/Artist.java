package edu.matc.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name="spotify_id")
    private String spotifyId;
    @Column(name="artist_name")
    private String artistName;
    @Column(name="avatar_url")
    private String avatarUrl;
    @Column(name="description")
    private String description;

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
     *
     */
    public Artist() {}

    /**
     * @param soundcloudId
     * @param artistName
     *
     */
    public Artist(String soundcloudId, String artistName) {
        this.spotifyId = soundcloudId;
        this.artistName = artistName;

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
    public String getSpotifyId() {
        return spotifyId;
    }

    /**
     * @param soundcloudId
     */
    public void setSpotifyId(String soundcloudId) {
        this.spotifyId = soundcloudId;
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
    public Set<City> getCities() {
        return cities;
    }

    /**
     * @param cities
     */
    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

    /**
     * Adds a location to the Artist's set of locations
     * @param city
     */
    public void addCity (City city) {
        this.cities.add(city);
        city.getArtists().add(this);

    }

    /**
     * @param city
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

    /**
     * @return
     */
    public Map<Integer, String> readableLocations () {
        Map<Integer, String> artistLocations = new HashMap<>();
        Set<City> artistCities = this.getCities();

        for (City city : artistCities) {
            String cityName = city.getCityName();
            Region region = city.getRegion();
            String regionName = region.getRegionName();
            Country country = region.getCountry();
            String countryName = country.getIso3();

            String locationString = cityName + ", " + regionName + ", " + countryName;
            Integer cityId = city.getId();
            artistLocations.put(cityId, locationString);
        }

        return artistLocations;
    }


}
