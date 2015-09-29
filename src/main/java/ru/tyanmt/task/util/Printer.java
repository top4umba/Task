package ru.tyanmt.task.util;

import ru.tyanmt.task.common.Cube;
import ru.tyanmt.task.common.Face;

import java.util.ArrayList;
import java.util.List;

import static ru.tyanmt.task.common.Cube.FACE_LENGTH;
import static ru.tyanmt.task.common.MatrixUtil.flipMatrixHorizontally;
import static ru.tyanmt.task.common.MatrixUtil.transposeMatrix;

/**
 * Created by mityan on 03.08.2015.
 */
public class Printer {
    public static void print(Cube cube) {
        List<String> asciiFaces = new ArrayList<>();
        List<Face> faces = new ArrayList<>();

        faces.add(new Face(transposeMatrix(cube.getFace(1).getMatrix())));
        faces.add(new Face(transposeMatrix(cube.getFace(2).getMatrix())));
        faces.add(new Face(transposeMatrix(cube.getFace(3).getMatrix())));
        faces.add(new Face(transposeMatrix(cube.getFace(4).getMatrix())));
        faces.add(new Face(transposeMatrix(flipMatrixHorizontally(cube.getFace(5).getMatrix()))));
        faces.add(new Face(transposeMatrix(flipMatrixHorizontally(cube.getFace(6).getMatrix()))));

        encodeFirstThreeFaces(faces, asciiFaces);
        encodeOtherThreeFaces(faces, asciiFaces);
        printFaces(asciiFaces);
    }

    private static void printFaces(List<String> asciiCube) {
        for (String s : asciiCube) {
            System.out.println(s);
        }
    }

    private static void encodeFirstThreeFaces(List<Face> faces, List<String> asciiCube) {
        for (int i = 0; i < FACE_LENGTH; i++) {
            StringBuilder line = new StringBuilder();
            for (int j = 1; j < 4; j++) {
                for (int k = 0; k < FACE_LENGTH; k++) {
                    line.append(faces.get(j-1).getMatrix()[i][k] == j ? "o" : " ");
                }
            }
            asciiCube.add(line.toString());
        }
    }

    private static void encodeOtherThreeFaces(List<Face> faces, List<String> asciiCube) {
        for (int i = 4; i < 7; i++) {
            for (int j = 0; j < FACE_LENGTH; j++) {
                StringBuilder line = new StringBuilder();
                line.append("     ");
                for (int k = 0; k < FACE_LENGTH; k++) {
                    line.append(faces.get(i - 1).getMatrix()[j][k] == i ? "o" : " ");
                }
                asciiCube.add(line.toString());
            }
        }
    }
}
