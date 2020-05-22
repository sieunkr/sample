package com.example.demo.repository.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBlog {

    private Integer total;
    private List<naverDocument> items;

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class naverDocument{
        private String title;
        private String link;
        private String bloggername;
        private String description;
        private String postdate;
    }
}