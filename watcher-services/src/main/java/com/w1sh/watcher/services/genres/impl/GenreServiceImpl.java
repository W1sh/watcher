package com.w1sh.watcher.services.genres.impl;

import com.w1sh.watcher.dtos.GenreDTO;
import com.w1sh.watcher.model.entities.Genre;
import com.w1sh.watcher.repos.GenreRepository;
import com.w1sh.watcher.services.genres.GenreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public void saveAll(List<GenreDTO> genreDTOs) {
        log.info("Saving genres to the database");
        List<Genre> genres = genreDTOs.stream()
                .distinct()
                .filter(Predicate.not(this::exists))
                .map(genreDTO -> modelMapper.map(genreDTO, Genre.class))
                .collect(Collectors.toList());
        genreRepository.saveAll(genres);
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRED)
    public Boolean exists(GenreDTO genreDTO) {
        Genre genre = modelMapper.map(genreDTO, Genre.class);
        return genreRepository.exists(Example.of(genre, ExampleMatcher.matching()
                .withIgnorePaths("id", "movies")
                .withMatcher("value", ExampleMatcher.GenericPropertyMatcher::contains)));
    }

    @Override
    @Transactional(Transactional.TxType.NEVER)
    public List<GenreDTO> findAll() {
        log.info("Retrieving all genres from database");
        List<Genre> genres = genreRepository.findAll();
        log.info("Found {} genres", genres);
        return genres.stream()
                .map(genre -> modelMapper.map(genre, GenreDTO.class))
                .collect(Collectors.toList());
    }
}
