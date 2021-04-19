package edu.matc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "City")
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;
    @Column(name = "city_name")
    private String cityName;
    private String lat;
    private String lng;

    @ManyToOne
    @JoinColumn(name="region_id", nullable = false)
    @JsonIgnore
    private Region region;

    @ManyToMany(mappedBy = "cities", fetch = FetchType.EAGER)
    private Set<Artist> artists = new HashSet<>();

    public City() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Set<Artist> getArtists() {
        return artists;
    }

    public void setArtists(Set<Artist> artists) {
        this.artists = artists;
    }

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
