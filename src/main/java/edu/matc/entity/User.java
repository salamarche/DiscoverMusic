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
    @Column(name="user_name")
    private String userName;
    private String email;
    @Column(name="user_role")
    private String userRole;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<ArtistEngagement> artistUserEngagement = new HashSet<>();

    /**
     * Instantiates a new user
     */
    public User() {}

    /**
     * Instantiates a new user
     * @param userName the user name
     * @param email the user email
     *
     */
    public User(String userName, String email, String role) {
        this.userName = userName;
        this.email = email;
        this.userRole = role;

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
     * @param id
     */
    public void setId(int id) { this.id = id; }

    /**
     * @return
     */
    public int getId() { return id; }

    /**
     * @param userName
     */
    public void setUserName(String userName) { this.userName = userName; }

    /**
     * @return
     */
    public String getUserName() { return userName; }

    /**
     * @param email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * @return
     */
    public String getEmail() { return email; }

    /**
     * @return
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * @param userRole
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }


}
