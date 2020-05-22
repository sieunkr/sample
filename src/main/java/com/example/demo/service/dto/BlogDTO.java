package com.example.demo.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BlogDTO {

    private String title;
    private String link;
    private String blogger;
    private String description;
    private String postdate;
}