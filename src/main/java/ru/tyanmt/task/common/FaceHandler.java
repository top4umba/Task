package ru.tyanmt.task.common;

/**
 * Created by top4umba on 10.08.2015.
 */
public class FaceHandler {

    public static int[][] flipFace(Face3D face) {
        int[][] faceMatrix = new int[5][5];
        for (int i = 0; i < 5; i++) {
            faceMatrix[i] = face.getFace()[5 - 1 - i];
        }
        return faceMatrix;
    }

    public static int[][] rotateClockwise(Face3D faceCandidate) {
        int[][] newFace = new int[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                newFace[j][faceCandidate.getFace().length - 1 - i] = faceCandidate.getFace()[i][j];
            }
        }
        return newFace;
    }

    public static boolean isAppropriateFace(Face3D faceCandidate, Face3D cubeFace) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!isEmpty(cubeFace.getFace()[i][j])) {
                    if (!isEmpty(faceCandidate.getFace()[i][j])) {
                        return false;
                    }
                } else {
                    if (isEmpty(faceCandidate.getFace()[i][j])) {
                        if (isVertex(i, j)) {
                            if (!isBothAdjacentEdgesEmpty(cubeFace, i, j)) return false;
                        } else {
                            if (isEdge(i, j) && !isAdjacentEdgeEmpty(cubeFace, i, j)) return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean isEmpty(int i) {
        return i == 0;
    }

    private static boolean isAdjacentEdgeEmpty(Face3D face, int i, int j) {
        return face.getAdjacentFaces()[i == 0 || i == 4 ? i / 2 : 1][j == 0 || j == 4 ? j / 2 : 1] == 0;
    }

    private static boolean isBothAdjacentEdgesEmpty(Face3D face, int i, int j) {
        return face.getAdjacentFaces()[i / 2][1] == 0 && face.getAdjacentFaces()[1][j / 2] == 0;
    }

    private static boolean isVertex(int i, int j) {
        return (i == 0 && j == 0) || (i == 0 && j == 4) || (i == 4 && j == 0) || (i == 4 && j == 0);
    }

    public static boolean isEdge(int i, int j) {
        return i == 0 || i == 4 || j == 0 || j == 4;
    }
}
