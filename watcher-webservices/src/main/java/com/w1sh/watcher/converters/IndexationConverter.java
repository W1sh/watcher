package com.w1sh.watcher.converters;

import com.w1sh.watcher.Indexation;
import org.springframework.core.convert.converter.Converter;

public class IndexationConverter implements Converter<String, Indexation> {

    @Override
    public Indexation convert(String s) {
        return Indexation.fromString(s);
    }
}
