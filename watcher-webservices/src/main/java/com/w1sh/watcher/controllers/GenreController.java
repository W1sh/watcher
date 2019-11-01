package com.w1sh.watcher.controllers;

import com.w1sh.watcher.dtos.GenreDTO;
import com.w1sh.watcher.services.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping ("/genres")
public class GenreController {

    private static final Logger logger = LoggerFactory.getLogger(GenreController.class);
    private final GenreService genreService;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping ()
    public List<GenreDTO> findAll(){
        logger.info("Received request to find all genres");
        return genreService.findAll();
    }
}
