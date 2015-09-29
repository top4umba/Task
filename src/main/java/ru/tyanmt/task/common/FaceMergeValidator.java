package ru.tyanmt.task.common;

import static ru.tyanmt.task.common.Cube.FACE_LENGTH;

/**
 * Created by top4umba on 10.08.2015.
 */
public class FaceMergeValidator {

    public static boolean isAppropriateFace(Face faceCandidate, Face cubeFace) {
        if (!isVerticesAccessible(cubeFace)) return false;
        for (int i = 0; i < FACE_LENGTH; i++) {
            for (int j = 0; j < FACE_LENGTH; j++) {
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

    private static boolean isVerticesAccessible(Face face) {
        for (int i = 0; i < FACE_LENGTH; i += lastIndex()) {
            for (int j = 0; j < FACE_LENGTH; j += lastIndex()) {
                int vertex = face.getMatrix()[i][j];
                int horizontalAdjacentPoint = face.getMatrix()[i == 0 ? 1 : penultimateIndex()][j];
                int verticalAdjacentPoint = face.getMatrix()[i][j == 0 ? 1 : penultimateIndex()];
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

    private static boolean isEmpty(int i) {
        return i == 0;
    }

    private static boolean isAdjacentEdgeEmpty(Face face, int i, int j) {
        return face.getAdjacentFacesSection()[i][j] == 0;
    }

    private static boolean isAnyAdjacentFaceEmpty(Face face, int i, int j) {
        return face.getAdjacentFacesSection()[i == 0 ? 1 : penultimateIndex()][j] == 0 || face.getAdjacentFacesSection()[i][j == 0 ? 1 : penultimateIndex()] == 0;
    }

    public static boolean isVertex(int i, int j) {
        return (i == 0 || i == lastIndex()) && (j == 0 || j == lastIndex());
    }

    public static boolean isEdge(int i, int j) {
        return i == 0 || i == lastIndex() || j == 0 || j == lastIndex();
    }

    private static int lastIndex() {
        return FACE_LENGTH - 1;
    }

    private static int penultimateIndex() {
        return lastIndex() - 1;
    }
}
