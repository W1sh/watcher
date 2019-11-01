package com.w1sh.watcher;

public enum GenreType {
    ALL, MOVIE, TV;

    public static GenreType fromString(String string){
        for(GenreType genreType : values()){
            if (genreType.toString().equalsIgnoreCase(string)) return genreType;
        }
        return ALL;
    }
}
