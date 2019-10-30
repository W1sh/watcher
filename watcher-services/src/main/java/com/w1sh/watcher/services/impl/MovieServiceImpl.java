package com.w1sh.watcher.services.impl;

import com.w1sh.watcher.clients.MovieConsumer;
import com.w1sh.watcher.dtos.MovieDTO;
import com.w1sh.watcher.entities.Movie;
import com.w1sh.watcher.repos.MovieRepository;
import com.w1sh.watcher.services.MovieService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieServiceImpl.class);

    private final MovieConsumer movieConsumer;
    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieConsumer movieConsumer, ModelMapper modelMapper,
                            MovieRepository movieRepository) {
        this.movieConsumer = movieConsumer;
        this.modelMapper = modelMapper;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieDTO> findAll() {
        logger.info("Retrieving all movies from database");
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MovieDTO findById(Integer id) {
        logger.info("Retrieving movie with id {}", id);
        Movie movie = movieRepository.findById(id).orElseThrow();
        return modelMapper.map(movie, MovieDTO.class);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public List<MovieDTO> findByTitle(String title) {
        try {
            logger.info("Retrieving movies with title containing \"{}\"", title);
            List<MovieDTO> movies = movieConsumer.findByTitle(title);
            Movie movie = modelMapper.map(movies.get(0), Movie.class);
            movieRepository.save(movie);
            return movies;
        } catch (Exception e){
            logger.error("Failed to retrieve movies", e);
            return new ArrayList<>();
        }
    }
}
