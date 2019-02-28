package main;

import util.PrintFormatting;
import util.WrappedReader;

import java.util.ArrayList;
import java.util.Arrays;

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
            PrintFormatting.print(photos);
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
}


