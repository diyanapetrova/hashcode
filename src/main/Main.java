package main;

import util.PrintFormatting;
import util.WrappedReader;

import java.util.ArrayList;

public class Main {


    public static final ArrayList<String> FILES = new ArrayList<>();
    public static int N;

    public static void main(String[] args) {


        FILES.add("files/a_example.txt");
        FILES.add("files/b_lovely_landscapes.txt");
        FILES.add("files/c_memorable_moments.txt");
        FILES.add("files/d_pet_pictures.txt");
        FILES.add("files/e_shiny_selfies.txt");

        ArrayList<String> lines;
        for (String name : FILES) {
            lines = WrappedReader.readFileLines(name);

            N = Integer.parseInt(lines.get(0));
            ArrayList<Photo> photos = new ArrayList<>();

            for (int i = 1; i < lines.size(); i++) {

                String[] par = lines.get(i).split(" ");

                boolean h = false;
                if (par[0].equals("H")) {
                    h = true;
                }

                int tagsNum = Integer.parseInt(par[1]);
                ArrayList<String> tags = new ArrayList<>();

                for (int j = 2; i < par.length; i++) {
                    tags.add(par[j]);
                }
                Photo p = new Photo(i - 1, h, tagsNum, tags);
                photos.add(p);
                PrintFormatting.print(p);
            }
        }


    }

}


