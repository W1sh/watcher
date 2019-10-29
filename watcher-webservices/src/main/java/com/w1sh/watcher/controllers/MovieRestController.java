package com.w1sh.watcher.controllers;

import com.w1sh.watcher.dto.MovieDTO;
import com.w1sh.watcher.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MovieRestController {

    private final MovieService movieService;

    public MovieRestController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping()
    public List<MovieDTO> findAll(){
        return movieService.findAll();
    }

    @GetMapping("/{id}")
    public MovieDTO findOne(@PathVariable Integer id){
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
