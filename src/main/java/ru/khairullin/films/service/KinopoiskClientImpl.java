package ru.khairullin.films.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.khairullin.films.model.FilmFilterDto;
import ru.khairullin.films.model.ModelFromKino;
import ru.khairullin.films.model.TotalResponse;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class KinopoiskClientImpl implements KinopoiskClient {

    HttpHeaders httpHeaders = new HttpHeaders();
    private final RestTemplate restTemplate;
    final String URL = "https://kinopoiskapiunofficial.tech/api/v2.2/films";

    @Override
    public List<ModelFromKino> getFilms(FilmFilterDto filter) {

        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.set("X-API-KEY","9ee6d9bf-6443-47ec-8b61-de3d42d0bdae");

        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        ResponseEntity<TotalResponse> response =restTemplate.exchange(generateUrl(filter),
                HttpMethod.GET, httpEntity,TotalResponse.class);

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null &&
        response.getBody().getItems() != null){
            return response.getBody().getItems();
        }else{
            return null;
        }
    }
    @Override
    public String generateUrl(FilmFilterDto filter) {

        UriComponentsBuilder uriComponentsBuilder =UriComponentsBuilder.fromHttpUrl(URL);
        Optional<FilmFilterDto> optionalFilter = Optional.ofNullable(filter);

        optionalFilter.map(FilmFilterDto::getOrder)
                .ifPresent(sort-> uriComponentsBuilder.queryParam("order",sort));
        optionalFilter.map(FilmFilterDto::getType)
                .ifPresent(type -> uriComponentsBuilder.queryParam("type",type));
        optionalFilter.map(FilmFilterDto::getCountries)
                .ifPresent(countries -> uriComponentsBuilder.queryParam("type", countries.toString()));
        optionalFilter.map(FilmFilterDto::getGenres)
                .ifPresent(genres -> uriComponentsBuilder.queryParam("genres", genres.toString()));
        optionalFilter.map(FilmFilterDto::getCountries)
                .ifPresent(countries -> uriComponentsBuilder.queryParam("countries", countries.toString()));
        optionalFilter.map(FilmFilterDto::getRatingFrom)
                .ifPresent(ratingFrom -> uriComponentsBuilder.queryParam("ratingFrom", ratingFrom.toString()));
        optionalFilter.map(FilmFilterDto::getRatingTo)
                .ifPresent(ratingTo -> uriComponentsBuilder.queryParam("ratingTo", ratingTo.toString()));
        optionalFilter.map(FilmFilterDto::getYearFrom)
                .ifPresent(yearFrom -> uriComponentsBuilder.queryParam("yearFrom", yearFrom.toString()));
        optionalFilter.map(FilmFilterDto::getYearTo)
                .ifPresent(yearTo -> uriComponentsBuilder.queryParam("yearTo", yearTo.toString()));
        optionalFilter.map(FilmFilterDto::getPage)
                .ifPresent(page -> uriComponentsBuilder.queryParam("page", page.toString()));
        optionalFilter.map(FilmFilterDto::getKeyword)
                .ifPresent(keyword -> uriComponentsBuilder.queryParam("keyword", keyword));

        return uriComponentsBuilder.build().toUriString();
    }
}
