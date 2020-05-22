package com.example.demo.service.dto;

import java.util.List;
import java.util.stream.Collectors;

public class MovieGroup {

    private final List<MovieDTO> list;

    public MovieGroup(List<MovieDTO> list) {
        this.list = excludeOfRanking(list);
    }

    public List<MovieDTO> getList() {
        return sortByRanking();
    }

    private List<MovieDTO> sortByRanking() {
        return list.stream()
                .sorted((a, b) -> (int)(b.getUserRating()*100) - (int)(a.getUserRating()*100))  //TODO: 깔끔하지 않음..
                .collect(Collectors.toList());
    }

    //TODO: 파라미터로 넘기는 구조가 깔끔하지 않은 듯..
    private List<MovieDTO> excludeOfRanking(List<MovieDTO> list) {
        return list.stream()
                .filter(b -> !((Float)b.getUserRating()).equals(0.0f))
                .collect(Collectors.toList());
    }
}