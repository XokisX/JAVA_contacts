package by.ermakovich.contacts.controller.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class ContactsRequest {
    private Long my_id;
    private ArrayList<Long> friend_id;
}
