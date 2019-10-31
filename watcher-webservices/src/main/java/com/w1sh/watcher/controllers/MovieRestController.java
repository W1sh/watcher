package com.w1sh.watcher.controllers;

import com.w1sh.watcher.dtos.MovieDTO;
import com.w1sh.watcher.services.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequestMapping("/movies")
public class MovieRestController {

    private static final Logger logger = LoggerFactory.getLogger(MovieRestController.class);
    private final MovieService movieService;

    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping()
    public List<MovieDTO> findAll(){
        logger.info("Received request to find all movies");
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public MovieDTO findOne(@PathVariable @Min(1) Integer id){
        logger.info("Received request to find movie with id {}", id);
        return movieService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDTO create(@RequestBody MovieDTO resource) {
        return null;
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDTO update(@PathVariable( "id" ) Long id, @RequestBody MovieDTO resource) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        /**/
    }
}
