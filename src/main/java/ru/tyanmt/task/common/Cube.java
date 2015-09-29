package ru.tyanmt.task.common;

import java.util.Arrays;

import static ru.tyanmt.task.common.FaceMapper.*;
import static ru.tyanmt.task.common.FaceMergeValidator.*;

/**
 * Created by mityan on 07.08.2015.
 */
public class Cube {

    public static final int FACE_LENGTH = 5;
    //TODO Make cube private
    private final int[][][] cube = new int[FACE_LENGTH][FACE_LENGTH][FACE_LENGTH];

    public Cube() {
    }

    public Cube(int[][][] cube) {
        for (int i = 0; i < FACE_LENGTH; i++) {
            for (int j = 0; j < FACE_LENGTH; j++) {
                for (int k = 0; k < FACE_LENGTH; k++) {
                    this.cube[i][j][k] = cube[i][j][k];
                }
            }
        }
    }

    public Cube(Cube cube) {
        for (int i = 0; i < FACE_LENGTH; i++) {
            for (int j = 0; j < FACE_LENGTH; j++) {
                for (int k = 0; k < FACE_LENGTH; k++) {
                    this.cube[i][j][k] = cube.cube[i][j][k];
                }
            }
        }
    }

    public int[][][] getCube() {
        int[][][] result = new int[FACE_LENGTH][FACE_LENGTH][FACE_LENGTH];
        for (int i = 0; i < FACE_LENGTH; i++) {
            for (int j = 0; j < FACE_LENGTH; j++) {
                result[i][j] = Arrays.copyOf(cube[i][j], FACE_LENGTH);
            }
        }
        return result;
    }

    public Face getFace(int number) {
        Face face = new Face();
        for (int i = 0; i < FACE_LENGTH; i++) {
            for (int j = 0; j < FACE_LENGTH; j++) {
                face.getMatrix()[i][j] = getPointFromFace(number, i, j, cube);
                if (isEdge(i, j) && !isVertex(i,j)) {
                    face.getAdjacentFacesSection()[i][j] = getPointFromSection(number, i, j, cube);
                }
            }
        }
        return face;
    }

    public boolean putFaceOn(int number, Face faceCandidate) {
        Face cubeFace = this.getFace(number);
        if (!isAppropriateFace(faceCandidate, cubeFace)) return false;
        addFaceOnSide(number, faceCandidate);
        return true;
    }

    private void addFaceOnSide(int number, Face faceCandidate) {
        for (int i = 0; i < FACE_LENGTH; i++) {
            for (int j = 0; j < FACE_LENGTH; j++) {
                if (faceCandidate.getMatrix()[i][j] != 0) {
                    setPointToFace(number, i, j, number, cube);
                }
            }
        }
    }
}
