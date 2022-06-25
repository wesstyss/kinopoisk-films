package ru.khairullin.films.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.khairullin.films.model.FilmFilterDataBase;
import ru.khairullin.films.model.FilmFilterDto;
import ru.khairullin.films.model.FilmModel;
import ru.khairullin.films.service.KinopoiskService;

import java.util.List;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class FilmController {

    private final KinopoiskService kinopoiskService;

    @GetMapping("/")
    public List<FilmModel> getFilm(FilmFilterDto filmFilterDto){
        return kinopoiskService.findAll(filmFilterDto);
    }
    @PostMapping("/")
    public List<FilmModel> saveFilm(FilmFilterDto filmFilterDto) {
        return kinopoiskService.addFilm(filmFilterDto);
    }

    @GetMapping("/fromdb")
    public List<FilmModel> getFilmsFromDb(FilmFilterDataBase filmFilterDataBase) {
        return kinopoiskService.getFilmsFromDb(filmFilterDataBase);
    }


}
