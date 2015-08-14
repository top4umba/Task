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
            faceMatrix[i] = face.getFace()[5 - 1 - i];
        }
        return faceMatrix;
    }

    public static int[][] rotateClockwise(Face faceCandidate) {
        int[][] newFace = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                newFace[j][faceCandidate.getFace().length - 1 - i] = faceCandidate.getFace()[i][j];
            }
        }
        return newFace;
    }

    public static boolean isVerticesAccessible(Face face) {
        for (int i = 0; i < 5; i += 4) {
            for (int j = 0; j < 5; j += 4) {
                int vertex = face.getFace()[i][j];
                int horAdjacentPoint = face.getFace()[i == 0 ? 1 : 3][j];
                int vertAdjacentPoint = face.getFace()[i][j == 0 ? 1 : 3];
                if (isEmpty(vertex) &&
                        !isEmpty(horAdjacentPoint) &&
                        !isEmpty(vertAdjacentPoint) &&
                        horAdjacentPoint != vertAdjacentPoint) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isAppropriateFace(Face faceCandidate, Face cubeFace) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!isEmpty(cubeFace.getFace()[i][j])) {
                    if (!isEmpty(faceCandidate.getFace()[i][j])) {
                        return false;
                    }
                } else {
                    if (isEmpty(faceCandidate.getFace()[i][j])) {
                        if (isVertex(i, j)) {
                            if (!isAnyAdjacentFaceEmpty(cubeFace, i, j)) return false;
                        } else {
                            if (isEdge(i, j) && !isAdjacentEdgeEmpty(cubeFace, i, j)) return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static List<Face> getRotateOptions(Face face) {
        Face faceOption = new Face(face.getFace());
        List<Face> result = new ArrayList<>();
        result.add(face);
        for (int i = 0; i < 7; i++) {
            if (i == 3) {
                faceOption.setFace(flipFace(faceOption));
                result.add(new Face(faceOption.getFace()));
            } else {
                faceOption.setFace(rotateClockwise(faceOption));
                result.add(new Face(faceOption.getFace()));
            }
        }
        return result;
    }

    private static boolean isEmpty(int i) {
        return i == 0;
    }

    private static boolean isAdjacentEdgeEmpty(Face face, int i, int j) {
        return face.getAdjacentFaces()[i == 0 || i == 4 ? i / 2 : 1][j == 0 || j == 4 ? j / 2 : 1] == 0;
    }

    private static boolean isAnyAdjacentFaceEmpty(Face face, int i, int j) {
        return face.getAdjacentFaces()[i / 2][1] == 0 || face.getAdjacentFaces()[1][j / 2] == 0;
    }

    private static boolean isVertex(int i, int j) {
        return (i == 0 && j == 0) || (i == 0 && j == 4) || (i == 4 && j == 0) || (i == 4 && j == 0);
    }

    public static boolean isEdge(int i, int j) {
        return i == 0 || i == 4 || j == 0 || j == 4;
    }
}
