package com.w1sh.watcher.controllers;

import com.w1sh.watcher.dtos.GenreDTO;
import com.w1sh.watcher.services.GenreService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping ("/genres")
public class GenreRestController {

    private final GenreService genreService;

    public GenreRestController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping ()
    public List<GenreDTO> findAll(){
        return genreService.findAll();
    }
}
