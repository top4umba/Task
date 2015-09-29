package ru.tyanmt.task.common;

import static ru.tyanmt.task.common.Cube.FACE_LENGTH;

/**
 * Created by mityan on 29.09.2015.
 */
public class MatrixUtil {
    public static int[][] flipMatrix(int[][] matrix) {
        int[][] flippedMatrix = new int[FACE_LENGTH][FACE_LENGTH];
        for (int i = 0; i < FACE_LENGTH; i++) {
            flippedMatrix[i] = matrix[FACE_LENGTH - 1 - i];
        }
        return flippedMatrix;
    }

    public static int[][] flipMatrixHorizontally(int[][] matrix) {
        int[][] flippedMatrix = new int[FACE_LENGTH][FACE_LENGTH];
        for (int i = 0; i < FACE_LENGTH; i++) {
            for (int j = 0; j < FACE_LENGTH; j++) {
                flippedMatrix[i][j] = matrix[i][FACE_LENGTH - 1 - j];
            }
        }
        return flippedMatrix;
    }

    public static int[][] rotateMatrixClockwise(int[][] matrix) {
        int[][] rotatedMatrix = new int[FACE_LENGTH][FACE_LENGTH];
        for (int i = 0; i < FACE_LENGTH; i++) {
            for (int j = 0; j < FACE_LENGTH; j++) {
                rotatedMatrix[j][FACE_LENGTH - 1 - i] = matrix[i][j];
            }
        }
        return rotatedMatrix;
    }

    public static int[][] transposeMatrix(int[][] matrix) {
        int[][] transposedMatrix = new int[FACE_LENGTH][FACE_LENGTH];
        for (int i = 0; i < FACE_LENGTH; i++) {
            for (int j = 0; j < FACE_LENGTH; j++) {
                transposedMatrix[i][j] = matrix[j][i];
            }
        }
        return transposedMatrix;
    }
}
