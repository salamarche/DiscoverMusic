package edu.matc.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * Country entity with getters and setters
 */
@Entity(name = "Country")
@Table(name = "country")

public class Country {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "iso3")
    private String iso3;

    @OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
    private Set<Region> regions;

    /**
     * Empty Country constructor
     */
    public Country() {}

    /**
     * Country id getter
     * @return Country id
     */
    public int getId() {
        return id;
    }

    /**
     * Country id setter
     * @param id Country id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Country name getter
     * @return Country name
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * Country name setter
     * @param countryName Country name
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * Country iso3 code getter
     * @return Country iso3 code
     */
    public String getIso3() {
        return iso3;
    }

    /**
     * Country iso3 code setter
     * @param iso3 Country iso3 code
     */
    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }

    /**
     * Country regions getter
     * @return Country regions
     */
    public Set<Region> getRegions() {
        return regions;
    }

    /**
     * Country regions setter
     * @param regions Country regions
     */
    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }
}
