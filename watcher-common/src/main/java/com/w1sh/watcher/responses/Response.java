package com.w1sh.watcher.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.w1sh.watcher.Interval;
import lombok.Data;

import java.util.List;

@Data
public class Response<T> {

    private Integer page;

    @JsonProperty(value = "total_pages")
    private Integer totalPages;

    @JsonProperty(value = "total_results")
    private Integer totalResults;

    private List<T> results;

    @JsonProperty(value = "dates")
    private Interval interval;

}
