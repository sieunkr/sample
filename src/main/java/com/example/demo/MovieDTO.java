package com.example.demo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MovieDTO {

    private String title;
    private String link;
    private float userRating;
}
