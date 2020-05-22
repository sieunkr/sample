package com.example.demo.service;

import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.response.ResponseMovie;
import com.example.demo.service.dto.MovieDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

/*
@ExtendWith(MockitoExtension.class) 선언하면, @Mock 어노테이션 사용 가능
 */
class MovieServiceTest {

    private MovieService movieService;

    @Test
    @DisplayName("평점 순으로 정렬이 잘 되는지")
    void arranged_well_in_user_ratings() {

        //given
        float expectedUserRanking = 9.7f;
        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
        Mockito.when(movieRepository.findByQuery(any())).thenReturn(getStubMovieList());
        movieService = new MovieService(movieRepository);

        //when
        List<MovieDTO> actualList = movieService.findByQuery("쿼리");

        //then
        assertEquals(expectedUserRanking, actualList.stream().findFirst().get().getUserRating());

    }

    private ResponseMovie getStubMovieList() {

        List<ResponseMovie.Item> items = Arrays.asList(
                ResponseMovie.Item.builder().title("영화1").actor("배우1").userRating(9.3f).build(),
                ResponseMovie.Item.builder().title("영화2").actor("배우2").userRating(9.7f).build(),
                ResponseMovie.Item.builder().title("영화3").actor("배우3").userRating(7.5f).build()
        );

        return ResponseMovie.builder()
                .items(items)
                .build();
    }
}