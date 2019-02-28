package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

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
                "id=" + id +
                ", horizontal=" + horizontal +
                ", numTags=" + numTags +
                ", tags=" + tags +
                '}';
    }

    private int intersectionSize(Photo other) {
        HashSet<String> intersection = new HashSet<>(this.tags);
        intersection.retainAll(other.tags);
        return intersection.size();
    }

    public Photo getBestPair(Collection<Photo> photos) {
        Photo bestPair = null;
        int minIntersection = Integer.MAX_VALUE;
        for (Photo photo : photos) {
            int score = intersectionSize(photo);
            if (score < minIntersection) {
                minIntersection = score;
                bestPair = photo;
                if (minIntersection == 0) {
                    break;
                }
            }
        }
        return bestPair;
    }
}
