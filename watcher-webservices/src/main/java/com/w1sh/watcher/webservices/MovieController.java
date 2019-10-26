package com.w1sh.watcher.webservices;

import com.w1sh.watcher.entities.Movie;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    @CrossOrigin
    @GetMapping
    public List<Movie> findAll(){
        return new ArrayList<Movie>();
    }
}
