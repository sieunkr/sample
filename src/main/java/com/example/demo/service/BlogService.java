package com.example.demo.service;

import com.example.demo.service.dto.BlogDTO;
import com.example.demo.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public List<BlogDTO> findByQuery(String query) {

        return blogRepository.findByQuery(query).getItems().stream()
                .map(b -> BlogDTO.builder()
                        .title(b.getTitle())
                        .link(b.getLink())
                        .description(b.getDescription())
                        .postdate(b.getPostdate())
                        .blogger(b.getBloggername())
                        .build()
                ).collect(Collectors.toList());

    }
}