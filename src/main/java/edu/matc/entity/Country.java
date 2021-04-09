package edu.matc.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity(name = "Country")
@Table(name = "country")

public class Country {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    @Column(name = "countryName")
    private String countryName;

    @Column(name = "iso3")
    private String iso3;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getIso3() {
        return iso3;
    }

    public void setIso3(String iso3) {
        this.iso3 = iso3;
    }
}
