package ru.khairullin.films.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ModelFromKino {
    private Long kinopoiskId;
    private String nameRu;
    private Integer year;
    private Double ratingKinopoisk;
    private String description;
}
