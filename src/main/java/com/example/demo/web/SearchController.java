package com.example.demo.web;

import com.example.demo.service.BlogService;
import com.example.demo.service.MovieService;
import com.example.demo.service.dto.BlogDTO;
import com.example.demo.service.dto.MovieDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SearchController {

    private final BlogService blogService;
    private final MovieService movieService;

    @GetMapping("/blog")
    public List<BlogDTO> searchBlogByQuery(@RequestParam(name = "query") String query){

        return blogService.findByQuery(query);
    }

    @GetMapping("/movie")
    public List<MovieDTO> searchMovieByQuery(@RequestParam(name = "query") String query){

        return movieService.findByQuery(query);
    }
}
