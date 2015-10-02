package ru.tyanmt.task.common;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.IntStream;

import static ru.tyanmt.task.common.FaceMapper.*;
import static ru.tyanmt.task.common.FaceMergeValidator.*;

public class Cube {

    public static final int FACE_LENGTH = 5;
    private final int[][][] cube = new int[FACE_LENGTH][FACE_LENGTH][FACE_LENGTH];

    public Cube() {

    }

    public Cube(Cube cube) {
        IntStream.range(0, FACE_LENGTH).forEach(x ->
                        IntStream.range(0, FACE_LENGTH).forEach(y ->
                                        this.cube[x][y] = Arrays.copyOf(cube.cube[x][y], FACE_LENGTH)
                        )
        );
    }

    public Face getFace(FacePosition position) {
        Face face = new Face();
        IntStream.range(0, FACE_LENGTH).forEach(x ->
                        IntStream.range(0, FACE_LENGTH).forEach(y -> {
                                    face.setPoint(x, y, getPointFromFace(position, x, y, cube));
                                    if (isEdge(x, y) && !isVertex(x, y)) {
                                        face.setNeighborAt(x, y, getPointFromNeighborFace(position, x, y, cube));
                                    }
                                }
                        )
        );

        return face;
    }

    public boolean tryPutFaceOn(FacePosition position, Face faceCandidate) {
        Face cubeFace = this.getFace(position);
        if (!new FaceMergeValidator(faceCandidate, cubeFace).validate()) return false;
        addFaceOnSide(position, faceCandidate);
        return true;
    }

    private void addFaceOnSide(FacePosition position, Face face) {
        IntStream.range(0, FACE_LENGTH).forEach(x ->
                        IntStream.range(0, FACE_LENGTH).forEach(y -> {
                                    if (face.getPoint(x, y) != 0) {
                                        setPointToFace(position, x, y, cube);
                                    }
                                }
                        )
        );
    }
}
