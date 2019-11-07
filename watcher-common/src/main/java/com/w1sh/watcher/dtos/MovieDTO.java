package com.w1sh.watcher.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties (value = { "video", "adult", "backdrop_path", "original_language", "title",
"belongs_to_collection", "budget", "homepage", "imdb_id", "production_companies", "production_countries",
"revenue", "spoken_languages", "status", "tagline"})
public class MovieDTO {

    @JsonProperty(value = "original_title")
    private String title;

    private String overview;

    private Integer id;

    private Double popularity;

    @JsonProperty(value = "poster_path")
    private String posterPath;

    private List<Integer> genres_ids;

    private List<GenreDTO> genres;

    @JsonProperty(value = "release_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @JsonProperty(value = "vote_count")
    private Integer voteCount;

    @JsonProperty(value = "vote_average")
    private Double voteAverage;

    private Integer runtime;

}
