package com.w1sh.watcher.services.movies.impl;

import com.w1sh.watcher.dtos.MovieDTO;
import com.w1sh.watcher.model.entities.Movie;
import com.w1sh.watcher.model.enums.Status;
import com.w1sh.watcher.repos.MovieRepository;
import com.w1sh.watcher.services.genres.GenreRequestService;
import com.w1sh.watcher.services.genres.GenreService;
import com.w1sh.watcher.services.movies.MovieRequestService;
import com.w1sh.watcher.services.movies.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;
    private final MovieRequestService movieRequestService;

    @Override
    @Transactional (Transactional.TxType.REQUIRED)
    public void saveAll(List<MovieDTO> movieDTOs) {
        movieRepository.saveAll(movieDTOs.stream()
                .filter(Objects::nonNull)
                .map(m -> movieRequestService.find(m.getId()))
                .map(m -> modelMapper.map(m, Movie.class))
                .peek(m -> m.setStatus(Status.PLANNED))
                .peek(m -> m.setDate(new Date()))
                .peek(m -> m.setGenres(new HashSet<>()))
                .collect(Collectors.toList()));
    }
}
