package ru.tyanmt.task.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Face {

    private final int FACE_LENGTH;
    private final int[][] matrix;
    private final int[][] neighborFaces;


    public Face(int[][] matrix) {
        this.FACE_LENGTH = matrix.length;
        this.matrix = new int[FACE_LENGTH][FACE_LENGTH];
        this.neighborFaces = new int[FACE_LENGTH][FACE_LENGTH];
        copyMatrix(this.matrix, matrix);
    }

    public Face(int[][] matrix, int[][] neighborFaces) {
        this(matrix);
        copyMatrix(this.neighborFaces, neighborFaces);
    }

    public int getPoint(int x, int y) {
        return matrix[x][y];
    }

    public int getNeighborAt(int x, int y) {
        return neighborFaces[x][y];
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

    private void copyMatrix(int[][] toMatrix, int[][] fromMatrix) {
        IntStream.range(0, FACE_LENGTH).forEach(i -> toMatrix[i] = Arrays.copyOf(fromMatrix[i], FACE_LENGTH));
    }

    private Face rotateClockwise() {
        int[][] rotatedMatrix = new int[FACE_LENGTH][FACE_LENGTH];
        IntStream.range(0, FACE_LENGTH).forEach(x ->
                        IntStream.range(0, FACE_LENGTH).forEach(y ->
                                        rotatedMatrix[y][FACE_LENGTH - 1 - x] = matrix[x][y]
                        )
        );
        return new Face(rotatedMatrix);
    }
}
