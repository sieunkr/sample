package com.example.demo.service;

import com.example.demo.repository.MovieRepository;
import com.example.demo.repository.response.ResponseMovie;
import com.example.demo.service.dto.MovieDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.StringUtils;

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

    @Test
    @DisplayName("평점이 0인 데이터는 제외하는지")
    void user_ratings_exclude_zero() {

        //given
        float expectedMovieSize = 3;
        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
        Mockito.when(movieRepository.findByQuery(any())).thenReturn(getStubMovieList());
        movieService = new MovieService(movieRepository);

        //when
        List<MovieDTO> actualList = movieService.findByQuery("쿼리");

        //then
        assertEquals(expectedMovieSize, actualList.size());

    }

    @Test
    @DisplayName("<b>, </b> 제거 잘 하는지")
    void remove_special_characters_when_mapping_title() {

        //given
        float expectedSpecialCharacterCount = 0;
        MovieRepository movieRepository = Mockito.mock(MovieRepository.class);
        Mockito.when(movieRepository.findByQuery(any())).thenReturn(getStubMovieList());
        movieService = new MovieService(movieRepository);

        //when
        List<MovieDTO> actualList = movieService.findByQuery("쿼리");

        //then
        assertEquals(expectedSpecialCharacterCount,
                StringUtils.countOccurrencesOf(actualList.stream().findFirst().get().getTitle(), "<b>"));

        assertEquals(expectedSpecialCharacterCount,
                StringUtils.countOccurrencesOf(actualList.stream().findFirst().get().getTitle(), "</b>"));

    }


    private ResponseMovie getStubMovieList() {

        List<ResponseMovie.Item> items = Arrays.asList(
                ResponseMovie.Item.builder().title("<b>영화1</b> 제목").actor("배우1").userRating(9.3f).build(),
                ResponseMovie.Item.builder().title("<b>영화2</b> 제목").actor("배우2").userRating(9.7f).build(),
                ResponseMovie.Item.builder().title("<b>영화3</b> 제목").actor("배우3").userRating(0.0f).build(),
                ResponseMovie.Item.builder().title("<b>영화4</b> 제목").actor("배우4").userRating(7.5f).build()
        );

        return ResponseMovie.builder()
                .items(items)
                .build();
    }
}