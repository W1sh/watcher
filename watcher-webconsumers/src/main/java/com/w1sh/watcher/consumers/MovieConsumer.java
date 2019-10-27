package com.w1sh.watcher.consumers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.w1sh.watcher.HttpClientConnection;
import com.w1sh.watcher.deserializers.MovieDTODeserializer;
import com.w1sh.watcher.dto.MovieDTO;
import com.w1sh.watcher.responses.Response;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.json.JsonParser;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MovieConsumer {

    private final HttpClientConnection connection;

    public MovieConsumer(HttpClientConnection connection) {
        this.connection = connection;
    }

    public MovieDTO findByTitle(String title){
        String json = connection.get(HttpClientConnection.TMDB_SEARCH_MOVIE);

        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(MovieDTO.class, new MovieDTODeserializer());
        mapper.registerModule(module);

        Response<MovieDTO> movieDTOResponse = null;
        try {
            movieDTOResponse = mapper.readValue(json, new TypeReference<Response<MovieDTO>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movieDTOResponse.getResults().get(0);
    }
}
