package edu.matc.entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


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


    public void setId(int id) { this.id = id; }

    public int getId() { return id; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getUserName() { return userName; }

    public void setEmail(String email) { this.email = email; }

    public String getEmail() { return email; }

    public void setPassword(String password) {this.password = password; }

    public String getPassword() { return password; }


}
