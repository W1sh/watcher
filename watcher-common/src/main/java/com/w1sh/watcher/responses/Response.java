package com.w1sh.watcher.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Response<T> {

    private Integer page;
    @JsonProperty(value = "total_pages")
    private Integer totalPages;
    @JsonProperty(value = "total_results")
    private Integer totalResults;

    private List<T> results;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
