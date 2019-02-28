package main;

import util.PrintFormatting;
import util.ProgressBar;
import util.WrappedReader;
import util.WrappedWriter;

import java.util.*;

public class Main {

    public static final ArrayList<String> FILES = new ArrayList<>();
    public static int N;

    static {
//        FILES.add("files/a_example.txt");
        FILES.add("files/b_lovely_landscapes.txt");
//        FILES.add("files/c_memorable_moments.txt");
//        FILES.add("files/d_pet_pictures.txt");
//        FILES.add("files/e_shiny_selfies.txt");
    }

    public static void main(String[] args) {
        ArrayList<String> lines;
        int i = 0;
        for (String name : FILES) {
            lines = WrappedReader.readFileLines(name);
            N = Integer.parseInt(lines.get(0));
            ArrayList<Photo> photos = getSample(lines);
            photos = clearSingleTag(photos);
            LinkedList<Slide> slides = toSlides(photos);
            Collections.sort(slides);
            Collections.reverse(slides);
            LinkedList<Slide> slideshow = createSlideshow(slides);
            // TODO: 28/02/2019 CODE
            WrappedWriter.saveToFile(asOutput(slideshow), "output" + i + ".txt");
            PrintFormatting.print("done with " + i);
            i++;
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

    private static String asOutput(LinkedList<Slide> slides) {
        StringBuilder sb = new StringBuilder();
        sb.append(slides.size());
        sb.append("\n");
        for (Slide slide : slides) {
            sb.append(slide.asOutput());
            sb.append("\n");
        }
        return sb.toString();
    }

    private static Slide getBestMatch(Slide prime, LinkedList<Slide> slides) {
        int bestScore = -1;
        Slide bestMatch = null;
        for (Slide slide : slides) {
            int score = prime.scoreWith(slide);
            if (score > bestScore) {
                bestScore = score;
                bestMatch = slide;
            }
        }
        return bestMatch;
    }

    private static LinkedList<Slide> createSlideshow(LinkedList<Slide> slides) {
        LinkedList<Slide> slideshow = new LinkedList<>();
        int totalWork = slides.size();
        // TODO: 28/02/2019 change poll method
        Slide prime = slides.pollFirst();
        slideshow.addLast(prime);
        while (!slides.isEmpty()) {
            Slide bestMatch = getBestMatch(prime, slides);
            slides.remove(bestMatch);
            slideshow.addLast(bestMatch);
            prime = bestMatch;
            String progressBar = ProgressBar.formatBar(slideshow.size(), totalWork);
            PrintFormatting.print(progressBar);
//            PrintFormatting.print(slides.size());
        }

        return slideshow;
    }
}


