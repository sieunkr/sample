package com.example.demo.service;

import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.response.ResponseBlog;
import com.example.demo.service.BlogService;
import com.example.demo.service.dto.BlogDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = BlogService.class)
class BlogServiceTest {

    @Autowired
    private BlogService blogService;

    @MockBean
    private BlogRepository blogRepository;

    @Test
    @DisplayName("블로그 조회 시 데이터 매핑")
    void givenNormal_whenFindByQuery_thenOk() {

        //given
        String query = "test";
        String title = "블로그주제";
        String blogger = "eddy";

        ResponseBlog responseBlog = ResponseBlog.builder()
                .total(2)
                .items(Arrays.asList(
                        getStubItem(title, "http://naver..", blogger, "블로그설명"),
                        getStubItem("anotherTitle", "http://naver..", blogger, "블로그설명")))
                .build();

        Mockito.when(blogRepository.findByQuery(any())).thenReturn(responseBlog);

        //when
        List<BlogDTO> actualList = blogService.findByQuery(query);

        //then
        assertThat(actualList).hasSize(2);
        assertEquals(title, actualList.get(0).getTitle());
    }

    ResponseBlog.naverDocument getStubItem(String title, String link, String blogger, String description) {

        return ResponseBlog.naverDocument.builder()
                .title(title)
                .link(link)
                .bloggername(blogger)
                .description(description)
                .build();
    }

}