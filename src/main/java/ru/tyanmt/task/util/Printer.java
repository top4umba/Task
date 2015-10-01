package ru.tyanmt.task.util;

import ru.tyanmt.task.common.Cube;
import ru.tyanmt.task.common.Face;
import ru.tyanmt.task.common.FacePosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static ru.tyanmt.task.common.Cube.FACE_LENGTH;
import static ru.tyanmt.task.common.FacePosition.LEFT;
import static ru.tyanmt.task.common.FacePosition.REAR;

/**
 * Created by mityan on 03.08.2015.
 */
public class Printer {
    public static void print(Cube cube) {
        List<String> asciiFaces = new ArrayList<>();
        List<Face> faces = Arrays.stream(FacePosition.values())
                .map(position ->
                                position == REAR || position == LEFT ?
                                        cube.getFace(position).flipHorizontally().transpose() :
                                        cube.getFace(position).transpose()
                ).collect(Collectors.toList());

        asciiFaces.addAll(encodeFirstThreeFaces(faces));
        asciiFaces.addAll(encodeOtherThreeFaces(faces));
        printFaces(asciiFaces);
    }

    private static void printFaces(List<String> asciiCube) {
        asciiCube.forEach(System.out::println);
    }

    private static List<String> encodeFirstThreeFaces(List<Face> faces) {
        List<String> result = new ArrayList<>();
        for (int x = 0; x < FACE_LENGTH; x++) {
            StringBuilder line = new StringBuilder();
            for (int i = 1; i < 4; i++) {
                for (int y = 0; y < FACE_LENGTH; y++) {
                    line.append(faces.get(i - 1).getPoint(x, y) == i ? "o" : " ");
                }
            }
            result.add(line.toString());
        }
        return result;
    }

    private static List<String> encodeOtherThreeFaces(List<Face> faces) {
        List<String> result = new ArrayList<>();
        for (int i = 4; i < 7; i++) {
            for (int x = 0; x < FACE_LENGTH; x++) {
                StringBuilder line = new StringBuilder();
                for (int j = 0; j < FACE_LENGTH; j++) {
                    line.append(" ");
                }
                for (int y = 0; y < FACE_LENGTH; y++) {
                    line.append(faces.get(i - 1).getPoint(x, y) == i ? "o" : " ");
                }
                result.add(line.toString());
            }
        }
        return result;
    }
}
