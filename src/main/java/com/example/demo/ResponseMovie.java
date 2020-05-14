package com.example.demo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseMovie {

    private List<Item> items;

    @Getter
    public static class Item {
        String title;
        String link;
        String actor;
        String director;
        float userRating;
        //...TODO: 필드 추가
    }
}
