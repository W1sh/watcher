package com.w1sh.watcher.controllers;

import com.w1sh.watcher.dtos.GenreDTO;
import com.w1sh.watcher.services.GenreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping ("/genres")
@AllArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @GetMapping ()
    public List<GenreDTO> findAll(){
        log.info("Received request to find all genres");
        return genreService.findAll();
    }
}
