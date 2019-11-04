package com.w1sh.watcher.constants;

public enum TmdbConstants {
    TMDB_API_BASE("https://api.themoviedb.org/3/"),
    METHOD_LIST("list"),
    METHOD_TV("tv"),
    METHOD_MOVIE("movie"),
    METHOD_GENRE("genre"),
    METHOD_SEARCH("search"),
    METHOD_POPULAR("popular"),
    METHOD_UPCOMING("upcoming"),
    METHOD_TOP_RATED("top_rated"),
    METHOD_NOW_PLAYING("now_playing");

    private String value;

    TmdbConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
