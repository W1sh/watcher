package com.w1sh.watcher.services;

import com.w1sh.watcher.dto.MovieDTO;

public interface MovieService {

    MovieDTO findByTitle(String title);
}
