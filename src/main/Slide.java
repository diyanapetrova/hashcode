package main;

import java.util.ArrayList;
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
}
