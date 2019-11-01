package com.w1sh.watcher.dtos;

import java.util.Map;
import java.util.Optional;

public class QueryParamsDTO {

    private String search;
    private Integer limit;
    private String sort;

    public QueryParamsDTO(Map<String, String> params) {
        this.search = Optional.ofNullable(params.get("search")).orElse("");
        this.limit = Optional.ofNullable(params.get("limit")).map(Integer::parseInt).orElse(1);
        this.sort = Optional.ofNullable(params.get("sort")).orElse("desc");
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
