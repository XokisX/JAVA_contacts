package by.ermakovich.contacts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

//@Data
//@Entity
//@Table(name="users")
public class User {
    public Integer getId() {
        return id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id;
    private String username;

    public User() {

    }


    public User(Integer id, String username, String login, String pass, String email, String number,  boolean isBaned) {
        this.id = id;
        this.username = username;
        this.login = login;
        this.pass = pass;
        this.email = email;
        this.number = number;
        this.isBaned = isBaned;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    private String login;
    private String pass;
    private String email;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    private String number;

    private boolean admin;

    public boolean isBaned() {
        return isBaned;
    }

    public void setBaned(boolean baned) {
        isBaned = baned;
    }

    private boolean isBaned;
}
