package by.ermakovich.contacts.controller.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ContactRequest {

    @NotNull
    private Long my_id;
    @NotNull
    private Long friend_id;
}
