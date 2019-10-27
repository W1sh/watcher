package com.w1sh.watcher.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.w1sh.watcher.dto.MovieDTO;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MovieDTODeserializer extends JsonDeserializer<MovieDTO> {

    @Override
    public MovieDTO deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        MovieDTO movieDTO = new MovieDTO();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Integer popularity = Integer.parseInt(node.get("popularity").asText().replace(".", ""));
        Integer id = node.get("id").asInt();
        String originalTitle = node.get("original_title").asText();
        String overview = node.get("overview").asText();
        String posterPath = node.get("poster_path").asText();

        Date releaseDate = null;
        try {
            releaseDate = simpleDateFormat.parse(node.get("release_date").asText());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        movieDTO.setPopularity(popularity);
        movieDTO.setId(id);
        movieDTO.setOriginalTitle(originalTitle);
        movieDTO.setOverview(overview);
        movieDTO.setPosterPath(posterPath);
        movieDTO.setReleaseDate(releaseDate);
        //List<Integer> genres = node.get("genre_ids").;
        return movieDTO;
    }
}
