package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class Slide implements Comparable {

    List<Photo> photos = new ArrayList<>();
    List<String> tags = new ArrayList<>();

    public Slide(Photo photo) {
        photos.add(photo);
        tags.addAll(photo.tags);
    }

    public Slide(Photo p1, Photo p2) {
        photos.add(p1);
        photos.add(p2);
        tags.addAll(p1.tags);
        tags.addAll(p2.tags);
    }

    public Photo addBest(Collection<Photo> others) {
        Photo p1 = photos.get(0);
        Photo p2 = p1.getBestPair(others);
        photos.add(p2);
        tags.addAll(p2.tags);
        return p2;
    }
    @Override
    public int compareTo( Object o) {

        return Integer.compare(tags.size(), ((Slide) o).tags.size());

    }

    @Override
    public String toString() {
        return "Slide{" +
                "photos=" + photos +
                ", tags=" + tags +
                '}';
    }

    public String asOutput() {
        StringBuilder sb = new StringBuilder();
        photos.forEach(photo -> {
            sb.append(photo.id);
            sb.append(" ");
        });
        return sb.toString().trim();
    }

    public int scoreWith(Slide other) {
        HashSet<String> intersection = new HashSet<>(this.tags);
        intersection.retainAll(other.tags);
        HashSet<String> left = new HashSet<>(this.tags);
        left.removeAll(intersection);
        HashSet<String> right = new HashSet<>(other.tags);
        right.removeAll(intersection);
        int minSizes = Math.min(left.size(), right.size());
        return Math.min(minSizes, intersection.size());
    }

    public Slide getBestMatch(Collection<Slide> slides) {
        int bestScore = -1;
        Slide bestMatch = null;
        for (Slide slide : slides) {
            int score = this.scoreWith(slide);
            if (score > bestScore) {
                bestScore = score;
                bestMatch = slide;
                // Stop if there is no more potential.
                if (bestScore == this.tags.size() / 2) {
                    break;
                }
            }
        }
        return bestMatch;
    }
}
