package ru.tyanmt.task.util;

import ru.tyanmt.task.common.Cube;
import ru.tyanmt.task.common.Face;
import ru.tyanmt.task.common.FacePosition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.tyanmt.task.common.Cube.FACE_LENGTH;
import static ru.tyanmt.task.common.FacePosition.LEFT;
import static ru.tyanmt.task.common.FacePosition.REAR;

public class Printer {
    private final static int FACES_IN_FIRST_ROW = 3;

    public static void print(Cube cube) {
        System.out.println(" ");
        printFaces(encodeFaces(cube));
        System.out.println(" ");
    }

    private static List<String> encodeFaces(Cube cube) {
        List<String> asciiFaces = new ArrayList<>();
        List<Face> faces = Arrays.stream(FacePosition.values())
                .map(position -> position == REAR || position == LEFT ?
                                cube.getFace(position).flipHorizontally().transpose() :
                                cube.getFace(position).transpose()
                ).collect(Collectors.toList());
        asciiFaces.addAll(encodeFacesHorizontally(faces));
        asciiFaces.addAll(encodeFacesVertically(faces));
        return asciiFaces;
    }

    private static void printFaces(List<String> asciiCube) {
        asciiCube.forEach(System.out::println);
    }

    private static List<String> encodeFacesHorizontally(List<Face> faces) {
        List<String> result = new ArrayList<>();
        IntStream.range(0, FACE_LENGTH).forEach(x -> {
                    StringBuilder line = new StringBuilder();
                    encodeHorizontalLine(faces, x, line);
                    result.add(line.toString());
                }

        );
        return result;
    }

    private static void encodeHorizontalLine(List<Face> faces, int x, StringBuilder line) {
        IntStream.rangeClosed(1, FACES_IN_FIRST_ROW).forEach(faceNumber ->
                        IntStream.range(0, FACE_LENGTH).forEach(y ->
                                        line.append(faces.get(faceNumber - 1).getPoint(x, y) == faceNumber ? "o" : " ")
                        )
        );
    }

    private static List<String> encodeFacesVertically(List<Face> faces) {
        List<String> result = new ArrayList<>();
        IntStream.range(FACES_IN_FIRST_ROW + 1, 7).forEach(faceNumber ->
                        IntStream.range(0, FACE_LENGTH).forEach(x -> {
                                    StringBuilder line = new StringBuilder();
                                    IntStream.range(0, FACE_LENGTH).forEach(i -> line.append(" "));
                                    IntStream.range(0, FACE_LENGTH).forEach(y ->
                                            line.append(faces.get(faceNumber - 1).getPoint(x, y) == faceNumber ? "o" : " "));
                                    result.add(line.toString());
                                }
                        )
        );
        return result;
    }
}
