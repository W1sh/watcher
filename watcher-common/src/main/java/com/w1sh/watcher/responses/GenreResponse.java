package com.w1sh.watcher.responses;

import com.w1sh.watcher.dtos.GenreDTO;
import lombok.Data;

import java.util.List;

@Data
public class GenreResponse {

    private List<GenreDTO> genres;

}
