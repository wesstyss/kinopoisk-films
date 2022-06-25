package ru.khairullin.films.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.khairullin.films.mapper.FilmMapper;
import ru.khairullin.films.model.FilmFilterDataBase;
import ru.khairullin.films.model.FilmFilterDto;
import ru.khairullin.films.model.FilmModel;
import ru.khairullin.films.repository.FilmRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KinopoiskService {

    private final KinopoiskClient kinopoiskClient;
    private final FilmMapper filmMapper;
    private final FilmRepository filmRepository;

    @PersistenceContext
    private final EntityManager entityManager;
    

    public List<FilmModel> findAll(FilmFilterDto filter) {
        return kinopoiskClient.getFilms(filter).stream()
                .map(filmMapper::toFilm).collect(Collectors.toList());
    }
    public void saveToDataBase(FilmModel filmModel){
        if(!filmRepository.existsByKinopoiskId(filmModel.getKinopoiskId())){
            filmRepository.save(filmModel);
        }
    }
    public List<FilmModel> addFilm(FilmFilterDto filter) {
        return kinopoiskClient.getFilms(filter).stream()
                .map(filmMapper::toFilm)
                .peek(this::saveToDataBase)
                .collect(Collectors.toList());
    }
    public List<FilmModel> getFilmsFromDb(FilmFilterDataBase filter) {
        if(filter == null) {
            return null;
        }
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<FilmModel> cq = cb.createQuery(FilmModel.class);

        Root<FilmModel> films = cq.from(FilmModel.class);

        Path<Integer> kinopoiskId = films.get("kinopoiskId");

        Path<String> nameRu = films.get("nameRu");

        Path<Integer> year = films.get("year");

        Path<Integer> ratingKinopoisk = films.get("ratingKinopoisk");

        Path<String> description = films.get("description");



        List<Predicate> predicateList = new ArrayList<>();

        if(filter.getKinopoiskId() != null) {
            predicateList.add(cb.in(kinopoiskId));
        }
        if(filter.getNameRu() != null) {
            predicateList.add(cb.in(nameRu));
        }

        if (filter.getYear() != null) {
            predicateList.add(cb.in(year));
        }

        if (filter.getRatingKinopoisk() != null) {
            predicateList.add(cb.in(ratingKinopoisk));
        }

        if (filter.getDescription() != null) {
            predicateList.add(cb.in(description));
        }


        cq.where(predicateList.toArray(new Predicate[0]));
        TypedQuery<FilmModel> query = entityManager.createQuery(cq);

        return query.getResultList();
    }
}
