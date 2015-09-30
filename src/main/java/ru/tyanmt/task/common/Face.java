package ru.tyanmt.task.common;

import static ru.tyanmt.task.common.Cube.FACE_LENGTH;

/**
 * Created by mityan on 10.08.2015.
 */
public class Face {
    //TODO Make matrices immutable
    private final int[][] matrix;
    private final int[][] adjacentFacesSection;

    public Face() {
        this(new int[FACE_LENGTH][FACE_LENGTH]);
    }

    public Face(int[][] face) {
        this(face, new int[FACE_LENGTH][FACE_LENGTH]);
    }

    public Face(int[][] face, int[][] adjacentFacesSection) {
        this.matrix = face;
        this.adjacentFacesSection = adjacentFacesSection;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public int[][] getAdjacentFacesSection() {
        return adjacentFacesSection;
    }
}
