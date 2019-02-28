package main;

import java.util.List;

public class Slide implements Comparable {

    List<Photo> photos;
    List<String> tags;

    public Slide(List<Photo> photos, List<String> tags) {
        this.photos = photos;
        this.tags = tags;
    }


    @Override
    public int compareTo( Object o) {

        return Integer.compare(tags.size(), ((Slide) o).tags.size());

    }

    @Override
    public String toString() {
        return photos.toString();
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
