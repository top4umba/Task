package ru.tyanmt.task.common;

/**
 * Created by top4umba on 10.08.2015.
 */
public class FaceHandler {

    public static int[][] flipFace(Face3D face) {
        int[][] faceMatrix = new int[5][5];
        for (int i = 0; i <5 ; i++) {
            faceMatrix[i] = face.getFace()[5-1-i];
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
                if (cubeFace.getFace()[i][j] != 0) {
                    if (faceCandidate.getFace()[i][j] != 0) {
                        return false;
                    }
                } else {
                    if (faceCandidate.getFace()[i][j] == 0) {
                        if (isVertex(i, j)) {
                            if (!isAdjacentSidesEmpty(cubeFace, i, j)) return false;
                        } else {
                            if (isEdge(i,j)) {
                                //TODO if is edge and (not null cubeFace edge, or not null vertices and not the same number as adjacent side)==notEmpty, then false
                            };
                        }
                    }
                }
            }
        }
        return true;
    }

    private static boolean isAdjacentSidesEmpty(Face3D cubeFace, int i, int j) {
        boolean sideIsEmpty = true;
        for (int k = 0; k < 5; k++) {
            if (cubeFace.getFace()[i][k] != 0) {
                if (!sideIsEmpty) {
                    return false;
                } else {
                    sideIsEmpty = false;
                }
            }
            if (cubeFace.getFace()[k][j] != 0) {
                if (!sideIsEmpty) {
                    return false;
                } else {
                    sideIsEmpty = false;
                }
            }
        }
        return true;
    }

    private static boolean isVertex(int i, int j) {
        return (i == 0 && j == 0) || (i == 0 && j == 4) || (i == 4 && j == 0) || (i == 4 && j == 0);
    }

    private static boolean isEdge(int i, int j) {
        return i == 0 || i ==4 || j==0 || j == 4;
    }
}
