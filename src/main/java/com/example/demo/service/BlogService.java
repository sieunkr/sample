package com.example.demo.service;

import com.example.demo.service.dto.BlogDTO;
import com.example.demo.repository.BlogRepository;
import com.example.demo.service.dto.BlogGroup;
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

        BlogGroup blogGroup = new BlogGroup(blogRepository.findByQuery(query).getItems().stream()
                .map(b -> BlogDTO.builder()
                        .title(b.getTitle())
                        .blogger(b.getBloggername())
                        .description(b.getDescription())
                        .postdate(b.getPostdate())
                        .link(b.getLink())
                        .build())
                .collect(Collectors.toList()));

        return blogGroup.getList();
    }
}