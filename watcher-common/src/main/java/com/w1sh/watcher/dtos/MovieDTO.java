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
@JsonIgnoreProperties (value = { "video", "adult", "backdrop_path", "original_language", "title"})
public class MovieDTO {

    @JsonProperty(value = "original_title")
    private String title;

    private String overview;

    private Integer id;

    private Double popularity;

    @JsonProperty(value = "poster_path")
    private String posterPath;

    @JsonProperty(value = "genre_ids")
    private List<Integer> genres;

    @JsonProperty(value = "release_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @JsonProperty(value = "vote_count")
    private Integer voteCount;

    @JsonProperty(value = "vote_average")
    private Double voteAverage;

}
