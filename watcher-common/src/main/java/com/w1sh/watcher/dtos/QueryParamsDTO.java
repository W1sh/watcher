package com.w1sh.watcher.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryParamsDTO {

    private String search;
    private Integer limit;
    private String sort;

    public QueryParamsDTO(Map<String, String> params) {
        this.search = Optional.ofNullable(params.get("search")).orElse("");
        this.limit = Optional.ofNullable(params.get("limit")).map(Integer::parseInt).orElse(1);
        this.sort = Optional.ofNullable(params.get("sort")).orElse("desc");
    }
}
