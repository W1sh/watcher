package com.w1sh.watcher.clients;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.w1sh.watcher.connections.TmdbConnection;
import com.w1sh.watcher.dtos.MovieDTO;
import com.w1sh.watcher.responses.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieConsumer {

    private final TmdbConnection connection;
    private final ObjectMapper objectMapper;

    public MovieConsumer(TmdbConnection connection, ObjectMapper objectMapper) {
        this.connection = connection;
        this.objectMapper = objectMapper;
    }

    public List<MovieDTO> findByTitle(String title){
        String json = connection.searchMoviesByTitle(title);

        try {
            Response<MovieDTO> movieDTOResponse = objectMapper.readValue(
                    json, new TypeReference<Response<MovieDTO>>() {});
            return movieDTOResponse.getResults();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
