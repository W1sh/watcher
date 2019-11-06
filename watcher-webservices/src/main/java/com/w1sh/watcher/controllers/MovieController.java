package com.w1sh.watcher.controllers;

import com.w1sh.watcher.dtos.MovieDTO;
import com.w1sh.watcher.services.movies.MovieQueryService;
import com.w1sh.watcher.services.movies.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    private final MovieQueryService movieQueryService;
    private final MovieService movieService;

    @GetMapping()
    public List<MovieDTO> findAll(){
        log.info("Received request to find all movies");
        return movieQueryService.findAll();
    }

    @GetMapping("/{id}")
    public MovieDTO findOne(@PathVariable("id") @Min(value = 1, message = "Id must be greater than 1") Integer id){
        log.info("Received request to find movie with id {}", id);
        return movieQueryService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody List<MovieDTO> resources) {
        log.info("Received request to save movies");
        movieService.saveAll(resources);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MovieDTO update(@PathVariable("id") Long id, @RequestBody MovieDTO resource) {
        return null;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") Long id) {
        /**/
    }
}
