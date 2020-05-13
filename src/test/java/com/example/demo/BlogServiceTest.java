package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
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

        ResponseBlog responseBlog = new ResponseBlog();
        responseBlog.setItems(Arrays.asList(
                getStubItem(title ,"http://naver..",blogger ,"블로그설명"),
                getStubItem("anotherTitle" ,"http://naver..",blogger ,"블로그설명"))
        );
        responseBlog.setTotal(2);
        Mockito.when(blogRepository.findByQuery(any())).thenReturn(responseBlog);

        //when
        List<BlogDTO> actualList = blogService.findByQuery(query);

        //then
        assertThat(actualList).hasSize(2);
        assertEquals(title, actualList.get(0).getTitle());
    }

    ResponseBlog.naverDocument getStubItem(String title, String link, String blogger, String description) {

        ResponseBlog.naverDocument item = new ResponseBlog.naverDocument();
        item.setTitle(title);
        item.setLink(link);
        item.setBloggername(blogger);
        item.setDescription(description);
        return item;
    }

}