package edu.matc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * City entity with getters and setters, and a toString method
 */
@Entity(name = "City")
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;

    @ManyToOne
    @JoinColumn(name="region_id", nullable = false)
    @JsonIgnore
    private Region region;

    @ManyToMany(mappedBy = "cities", fetch = FetchType.EAGER)
    private Set<Artist> artists = new HashSet<>();

    /**
     * Empty Constructor
     */
    public City() {}

    /**
     * City id getter
     * @return city id
     */
    public int getId() {
        return id;
    }

    /**
     * city id setter
     * @param id  city id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * city name getter
     * @return city name
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * city name setter
     * @param cityName city name
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * city latitude getter
     * @return city latitude
     */
    public String getLat() {
        return lat;
    }

    /**
     * city latitude setter
     * @param lat city latitude
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     * city longitude getter
     * @return city longitude
     */
    public String getLng() {
        return lng;
    }

    /**
     * city longitude setter
     * @param lng city longitude
     */
    public void setLng(String lng) {
        this.lng = lng;
    }

    /**
     * city region getter
     * @return city region
     */
    public Region getRegion() {
        return region;
    }

    /**
     * city region setter
     * @param region city region
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     * city artists getter
     * @return city artists
     */
    public Set<Artist> getArtists() {
        return artists;
    }

    /**
     * city artist setter
     * @param artists city artists
     */
    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

    /**
     * to String method that return a city with region and country
     * @return full city location string
     */
    @Override
    public String toString() {

        String cityName = this.getCityName();
        Region region = this.getRegion();
        String regionName = region.getRegionName();
        Country country = region.getCountry();
        String countryName = country.getIso3();

        String cityLocation = cityName + ", " + regionName + ", " + countryName;

        return cityLocation ;
    }
}
