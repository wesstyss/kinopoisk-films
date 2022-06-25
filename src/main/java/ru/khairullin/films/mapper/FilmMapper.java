package ru.khairullin.films.mapper;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import ru.khairullin.films.model.FilmModel;
import ru.khairullin.films.model.ModelFromKino;

@Mapper(componentModel = "spring")
@Component
public interface FilmMapper {
    FilmModel toFilm(ModelFromKino modelFromKino);
}
