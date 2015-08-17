package ru.tyanmt.task.common;

import java.util.Arrays;

import static ru.tyanmt.task.common.FaceHandler.*;
import static ru.tyanmt.task.common.FaceMapper.getPointFromFace;
import static ru.tyanmt.task.common.FaceMapper.setPointToFace;

/**
 * Created by mityan on 07.08.2015.
 */
public class Cube {
    int[][][] cube = new int[5][5][5];

    public Cube() {
        this.cube = new int[5][5][5];
    }

    public Cube(int[][][] cube) {
        this.cube = cube;
    }

    public int[][][] getCube() {
        int[][][] result = new int[5][5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                result[i][j] = Arrays.copyOf(cube[i][j], 5);
            }
        }
        return result;
    }

    public Face getFace(int number) {
        Face face = new Face();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                face.getFace()[i][j] = getPointFromFace(number, i, j, cube);
                if (isEdge(i, j) && (i == 2 || j == 2)) {
                    face.getAdjacentFaces()[i / 2][j / 2] = getPointFromFace(number, i, j, cube);
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
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (faceCandidate.getFace()[i][j] != 0) {
                    setPointToFace(number, i, j, faceCandidate.getFace()[i][j], cube);
                }
            }
        }
    }
}
