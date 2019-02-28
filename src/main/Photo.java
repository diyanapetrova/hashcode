package main;

import java.util.ArrayList;

public class Photo {
    int id;
    boolean horizontal;
    int numTags;
    ArrayList<String> tags;

    public Photo(int id, boolean horizontal, int t, ArrayList<String> tags) {
        this.id = id;
        this.horizontal = horizontal;
        this.numTags = t;
        this.tags = tags;
    }

    boolean isHorizontal(){
        return horizontal;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "horizontal=" + horizontal +
                ", numTags=" + numTags +
                ", tags=" + tags +
                '}';
    }
}
