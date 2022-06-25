package ru.khairullin.films.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmFilterDto {

    List<Integer> countries;
    List<Integer> genres;
    Order order;
    Type type;
    Integer ratingTo;
    Integer ratingFrom;
    Integer yearFrom;
    Integer yearTo;
    String imdb;
    String keyword;
    Integer page;

}
