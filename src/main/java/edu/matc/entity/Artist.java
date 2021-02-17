package edu.matc.entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity(name = "Artist")
@Table(name = "artist")

public class Artist {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private int id;

    private String soundcloud_id;

}
