package ru.khairullin.films.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TotalResponse {

    private Integer total;
    private Integer totalPages;
    private List<ModelFromKino> items;

}
