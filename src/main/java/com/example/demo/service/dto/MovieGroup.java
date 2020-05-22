package com.example.demo.service.dto;

import java.util.List;
import java.util.stream.Collectors;

public class MovieGroup {

    private final List<MovieDTO> list;

    public MovieGroup(List<MovieDTO> list) {
        this.list = list;
    }

    public List<MovieDTO> getList() {
        return sortByRanking();
    }

    private List<MovieDTO> sortByRanking() {
        return list.stream()
                .sorted((a, b) -> (int)(b.getUserRating()*100) - (int)(a.getUserRating()*100))  //TODO: 깔끔하지 않음..
                .collect(Collectors.toList());
    }
}
