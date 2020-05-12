package com.example.demo;

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