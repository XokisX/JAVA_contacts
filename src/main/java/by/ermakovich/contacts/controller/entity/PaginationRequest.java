package by.ermakovich.contacts.controller.entity;

import lombok.Data;

@Data
public class PaginationRequest {
    private Integer page;
    private Integer limit;
}
