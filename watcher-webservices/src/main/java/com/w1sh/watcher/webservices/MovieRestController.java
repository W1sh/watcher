package com.w1sh.watcher.webservices;

import com.w1sh.watcher.dto.MovieDTO;
import com.w1sh.watcher.services.MovieService;
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
}
