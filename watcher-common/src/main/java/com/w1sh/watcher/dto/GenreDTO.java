package com.w1sh.watcher.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties(value = { "movies" })
public class GenreDTO {

    private Integer id;
    @JsonProperty(value = "name")
    private String value;
    private Set<MovieDTO> movies;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<MovieDTO> getMovies() {
        return movies;
    }

    public void setMovies(Set<MovieDTO> movies) {
        this.movies = movies;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDTO genreDTO = (GenreDTO) o;
        return value.equals(genreDTO.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
