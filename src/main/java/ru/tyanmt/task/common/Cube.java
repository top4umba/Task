package ru.tyanmt.task.common;

import java.util.Arrays;

import static ru.tyanmt.task.common.FaceMapper.*;
import static ru.tyanmt.task.common.FaceMergeValidator.*;

/**
 * Created by mityan on 07.08.2015.
 */
public class Cube {

    public static final int FACE_LENGTH = 5;
    private final int[][][] cube = new int[FACE_LENGTH][FACE_LENGTH][FACE_LENGTH];

    public Cube() {

    }

    public Cube(Cube cube) {
        for (int x = 0; x < FACE_LENGTH; x++) {
            for (int y = 0; y < FACE_LENGTH; y++) {
                this.cube[x][y] = Arrays.copyOf(cube.cube[x][y], FACE_LENGTH);
            }
        }
    }

    public Face getFace(FacePosition position) {
        Face face = new Face();
        for (int x = 0; x < FACE_LENGTH; x++) {
            for (int y = 0; y < FACE_LENGTH; y++) {
                face.setPoint(x, y, getPointFromFace(position, x, y, cube));
                if (isEdge(x, y) && !isVertex(x, y)) {
                    face.setNeighborAt(x, y, getPointFromSection(position, x, y, cube));
                }
            }
        }
        return face;
    }

    public boolean putFaceOn(FacePosition position, Face faceCandidate) {
        Face cubeFace = this.getFace(position);
        if (!isAppropriateFace(faceCandidate, cubeFace)) return false;
        addFaceOnSide(position, faceCandidate);
        return true;
    }

    private void addFaceOnSide(FacePosition position, Face faceCandidate) {
        for (int x = 0; x < FACE_LENGTH; x++) {
            for (int y = 0; y < FACE_LENGTH; y++) {
                if (faceCandidate.getPoint(x, y) != 0) {
                    setPointToFace(position, x, y, cube);
                }
            }
        }
    }
}
