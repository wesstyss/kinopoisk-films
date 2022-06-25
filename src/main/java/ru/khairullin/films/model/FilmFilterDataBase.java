package ru.khairullin.films.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmFilterDataBase {

    private List<Long> kinopoiskId;
    private List<String> nameRu;
    private List<Integer> year;
    private List<Double> ratingKinopoisk;
    private List<String> description;

}
