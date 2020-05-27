package com.example.demo.service;

import com.example.demo.service.dto.MovieDTO;
import com.example.demo.repository.MovieRepository;
import com.example.demo.service.dto.MovieGroup;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> findByQuery(String query) {

        MovieGroup movieGroup = new MovieGroup(movieRepository.findByQuery(query).getItems().stream()
                .map(m -> MovieDTO.builder()
                        .title(removeSpecialCharacter(m.getTitle()))
                        .link(m.getLink())
                        .userRating(m.getUserRating())
                        .build())
                .collect(Collectors.toList()));

        return movieGroup.getList();
    }

    private String removeSpecialCharacter(String str) {
        String resultStr = str;
        resultStr = StringUtils.replace(resultStr, "<b>", "");
        resultStr = StringUtils.replace(resultStr, "</b>", "");
        return resultStr;
    }
}
