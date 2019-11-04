package com.w1sh.watcher.services.impl;

import com.w1sh.watcher.clients.MovieClient;
import com.w1sh.watcher.dtos.MovieDTO;
import com.w1sh.watcher.entities.Movie;
import com.w1sh.watcher.repos.MovieRepository;
import com.w1sh.watcher.services.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieClient movieClient;
    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;

    @Override
    @Transactional(Transactional.TxType.NEVER)
    public List<MovieDTO> findAll() {
        log.info("Retrieving all movies from database");
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(Transactional.TxType.NEVER)
    public MovieDTO findById(Integer id) {
        log.info("Retrieving movie with id {}", id);
        Movie movie = movieRepository.findById(id).orElseThrow();
        return modelMapper.map(movie, MovieDTO.class);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public List<MovieDTO> findByTitle(String title) {
        try {
            log.info("Retrieving movies with title containing \"{}\"", title);
            List<MovieDTO> movies = movieClient.findByTitle(title);
            Movie movie = modelMapper.map(movies.get(0), Movie.class);
            movieRepository.save(movie);
            return movies;
        } catch (Exception e){
            log.error("Failed to retrieve movies", e);
            return new ArrayList<>();
        }
    }
}
