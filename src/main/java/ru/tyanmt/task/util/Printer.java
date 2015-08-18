package ru.tyanmt.task.util;

import ru.tyanmt.task.common.Cube;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mityan on 03.08.2015.
 */
public class Printer {
    public static void print(Cube cube) {
        List<String> asciiCube = new ArrayList<>();
        addFirstThreeFaces(cube, asciiCube);
        addOtherThreeFaces(cube, asciiCube);
        printFaces(asciiCube);
    }

    private static void printFaces(List<String> asciiCube) {
        for (String s : asciiCube) {
            System.out.println(s);
        }
    }

    private static void addOtherThreeFaces(Cube cube, List<String> asciiCube) {
        for (int i = 4; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                StringBuilder line = new StringBuilder();
                line.append("     ");
                for (int k = 0; k < 5; k++) {
                    line.append(cube.getFace(i).getFace()[j][k] == i ? "o" : " ");
                }
                asciiCube.add(line.toString());
            }
        }
    }

    private static void addFirstThreeFaces(Cube cube, List<String> asciiCube) {
        for (int i = 0; i < 5; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 1; j < 4; j++) {
                for (int k = 0; k < 5; k++) {
                    line.append(cube.getFace(j).getFace()[i][k] == j ? "o" : " ");
                }
            }
            asciiCube.add(line.toString());
        }
    }
}
