package com.w1sh.watcher;

public enum Indexation {
    UPCOMING, POPULAR, NOWPLAYING, TOPRATED;

    public static Indexation fromString(String string){
        for(Indexation i : values()){
            if(i.toString().equalsIgnoreCase(string)) return i;
        }
        return null;
    }
}
