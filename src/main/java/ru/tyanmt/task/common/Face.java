package ru.tyanmt.task.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static ru.tyanmt.task.common.Cube.FACE_LENGTH;

public class Face {

    private final int[][] matrix = new int[FACE_LENGTH][FACE_LENGTH];
    private final int[][] neighborFaces = new int[FACE_LENGTH][FACE_LENGTH];

    public Face() {
    }

    private Face(int[][] matrix) {
        IntStream.range(0, FACE_LENGTH).forEach(i -> this.matrix[i] = Arrays.copyOf(matrix[i], FACE_LENGTH));
    }

    public void setPoint(int x, int y, int value) {
        matrix[x][y] = value;
    }

    public int getPoint(int x, int y) {
        return matrix[x][y];
    }

    public int getNeighborAt(int x, int y) {
        return neighborFaces[x][y];
    }

    public void setNeighborAt(int x, int y, int value) {
        neighborFaces[x][y] = value;
    }

    public List<Face> getRotateOptions() {
        List<Face> result = new ArrayList<>();
        Face face = this;
        Face flippedFace = this.flip();
        for (int i = 0; i < 4; i++) {
            result.add(face);
            result.add(flippedFace);
            face = face.rotateClockwise();
            flippedFace = flippedFace.rotateClockwise();
        }
        return result;
    }

    public Face flip() {
        int[][] flippedMatrix = new int[FACE_LENGTH][FACE_LENGTH];
        IntStream.range(0, FACE_LENGTH).forEach(x ->
                        flippedMatrix[x] = matrix[FACE_LENGTH - 1 - x]
        );
        return new Face(flippedMatrix);
    }

    public Face rotateClockwise() {
        int[][] rotatedMatrix = new int[FACE_LENGTH][FACE_LENGTH];
        IntStream.range(0, FACE_LENGTH).forEach(x ->
                        IntStream.range(0, FACE_LENGTH).forEach(y ->
                                        rotatedMatrix[y][FACE_LENGTH - 1 - x] = matrix[x][y]
                        )
        );
        return new Face(rotatedMatrix);
    }

    public Face flipHorizontally() {
        int[][] flippedMatrix = new int[FACE_LENGTH][FACE_LENGTH];
        IntStream.range(0, FACE_LENGTH).forEach(x ->
                        IntStream.range(0, FACE_LENGTH).forEach(y ->
                                        flippedMatrix[x][y] = matrix[x][FACE_LENGTH - 1 - y]
                        )
        );
        return new Face(flippedMatrix);
    }

    public Face transpose() {
        int[][] transposedMatrix = new int[FACE_LENGTH][FACE_LENGTH];
        IntStream.range(0, FACE_LENGTH).forEach(x ->
                        IntStream.range(0, FACE_LENGTH).forEach(y ->
                                        transposedMatrix[x][y] = matrix[y][x]
                        )
        );
        return new Face(transposedMatrix);
    }
}
