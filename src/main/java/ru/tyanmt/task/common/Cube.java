package ru.tyanmt.task.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
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
        if (!new FaceMergeValidator(faceCandidate, cubeFace).validate()) {
            return false;
        }
        addFaceOnSide(position, faceCandidate);
        return true;
    }

    @Override
    public String toString() {
        List<Face> faces = Arrays.stream(FacePosition.values())
                .map(position -> position == REAR || position == LEFT ?
                                this.getFace(position).flip().transpose() :
                                this.getFace(position).transpose()
                ).collect(toList());
        return encodeFacesHorizontally(faces) + encodeFacesVertically(faces);
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

    private boolean isEdge(int x, int y) {
        return x == 0 || x == FACE_LENGTH - 1 || y == 0 || y == FACE_LENGTH - 1;
    }

    private String encodeFacesHorizontally(List<Face> faces) {
        return IntStream.range(0, FACE_LENGTH)
                .mapToObj(x -> encodeHorizontalLine(faces, x))
                .collect(joining("\n")) + "\n";
    }

    private String encodeHorizontalLine(List<Face> faces, int x) {
        StringBuilder builder = new StringBuilder();
        IntStream.rangeClosed(1, FACES_IN_FIRST_ROW).forEach(faceNumber ->
                        IntStream.range(0, FACE_LENGTH).forEach(y ->
                                        builder.append(getSign(faces, faceNumber, x, y))
                        )
        );
        return builder.toString();
    }

    private String encodeFacesVertically(List<Face> faces) {
        return IntStream.range(FACES_IN_FIRST_ROW + 1, 7)
                .mapToObj(faceNumber -> encodeAFaceVertically(faces, faceNumber))
                .collect(joining());
    }

    private String encodeAFaceVertically(List<Face> faces, Integer faceNumber) {
        return IntStream.range(0, FACE_LENGTH)
                .mapToObj(x -> IntStream.range(0, FACE_LENGTH)
                        .mapToObj(y -> getSign(faces, faceNumber, x, y))
                        .collect(joining()))
                .map(this::pad)
                .collect(joining("\n")) + "\n";
    }

    private String getSign(List<Face> faces, int faceNumber, int x, int y) {
        return faces.get(faceNumber - 1).getPoint(x, y) == faceNumber ? "o" : " ";
    }

    private String pad(String line) {
        return String.format("%1$" + FACE_LENGTH * 2 + "s", line);
    }


}
