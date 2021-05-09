package edu.matc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Region entity contains getters and setters
 */
@Entity(name = "Region")
@Table(name = "region")

public class Region {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "region_name")
    private String regionName;

    @ManyToOne
    @JoinColumn(name="country_id", nullable = false)
    @JsonIgnore
    private Country country;

    @OneToMany(mappedBy = "region", fetch = FetchType.EAGER)
    private Set<City> cities;

    /**
     * Empty constructor
     */
    public Region() {}

    /**
     * Region id getter
     * @return Region id
     */
    public int getId() {
        return id;
    }

    /**
     * Region id setter
     * @param id Region id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Region name getter
     * @return Region name
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * Region name setter
     * @param regionName Region name
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * Region country getter
     * @return Region country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Region country setter
     * @param country Region country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Region cities getter
     * @return region cities
     */
    public Set<City> getCities() {
        return cities;
    }

    /**
     * Region cities setter
     * @param cities region cities
     */
    public void setCities(Set<City> cities) {
        this.cities = cities;
    }

}
