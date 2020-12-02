package by.ermakovich.contacts.entity;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Data
public class UserEntity {
    public String getUsername() {
        return username;
    }
    public UserEntity(){

    }

    public UserEntity(String username, String login, String email, String number, Boolean is_blocked, String password, RoleEntity roleEntity) {
        this.username = username;
        this.login = login;
        this.email = email;
        this.number = number;
        this.is_blocked = is_blocked;
        this.password = password;
        this.roleEntity = roleEntity;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Boolean getIs_blocked() {
        return is_blocked;
    }

    public void setIs_blocked(Boolean is_blocked) {
        this.is_blocked = is_blocked;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotEmpty
    @NotNull
    private  String username;

    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String login;

    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String email;

    @NotEmpty
    @NotNull
    @Column(unique = true)
    private String number;


    @Column
    private Boolean is_blocked;

    @Column
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private  RoleEntity roleEntity;

}
