package ru.tyanmt.task.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by top4umba on 10.08.2015.
 */
public class FaceHandler {

    public static int[][] flipFace(Face face) {
        int[][] faceMatrix = new int[5][5];
        for (int i = 0; i < 5; i++) {
            faceMatrix[i] = face.getMatrix()[5 - 1 - i];
        }
        return faceMatrix;
    }

    public static int[][] flipFaceHorizontally(Face face) {
        int[][] faceMatrix = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                faceMatrix[i][j] = face.getMatrix()[i][5 - 1 - j];
            }
        }
        return faceMatrix;
    }

    public static int[][] rotateClockwise(Face faceCandidate) {
        int[][] newFace = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                newFace[j][faceCandidate.getMatrix().length - 1 - i] = faceCandidate.getMatrix()[i][j];
            }
        }
        return newFace;
    }

    public static int[][] transposeMatrix(Face faceCandidate) {
        int[][] newFace = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                newFace[i][j] = faceCandidate.getMatrix()[j][i];
            }
        }
        return newFace;
    }

    private static boolean isVerticesAccessible(Face face) {
        for (int i = 0; i < 5; i += 4) {
            for (int j = 0; j < 5; j += 4) {
                int vertex = face.getMatrix()[i][j];
                int horizontalAdjacentPoint = face.getMatrix()[i == 0 ? 1 : 3][j];
                int verticalAdjacentPoint = face.getMatrix()[i][j == 0 ? 1 : 3];
                if (isEmpty(vertex) &&
                        !isEmpty(horizontalAdjacentPoint) &&
                        !isEmpty(verticalAdjacentPoint) &&
                        horizontalAdjacentPoint != verticalAdjacentPoint) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isAppropriateFace(Face faceCandidate, Face cubeFace) {
        if (!isVerticesAccessible(cubeFace)) return false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (isEmpty(cubeFace.getMatrix()[i][j])) {
                    if (isEmpty(faceCandidate.getMatrix()[i][j])) {
                        if (isVertex(i, j)) {
                            if (!isAnyAdjacentFaceEmpty(cubeFace, i, j)) return false;
                        } else {
                            if (isEdge(i, j) && !isAdjacentEdgeEmpty(cubeFace, i, j)) return false;
                        }
                    }
                } else {
                    if (!isEmpty(faceCandidate.getMatrix()[i][j])) {
                        return false;
                    }

                }
            }
        }
        return true;
    }

    public static List<Face> getRotateOptions(Face face) {
        Face faceOption = new Face(face.getMatrix());
        List<Face> result = new ArrayList<>();
        result.add(face);
        for (int i = 0; i < 7; i++) {
            faceOption.setMatrix(i == 3 ? flipFace(faceOption) : rotateClockwise(faceOption));
            result.add(new Face(faceOption.getMatrix()));
        }
        return result;
    }

    private static boolean isEmpty(int i) {
        return i == 0;
    }

    private static boolean isAdjacentEdgeEmpty(Face face, int i, int j) {
        return face.getAdjacentFacesSection()[i][j] == 0;
    }

    private static boolean isAnyAdjacentFaceEmpty(Face face, int i, int j) {
        return face.getAdjacentFacesSection()[i == 0 ? 1 : 3][j] == 0 || face.getAdjacentFacesSection()[i][j == 0 ? 1 : 3] == 0;
    }

    public static boolean isVertex(int i, int j) {
        return (i == 0 || i == 4) && (j == 0 || j == 4);
    }

    public static boolean isEdge(int i, int j) {
        return i == 0 || i == 4 || j == 0 || j == 4;
    }
}
