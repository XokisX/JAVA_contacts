package by.ermakovich.contacts.controller.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class EmailSendRequest {
    private Long my_id;
    private ArrayList<Long> contacts;
    private String message;
}
