package main;

import util.PrintFormatting;
import util.WrappedReader;

import java.util.*;

public class Main {

    public static final ArrayList<String> FILES = new ArrayList<>();
    public static int N;

    static {
        FILES.add("files/a_example.txt");
//        FILES.add("files/b_lovely_landscapes.txt");
//        FILES.add("files/c_memorable_moments.txt");
//        FILES.add("files/d_pet_pictures.txt");
//        FILES.add("files/e_shiny_selfies.txt");
    }

    public static void main(String[] args) {
        ArrayList<String> lines;
        for (String name : FILES) {
            lines = WrappedReader.readFileLines(name);
            N = Integer.parseInt(lines.get(0));
            ArrayList<Photo> photos = getSample(lines);
            photos = clearSingleTag(photos);
            LinkedList<Slide> slides = toSlides(photos);
            Collections.sort(slides);
            Collections.reverse(slides);
            PrintFormatting.print(slides);
            // TODO: 28/02/2019 All the code
        }
    }

    private static ArrayList<Photo> getSample(ArrayList<String> lines) {
        ArrayList<Photo> photos = new ArrayList<>();
        for (int i = 1; i < lines.size(); i++) {

            String[] param = lines.get(i).split(" ");

            boolean h = false;
            if (param[0].equals("H")) {
                h = true;
            }

            int tagsNum = Integer.parseInt(param[1]);
            ArrayList<String> tags = new ArrayList<>(Arrays.asList(param).subList(2, param.length));
            Photo p = new Photo(i - 1, h, tagsNum, tags);
            photos.add(p);
        }
        return photos;
    }

    private static ArrayList<Photo> clearSingleTag(ArrayList<Photo> inputPhotos) {
        ArrayList<Photo> filtered = new ArrayList<>();
        for (Photo inputPhoto : inputPhotos) {
            if (!inputPhoto.isHorizontal() || inputPhoto.numTags != 1) {
                filtered.add(inputPhoto);
            }
        }
        return filtered;
    }

    private static LinkedList<Slide> toSlides(ArrayList<Photo> photos) {
        LinkedList<Photo> horizontal = new LinkedList<>();
        LinkedList<Photo> vertical = new LinkedList<>();
        for (Photo photo : photos) {
            if (photo.isHorizontal()) {
                horizontal.add(photo);
            } else {
                vertical.add(photo);
            }
        }
        LinkedList<Slide> slides = new LinkedList<>();
        slides.addAll(horizontalToSlide(horizontal));
        slides.addAll(verticalToSlide(vertical));
        return slides;
    }

    private static LinkedList<Slide> horizontalToSlide(List<Photo> horizontal) {
        LinkedList<Slide> slides = new LinkedList<>();
        horizontal.forEach(photo -> slides.add(new Slide(photo)));
        return slides;
    }

    private static LinkedList<Slide> verticalToSlide(List<Photo> vertical) {
        LinkedList<Slide> slides = new LinkedList<>();
        for (int i = 0; i < vertical.size() - 1; i += 2) {
            Slide s = new Slide(vertical.get(i), vertical.get(i + 1));
            slides.add(s);
        }
        return slides;
    }
}


