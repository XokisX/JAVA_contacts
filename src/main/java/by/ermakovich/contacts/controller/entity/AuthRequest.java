package by.ermakovich.contacts.controller.entity;

import lombok.Data;

@Data
public class AuthRequest {
    private String login;
    private String password;
}
