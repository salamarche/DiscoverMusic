package edu.matc.entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.*;


@Entity(name = "User") //class name
@Table(name = "user") //table name

public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name = "user_name") //this does not have to be included if the column name is the same as var
    private String userName;
    private String email;
    private String password;

    @OneToMany(mappedBy = "artist")
    private Set<Artist> artistsEngagedByUser = new HashSet<>();

    /**
     * Instantiates a new user
     */
    public User() {}

    /**
     * Instantiates a new user
     * @param userName the user name
     * @param email the user email
     * @param password the user password
     */
    public User(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;

    }

    public Set<Artist> getArtistsEngagedByUser() {
        return artistsEngagedByUser;
    }

    public void setArtistsEngagedByUser(Set<Artist> artistsEngagedByUser) {
        this.artistsEngagedByUser = artistsEngagedByUser;
    }

    public void setId(int id) { this.id = id; }

    public int getId() { return id; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserName() { return userName; }

    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return email; }

    public void setPassword(String password) {this.password = password; }

    public String getPassword() { return password; }


}
