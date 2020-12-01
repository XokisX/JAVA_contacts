package by.ermakovich.contacts.controller.entity;

import lombok.Data;

@Data
public class SearchRequest {
    private String info;
    private Integer page;
    private Integer limit;
}
