package com.example.demo.repository;

import com.example.demo.config.NaverProperties;
import com.example.demo.exception.ExceptionMessage;
import com.example.demo.exception.OpenApiRuntimeException;
import com.example.demo.repository.response.ResponseBlog;
import com.example.demo.repository.response.ResponseMovie;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Repository
@RequiredArgsConstructor
public class BlogRepository {

    private final NaverProperties naverProperties;
    private final RestTemplate naverRestTemplate;

    public ResponseBlog findByQuery(String query) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", naverProperties.getClientId());
        httpHeaders.add("X-Naver-Client-Secret", naverProperties.getClientSecret());

        String url = naverProperties.getBlogUrl() + "?query=" + query;
        
        //TODO: 중복 코드 개선
        try {

            return naverRestTemplate.exchange(url, HttpMethod.GET, new HttpEntity(httpHeaders), ResponseBlog.class).getBody();

        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
                throw new OpenApiRuntimeException(ExceptionMessage.NAVER_API_UNAUTHORIZED);
            } else {
                throw new OpenApiRuntimeException(ExceptionMessage.NAVER_API_ERROR);
            }
        } catch (Exception ex) {
            throw new OpenApiRuntimeException(ExceptionMessage.NAVER_API_ERROR);
        }
    }
}