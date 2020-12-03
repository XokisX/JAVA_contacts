package by.ermakovich.contacts.controller.entity;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class RegisterByAdminRequest {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @NotEmpty
    private String username;
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    @NotEmpty
    private String number;
    @NotEmpty
    private boolean admin;
}
