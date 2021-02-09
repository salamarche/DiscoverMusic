package edu.matc.entity;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "User") //class name
@Table(name = "user") //table name

public class User {

    @Column(name = "user_name") //this does not have to be included if the column name is the same as var
    private String userName;



}
