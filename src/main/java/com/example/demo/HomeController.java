package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class HomeController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<BlogDTO> searchBlogByQuery(@RequestParam(name = "query") String query){

        return blogService.findByQuery(query);
    }
}