package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public List<BlogDTO> findByQuery(String query) {

        return blogRepository.findByQuery(query).getItems().stream()
                .map(b -> BlogDTO.builder()
                        .title(b.title)
                        .link(b.link)
                        .description(b.description)
                        .postdate(b.postdate)
                        .blogger(b.bloggername)
                        .build()
                ).collect(Collectors.toList());
    }
}