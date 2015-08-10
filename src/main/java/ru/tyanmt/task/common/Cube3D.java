package ru.tyanmt.task.common;

import static ru.tyanmt.task.common.FaceHandler.*;

/**
 * Created by mityan on 07.08.2015.
 */
public class Cube3D {
    int[][][] cube = new int[5][5][5];

    public Cube3D() {
        this.cube = new int[5][5][5];
    }

    public Cube3D(int[][][] cube) {
        this.cube = cube;
    }

    int[][] cubeInt = new int[5][5];

    public Face3D getTop() {
        Face3D face = new Face3D();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; i++) {
                face.getFace()[i][j] = cube[i][4][j];
            }
        }
        return face;
    }

    public void placeOnTop(Face3D faceCandidate) {
        Face3D cubeFace = this.getTop();
        int i = 0;
        while (!isAppropriateFace(faceCandidate, cubeFace) && i<8) {
            faceCandidate.setFace(rotateClockwise(faceCandidate));
            i++;
            if (i == 4) {
                faceCandidate.setFace(flipFace(faceCandidate));
            }
        }
        if (i==8) return;
        addFaceOnTopOfCube(faceCandidate);
    }

    private void addFaceOnTopOfCube(Face3D faceCandidate) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; i++) {
                cube[i][4][j] = faceCandidate.getFace()[i][j];
            }
        }
    }
}
