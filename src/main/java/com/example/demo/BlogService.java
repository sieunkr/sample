package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    /*
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

     */

    public List<BlogDTO> findByQuery(String query) {

        List<BlogDTO> list = new ArrayList<>();
        blogRepository.findByQuery(query).getItems().forEach(
                b -> list.add(BlogDTO.builder()
                        .title(b.title)
                        .link(b.link)
                        .description(b.description)
                        .postdate(b.postdate)
                        .blogger(b.bloggername)
                        .build())
        );

        return list;

        /*
        return blogRepository.findByQuery(query).getItems().stream()
                .map(b -> BlogDTO.builder()
                        .title(b.title)
                        .link(b.link)
                        .description(b.description)
                        .postdate(b.postdate)
                        .blogger(b.bloggername)
                        .build()
                ).collect(Collectors.toList());
        */
    }
}