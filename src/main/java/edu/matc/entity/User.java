package edu.matc.entity;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.*;


/**
 * User entity contains getters and setters
 */
@Entity(name = "User") //class name
@Table(name = "user") //table name
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private int id;

    @Column(name="user_name")
    private String userName;

    @Column(name="email")
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
     * @param role the user role
     *
     */
    public User(String userName, String email, String role) {
        this.userName = userName;
        this.email = email;
        this.userRole = role;
    }

    /**
     * Artist User Engagement setter
     * @return Artist User Engagement
     */
    public Set<ArtistEngagement> getArtistUserEngagement() {
        return artistUserEngagement;
    }

    /**
     * Artist User Engagement getter
     * @param artistUserEngagement Artist User Engagement
     */
    public void setArtistUserEngagement(Set<ArtistEngagement> artistUserEngagement) {
        this.artistUserEngagement = artistUserEngagement;
    }

    /**
     * User id setter
     * @param id User id
     */
    public void setId(int id) { this.id = id; }

    /**
     * User id getter
     * @return User id
     */
    public int getId() { return id; }

    /**
     * User name setter
     * @param userName User name
     */
    public void setUserName(String userName) { this.userName = userName; }

    /**
     * User name getter
     * @return User name
     */
    public String getUserName() { return userName; }

    /**
     * User email setter
     * @param email User email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     * User email getter
     * @return User email
     */
    public String getEmail() { return email; }

    /**
     * User role getter
     * @return User role
     */
    public String getUserRole() {
        return userRole;
    }

    /**
     * User role setter
     * @param userRole User role
     */
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }


}
