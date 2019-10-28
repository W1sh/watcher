package com.w1sh.watcher.consumers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.w1sh.watcher.TmdbConnection;
import com.w1sh.watcher.entities.Genre;
import com.w1sh.watcher.responses.GenreResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class GenreConsumer {

    private final TmdbConnection connection;
    private final ObjectMapper objectMapper;

    public GenreConsumer(TmdbConnection connection, ObjectMapper objectMapper) {
        this.connection = connection;
        this.objectMapper = objectMapper;
    }

    public List<Genre> getAllMovieGenres(){
        String json = connection.getAllMovieGenres();

        try {
            return objectMapper.readValue(json, GenreResponse.class).getGenres();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Genre> getAllTVGenres(){
        String json = connection.getAllTVGenres();

        try {
            return objectMapper.readValue(json, GenreResponse.class).getGenres();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
