package ru.tyanmt.task.common;

import static ru.tyanmt.task.common.Cube.FACE_LENGTH;

/**
 * Created by top4umba on 10.08.2015.
 */
public class FaceMergeValidator {

    public static boolean isAppropriateFace(Face faceCandidate, Face cubeFace) {
        if (!isVerticesAccessible(cubeFace)) return false;
        for (int x = 0; x < FACE_LENGTH; x++) {
            for (int y = 0; y < FACE_LENGTH; y++) {
                if (isEmpty(cubeFace.getPoint(x, y))) {
                    if (isEmpty(faceCandidate.getPoint(x, y))) {
                        if (isVertex(x, y)) {
                            if (!isAnyNeighborFaceEmpty(cubeFace, x, y)) return false;
                        } else {
                            if (isEdge(x, y) && !isNeighborEdgeEmpty(cubeFace, x, y)) return false;
                        }
                    }
                } else {
                    if (!isEmpty(faceCandidate.getPoint(x, y))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static boolean isVerticesAccessible(Face face) {
        for (int x = 0; x < FACE_LENGTH; x += lastIndex()) {
            for (int y = 0; y < FACE_LENGTH; y += lastIndex()) {
                int vertex = face.getPoint(x, y);
                int horNeighborPoint = face.getPoint(getNeighborCoordinate(x), y);
                int verNeighborPoint = face.getPoint(x, getNeighborCoordinate(y));
                if (isEmpty(vertex) &&
                        !isEmpty(horNeighborPoint) &&
                        !isEmpty(verNeighborPoint) &&
                        horNeighborPoint != verNeighborPoint) {
                    return false;
                }
            }
        }
        return true;
    }

    private static int getNeighborCoordinate(int x) {
        return x == 0 ? 1 : penultimateIndex();
    }

    private static boolean isEmpty(int value) {
        return value == 0;
    }

    private static boolean isNeighborEdgeEmpty(Face face, int x, int y) {
        return face.getNeighborAt(x, y) == 0;
    }

    private static boolean isAnyNeighborFaceEmpty(Face face, int x, int y) {
        return face.getNeighborAt(getNeighborCoordinate(x), y) == 0 || face.getNeighborAt(x, getNeighborCoordinate(y)) == 0;
    }

    public static boolean isVertex(int x, int y) {
        return (x == 0 || x == lastIndex()) && (y == 0 || y == lastIndex());
    }

    public static boolean isEdge(int x, int y) {
        return x == 0 || x == lastIndex() || y == 0 || y == lastIndex();
    }

    private static int lastIndex() {
        return FACE_LENGTH - 1;
    }

    private static int penultimateIndex() {
        return lastIndex() - 1;
    }
}
