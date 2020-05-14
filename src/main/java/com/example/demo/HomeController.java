package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class HomeController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private MovieService movieService;

    @GetMapping("/blog")
    public List<BlogDTO> searchBlogByQuery(@RequestParam(name = "query") String query){

        return blogService.findByQuery(query);
    }

    @GetMapping("/movie")
    public List<MovieDTO> searchMovieByQuery() {

        return movieService.findByQuery("반지의제왕");
    }
}