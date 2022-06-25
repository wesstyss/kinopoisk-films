package ru.khairullin.films.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import ru.khairullin.films.model.FilmModel;

@Repository
@EnableJpaRepositories
public interface FilmRepository extends JpaRepository<FilmModel, Long> {

    boolean existsByKinopoiskId(Long kinopoiskId);

}
