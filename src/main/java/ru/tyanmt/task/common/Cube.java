package ru.tyanmt.task.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static ru.tyanmt.task.common.FaceMergeValidator.isEdge;
import static ru.tyanmt.task.common.FaceMergeValidator.isVertex;
import static ru.tyanmt.task.common.FacePosition.LEFT;
import static ru.tyanmt.task.common.FacePosition.REAR;

public class Cube {

    public static final int FACE_LENGTH = 5;
    private static final int FACES_IN_FIRST_ROW = 3;
    private final int[][][] cube = new int[FACE_LENGTH][FACE_LENGTH][FACE_LENGTH];

    public Cube() {

    }

    public Cube(Cube cube) {
        IntStream.range(0, FACE_LENGTH).forEach(x ->
                        IntStream.range(0, FACE_LENGTH).forEach(y ->
                                        this.cube[x][y] = Arrays.copyOf(cube.cube[x][y], FACE_LENGTH)
                        )
        );
    }

    public Face getFace(FacePosition position) {
        int[][] matrix = new int[FACE_LENGTH][FACE_LENGTH];
        int[][] neighborFaces = new int[FACE_LENGTH][FACE_LENGTH];
        IntStream.range(0, FACE_LENGTH).forEach(x ->
                        IntStream.range(0, FACE_LENGTH).forEach(y -> {
                                    matrix[x][y] = position.getPoint(x, y, cube);
                                    if (isEdge(x, y) && !isVertex(x, y)) {
                                        neighborFaces[x][y] = position.getPointFromNeighbor(x, y, cube);
                                    }
                                }
                        )
        );
        return new Face(matrix, neighborFaces);
    }

    public boolean tryPutFaceOn(FacePosition position, Face faceCandidate) {
        Face cubeFace = this.getFace(position);
        if (!new FaceMergeValidator(faceCandidate, cubeFace).validate()) return false;
        addFaceOnSide(position, faceCandidate);
        return true;
    }

    private void addFaceOnSide(FacePosition position, Face face) {
        IntStream.range(0, FACE_LENGTH).forEach(x ->
                        IntStream.range(0, FACE_LENGTH).forEach(y -> {
                                    if (face.getPoint(x, y) != 0) {
                                        position.setPoint(x, y, cube);
                                    }
                                }
                        )
        );
    }

    @Override
    public String toString() {

        List<Face> faces = Arrays.stream(FacePosition.values())
                .map(position -> position == REAR || position == LEFT ?
                                this.getFace(position).flip().transpose() :
                                this.getFace(position).transpose()
                ).collect(Collectors.toList());
        StringBuilder textCube = new StringBuilder();
        encodeFacesHorizontally(faces, textCube);
        encodeFacesVertically(faces, textCube);
        textCube.setLength(textCube.length()-1);
        return textCube.toString();
    }

    private void encodeFacesHorizontally(List<Face> faces, StringBuilder textCube) {
        IntStream.range(0, FACE_LENGTH).forEach(x -> encodeHorizontalLine(faces, x, textCube));
    }

    private void encodeHorizontalLine(List<Face> faces, int x, StringBuilder textCube) {
        IntStream.rangeClosed(1, FACES_IN_FIRST_ROW).forEach(faceNumber ->
                        IntStream.range(0, FACE_LENGTH).forEach(y ->
                                        textCube.append(faces.get(faceNumber - 1).getPoint(x, y) == faceNumber ? "o" : " ")
                        )
        );
        textCube.append("\n");
    }

    private void encodeFacesVertically(List<Face> faces, StringBuilder textCube) {
        IntStream.range(FACES_IN_FIRST_ROW + 1, 7).forEach(faceNumber ->
                        IntStream.range(0, FACE_LENGTH).forEach(x -> {
                                    IntStream.range(0, FACE_LENGTH).forEach(i -> textCube.append(" "));
                                    IntStream.range(0, FACE_LENGTH).forEach(y ->
                                            textCube.append(faces.get(faceNumber - 1).getPoint(x, y) == faceNumber ? "o" : " "));
                                    textCube.append("\n");
                                }
                        )
        );
    }
}
