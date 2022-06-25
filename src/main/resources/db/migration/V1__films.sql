drop table if exists films;
create table films(
    id integer not null auto_increment,
    kinopoisk_id integer,
    name_ru varchar(255),
    year integer,
    kinopoisk_rating double,
    discription varchar(3000),
    primary key(id)
)