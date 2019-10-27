package com.w1sh.watcher.webservices;

import com.w1sh.watcher.HttpClientConnection;
import com.w1sh.watcher.entities.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final HttpClientConnection connection;

    public MovieController(HttpClientConnection connection) {
        this.connection = connection;
    }

    @CrossOrigin
    @GetMapping
    public List<Movie> findAll(){
        connection.get(HttpClientConnection.TMDB_SEARCH_MOVIE);
        return new ArrayList<Movie>();
    }
}
