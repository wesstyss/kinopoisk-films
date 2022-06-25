package ru.khairullin.films.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "films")
public class FilmModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "kinopoisk_id")
    private long kinopoiskId;

    @Column(name = "name_ru")
    private String nameRu;

    @Column(name = "year")
    private int year;

    @Column(name = "kinopoisk_rating")
    private double ratingKinopoisk;

    @Column(name = "description")
    private String description;

}
