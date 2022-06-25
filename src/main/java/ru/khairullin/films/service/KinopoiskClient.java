package ru.khairullin.films.service;

import ru.khairullin.films.model.FilmFilterDto;
import ru.khairullin.films.model.FilmModel;
import ru.khairullin.films.model.ModelFromKino;

import java.util.List;

public interface KinopoiskClient {

    List<ModelFromKino> getFilms(FilmFilterDto filter);

    String generateUrl(FilmFilterDto filter);
}
