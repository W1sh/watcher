package com.w1sh.watcher.services.movies.impl;

import com.w1sh.watcher.dtos.MovieDTO;
import com.w1sh.watcher.repos.MovieRepository;
import com.w1sh.watcher.services.movies.MovieService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Slf4j
@AllArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final ModelMapper modelMapper;
    private final MovieRepository movieRepository;

    @Override
    @Transactional (Transactional.TxType.REQUIRED)
    public void save(MovieDTO movieDTO) {

    }
}
