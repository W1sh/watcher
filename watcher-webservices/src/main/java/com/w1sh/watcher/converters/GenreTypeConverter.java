package com.w1sh.watcher.converters;

import com.w1sh.watcher.GenreType;
import org.springframework.core.convert.converter.Converter;

public class GenreTypeConverter implements Converter<String, GenreType> {

    @Override
    public GenreType convert(String s) {
        return GenreType.fromString(s);
    }
}
