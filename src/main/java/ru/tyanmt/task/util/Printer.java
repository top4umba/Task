package ru.tyanmt.task.util;

import ru.tyanmt.task.common.Cube;
import ru.tyanmt.task.common.Face;

import java.util.ArrayList;
import java.util.List;

import static ru.tyanmt.task.common.FaceHandler.flipFace;
import static ru.tyanmt.task.common.FaceHandler.flipFaceHorizontally;
import static ru.tyanmt.task.common.FaceHandler.transposeMatrix;

/**
 * Created by mityan on 03.08.2015.
 */
public class Printer {
    public static void print(Cube cube) {
        List<String> asciiCube = new ArrayList<>();
        List<Face> faces = new ArrayList<>();

        faces.add(new Face(transposeMatrix(cube.getFace(1))));
        faces.add(new Face(transposeMatrix(cube.getFace(2))));
        faces.add(new Face(transposeMatrix(cube.getFace(3))));
        faces.add(new Face(transposeMatrix(cube.getFace(4))));
        faces.add(new Face(transposeMatrix(new Face(flipFaceHorizontally(cube.getFace(5))))));
        faces.add(new Face(transposeMatrix(new Face(flipFaceHorizontally(cube.getFace(6))))));

        addFirstThreeFaces(faces, asciiCube);
        addOtherThreeFaces(faces, asciiCube);
        printFaces(asciiCube);
    }

    private static void printFaces(List<String> asciiCube) {
        for (String s : asciiCube) {
            System.out.println(s);
        }
    }

    private static void addFirstThreeFaces(List<Face> faces, List<String> asciiCube) {
        for (int i = 0; i < 5; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 1; j < 4; j++) {
                for (int k = 0; k < 5; k++) {
                    line.append(faces.get(j-1).getFace()[i][k] == j ? "o" : " ");
                }
            }
            asciiCube.add(line.toString());
        }
    }

    private static void addOtherThreeFaces(List<Face> faces, List<String> asciiCube) {
        for (int i = 4; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                StringBuilder line = new StringBuilder();
                line.append("     ");
                for (int k = 0; k < 5; k++) {
                    line.append(faces.get(i - 1).getFace()[j][k] == i ? "o" : " ");
                }
                asciiCube.add(line.toString());
            }
        }
    }
}
