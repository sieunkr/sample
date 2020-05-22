package com.example.demo.service.dto;

import java.util.List;

public class BlogGroup {

    private final List<BlogDTO> list;

    public BlogGroup(List<BlogDTO> list) {
        this.list = list;
    }

    public List<BlogDTO> getList() {
        return list;
    }
}
