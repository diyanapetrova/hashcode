package main;

import util.WrappedReader;

import java.util.ArrayList;

public class Main {

    public static final ArrayList<String> FILES = new ArrayList<>();

    public static void main(String[] args) {
        FILES.add("");
        FILES.add("");
        FILES.add("");

        ArrayList<String> lines;
        for (String name: FILES) {
            lines = WrappedReader.readFileLines(name);
            repeatForFile(lines);
        }
    }

    public static void repeatForFile(final ArrayList<String> lines) {

    }
}
