package com.w1sh.watcher.controllers;

import com.w1sh.watcher.dtos.GenreDTO;
import com.w1sh.watcher.services.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/genres")
public class GenreRestController {

    private static final Logger logger = LoggerFactory.getLogger(GenreRestController.class);
    private final GenreService genreService;

    public GenreRestController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping ()
    public List<GenreDTO> findAll(){
        logger.info("Received request to find all genres");
        return genreService.findAll();
    }
}
