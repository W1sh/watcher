package com.w1sh.watcher.services.impl;

import com.w1sh.watcher.consumers.GenreConsumer;
import com.w1sh.watcher.consumers.MovieConsumer;
import com.w1sh.watcher.dto.MovieDTO;
import com.w1sh.watcher.entities.Genre;
import com.w1sh.watcher.entities.Movie;
import com.w1sh.watcher.repos.MovieRepository;
import com.w1sh.watcher.services.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieConsumer movieConsumer;
    private final GenreConsumer genreConsumer;
    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieConsumer movieConsumer, GenreConsumer genreConsumer,
                            ModelMapper modelMapper, MovieRepository movieRepository) {
        this.movieConsumer = movieConsumer;
        this.genreConsumer = genreConsumer;
        this.modelMapper = modelMapper;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieDTO> findAll() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MovieDTO findById(Integer id) {
        Movie movie = movieRepository.findById(id).orElseThrow();
        return modelMapper.map(movie, MovieDTO.class);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public List<MovieDTO> findByTitle(String title) {
        try {
            List<Genre> movieGenres = genreConsumer.getAllMovieGenres();
            List<Genre> tvGenres = genreConsumer.getAllTVGenres();
            List<MovieDTO> movies = movieConsumer.findByTitle(title);
            Movie movie = modelMapper.map(movies.get(0), Movie.class);
            movieRepository.save(movie);
            return movies;
        } catch (Exception e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
