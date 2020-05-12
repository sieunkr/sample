package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class BlogRepository {

    @Value("${naver.openapi.blogUrl}")
    private String naverOpenApiUrl;

    @Value("${naver.openapi.clientId}")
    private String naverOpenApiClientId;

    @Value("${naver.openapi.clientSecret}")
    private String naverOpenApiClientSecret;

    public ResponseBlog findByQuery(String query) {

        //TODO: 코드 리팩토링
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", naverOpenApiClientId);
        httpHeaders.add("X-Naver-Client-Secret", naverOpenApiClientSecret);

        String url = naverOpenApiUrl + "?query=" + query;

        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(httpHeaders), ResponseBlog.class).getBody();
    }
}